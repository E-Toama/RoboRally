package game;

import game.cards.ActiveCard;
import game.cards.Card;
import game.gameboard.BoardElement;
import game.gameboard.GameBoard;
import game.player.Player;
import game.player.PlayerMat;
import game.utilities.*;
import server.network.Server;
import utilities.MessageHandler;
import utilities.messages.*;

import java.util.*;

public class Game {

    public Server server;
    private final MessageHandler messageHandler = new MessageHandler();

    private final GameState gameState;

    public Game(Server server, List<Player> playerList, GameBoard gameBoard) {

        this.server = server;
        this.gameState = new GameState(playerList, server, gameBoard);

    }

    public GameBoard getGameBoard() {
        return gameState.gameBoard;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void startGame() {

        setActivePhase(0);

        gameState.initialize();

        startStaringPointSelection();

    }

    public void setActivePhase(int newActivePhase) {

        gameState.activePhase = newActivePhase;

        String activePhaseMessage = messageHandler.buildMessage("ActivePhase", new ActivePhase(gameState.activePhase));
        server.sendMessageToAllUsers(activePhaseMessage);

    }

    public void startStaringPointSelection() {

        String currentPlayerMessage = messageHandler.buildMessage("CurrentPlayer", new CurrentPlayer(server.getPlayerList().get(0).getPlayerID()));
        server.sendMessageToAllUsers(currentPlayerMessage);

    }

    public void continueStartingPointSelection(Player player, int position) {

        gameState.playerMatHashMap.get(player.getPlayerID()).getRobot().setRobotPosition(position);

        Position position1 = PositionLookUp.positionToXY.get(position);
        gameState.playerMatHashMap.get(player.getPlayerID()).getRobot().setStartingPosition(position1);
        gameState.gameBoard.getGameBoard()[position1.getY()][position1.getX()].setRobot(gameState.playerMatHashMap.get(player.getPlayerID()).getRobot());

        String startingPointTaken = messageHandler.buildMessage("StartingPointTaken", new StartingPointTaken(player.getPlayerID(), position));
        server.sendMessageToAllUsers(startingPointTaken);

        if (server.getPlayerList().get(server.getPlayerList().size() - 1).getPlayerID() == player.getPlayerID()) {

            programmingPhase();

        } else {

            int newPlayerIDIndex = server.getPlayerList().indexOf(player) + 1;
            String currentPlayerMessage = messageHandler.buildMessage("CurrentPlayer", new CurrentPlayer(server.getPlayerList().get(newPlayerIDIndex).getPlayerID()));
            server.sendMessageToAllUsers(currentPlayerMessage);

        }

    }

    public void programmingPhase() {

        setActivePhase(2);

        for (PlayerMat playerMat : gameState.playerMatList) {
            playerMat.discardRegister();
            server.handOutCards(playerMat.getPlayer().getPlayerID(), playerMat.drawNineCards(), playerMat.getDeckCount());
        }

    }

    public synchronized void selectCard(String card, int register, int playerID) {

        Card selectedCard = Card.getCardByString(card);
        gameState.playerMatHashMap.get(playerID).addCardToRegister(register - 1, selectedCard);

        String cardSelected = messageHandler.buildMessage("CardSelected", new CardSelected(playerID, register));
        server.sendMessageToAllUsers(cardSelected);

        if (register == 5 && gameState.playersFinishedSelectionList.size() == 0) {

            gameState.playersFinishedSelectionList.add(playerID);

            gameState.playerMatHashMap.get(playerID).addRemainingCardsToDiscardedPile();

            String selectionFinished = messageHandler.buildMessage("SelectionFinished", new SelectionFinished(playerID));
            server.sendMessageToAllUsers(selectionFinished);

            startTimer();

        } else if (register == 5) {

            gameState.playersFinishedSelectionList.add(playerID);

            gameState.playerMatHashMap.get(playerID).addRemainingCardsToDiscardedPile();

            String selectionFinished = messageHandler.buildMessage("SelectionFinished", new SelectionFinished(playerID));
            server.sendMessageToAllUsers(selectionFinished);

        }

    }

    public synchronized void startTimer() {

        String timerStarted = messageHandler.buildMessage("TimerStarted", new TimerStarted());
        server.sendMessageToAllUsers(timerStarted);

        TimerTask timerEndedTask = new TimerTask() {

            @Override
            public void run() {

                timerEnded();

            }

        };

        Timer timer = new Timer();

        //TODO: Reset TImer to 30
        timer.schedule(timerEndedTask, 30000);

    }

    public synchronized void timerEnded() {

        if (gameState.playersFinishedSelectionList.size() == gameState.playerList.size()) {

            String timerEnded = messageHandler.buildMessage("TimerEnded", new TimerEnded(new int[0]));
            server.sendMessageToAllUsers(timerEnded);

        } else {

            int[] notFinishedPlayer = gameState.getNotFinishedPlayer();

            String timerEnded = messageHandler.buildMessage("TimerEnded", new TimerEnded(notFinishedPlayer));
            server.sendMessageToAllUsers(timerEnded);

            for (int playerID : notFinishedPlayer) {

                String discardHand = messageHandler.buildMessage("DiscardHand", new DiscardHand(playerID));
                server.sendMessageToSingleUser(discardHand, playerID);

                gameState.playerMatHashMap.get(playerID).addCompleteHandToDiscardedPile();

                Card[] newCards = gameState.playerMatHashMap.get(playerID).drawFiveCards();
                gameState.playerMatHashMap.get(playerID).setRegister(newCards);

                String cardsYouGotNow = messageHandler.buildMessage("CardsYouGotNow", new CardsYouGotNow(Card.toStringArray(newCards)));
                server.sendMessageToSingleUser(cardsYouGotNow, playerID);

            }

        }

        startActivationPhase();

    }

    public void startActivationPhase() {

        setActivePhase(3);

        for (PlayerMat playerMat : gameState.playerMatList) {

            playerMat.setWasRebootedThisRound(false);

        }

        gameState.nextRegisterList.addAll(gameState.playerMatList);

        gameState.register = 1;

        nextRegister();

    }

    public void nextRegister() {

        int registerNumber = gameState.register;

        gameState.registerList = gameState.nextRegisterList;
        gameState.nextRegisterList = new ArrayList<>();

        determinePriority();

        ActiveCard[] activeCards = new ActiveCard[gameState.registerList.size()];

        for (int i = 0; i < gameState.registerList.size(); i++) {
            
            activeCards[i] = new ActiveCard(gameState.registerList.get(i).getPlayer().getPlayerID(), gameState.registerList.get(i).getRegister()[registerNumber - 1].getName());
            
        }

        String currentCards = messageHandler.buildMessage("CurrentCards", new CurrentCards(activeCards));
        server.sendMessageToAllUsers(currentCards);

        nextPlayersTurn();

    }

    public void determinePriority() {

        for (PlayerMat playerMat : gameState.registerList) {

            playerMat.calculateDistanceToAntenna(gameState.gameBoard.getAntennaPosition());

        }

        gameState.registerList.sort(Comparator.comparingInt(PlayerMat::getDistanceToAntenna));

        for (int i = 0; i < gameState.registerList.size() - 1; i++) {

            PlayerMat p1 = gameState.registerList.get(i);
            PlayerMat p2 = gameState.registerList.get(i + 1);

            if (p1.getDistanceToAntenna() == p2.getDistanceToAntenna()) {

                if (p2.getRobot().getRobotXY().getY() < p1.getRobot().getRobotXY().getY()) {

                    gameState.registerList.set(i, p2);
                    gameState.registerList.set(i + 1, p1);

                }

            }

        }

    }

    public void nextPlayersTurn() {

        if (gameState.registerList.size() > 0) {

            String currentPlayer = messageHandler.buildMessage("CurrentPlayer", new CurrentPlayer(gameState.registerList.get(0).getPlayer().getPlayerID()));
            server.sendMessageToAllUsers(currentPlayer);

        } else {

            activateBoardElements();

        }

    }

    public synchronized void continuePlayersTurn() {

        gameState.registerList.get(0).getRegister()[gameState.register - 1].action(this, gameState, gameState.registerList.get(0).getPlayer().getPlayerID());

    }

    public void activateBoardElements() {

        activateBlueConveyorBelts();

        activateGreenConveyorBelts();

        activatePushPanels();

        activateGears();

        activateLaser();

        activateRobotLaser();

        activateEnergySpace();

        activateCheckPoint();

        if(gameState.register < 5) {

            gameState.register = gameState.register + 1;

            nextRegister();

        } else {

            programmingPhase();

        }

    }

    public void activateBlueConveyorBelts() {

        List<PlayerMat> playerOnBlueConveyorBelt = new ArrayList<>();

        for (PlayerMat playerMat : gameState.playerMatList) {

            Position position = playerMat.getRobot().getRobotXY();

            if (gameState.gameBoard.getBlueConveyorBelts().containsKey(position)) {

                playerOnBlueConveyorBelt.add(playerMat);

            }

            if (gameState.gameBoard.getBlueRotatingConveyorBelts().containsKey(position)) {

                playerOnBlueConveyorBelt.add(playerMat);

            }

        }

        List<ConveyorBeltTarget> conveyorBeltTargets = new ArrayList<>();
        ConveyorBeltsHelperV2 conveyorBeltsHelperV2 = new ConveyorBeltsHelperV2();

        for (PlayerMat playerMat : playerOnBlueConveyorBelt) {

            conveyorBeltTargets.add(conveyorBeltsHelperV2.calculateBlueConveyorBeltTarget(gameState, playerMat));

        }

        completeConveyorBeltActivationV2(conveyorBeltTargets);

    }

    public void activateGreenConveyorBelts() {

        List<PlayerMat> playerOnGreenConveyorBelt = new ArrayList<>();

        for (PlayerMat playerMat : gameState.playerMatList) {

            Position position = playerMat.getRobot().getRobotXY();

            if (gameState.gameBoard.getGreenConveyorBelts().containsKey(position)) {

                playerOnGreenConveyorBelt.add(playerMat);

            }

            if (gameState.gameBoard.getBlueRotatingConveyorBelts().containsKey(position)) {

                playerOnGreenConveyorBelt.add(playerMat);

            }

        }

        List<ConveyorBeltTarget> conveyorBeltTargets = new ArrayList<>();
        ConveyorBeltsHelperV2 conveyorBeltsHelperV2 = new ConveyorBeltsHelperV2();

        for (PlayerMat playerMat : playerOnGreenConveyorBelt) {

            conveyorBeltTargets.add(conveyorBeltsHelperV2.calculateGreenConveyorBeltTarget(gameState, playerMat));

        }

        completeConveyorBeltActivationV2(conveyorBeltTargets);

    }

    private void completeConveyorBeltActivationV2(List<ConveyorBeltTarget> conveyorBeltTargets) {

        for (ConveyorBeltTarget targetOne : conveyorBeltTargets) {

            for (ConveyorBeltTarget targetTwo : conveyorBeltTargets) {

                if (targetOne.getPlayerID() != targetTwo.getPlayerID() && targetOne.getTargetBoardElement() == targetTwo.getTargetBoardElement()) {

                    conveyorBeltTargets.remove(targetOne);
                    conveyorBeltTargets.remove(targetTwo);

                }

            }

        }

        for (ConveyorBeltTarget target : conveyorBeltTargets) {

            Position oldPosition = gameState.playerMatHashMap.get(target.getPlayerID()).getRobot().getRobotXY();
            Position newPosition = target.getTargetBoardElement().getXY();

            for (String rotatingDirection : target.getRotationDirection()) {

                switch (rotatingDirection) {
                    case "clockwise" -> {
                        gameState.playerMatHashMap.get(target.getPlayerID()).getRobot().turnRight();
                        String playerTurningClockwise = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(target.getPlayerID(), "clockwise"));
                        gameState.server.sendMessageToAllUsers(playerTurningClockwise);
                    }
                    case "counterClockwise" -> {
                        gameState.playerMatHashMap.get(target.getPlayerID()).getRobot().turnLeft();
                        String playerTurningCounterClockwise = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(target.getPlayerID(), "counterClockwise"));
                        gameState.server.sendMessageToAllUsers(playerTurningCounterClockwise);
                    }
                }

            }

            if (target.getTargetIsBelt()) {

                BoardElement currentBoardElement = gameState.gameBoard.getGameBoard()[oldPosition.getY()][oldPosition.getX()];
                BoardElement targetBoardElement = target.getTargetBoardElement();

                currentBoardElement.setRobot(null);
                targetBoardElement.setRobot(gameState.playerMatHashMap.get(target.getPlayerID()).getRobot());

                gameState.playerMatHashMap.get(target.getPlayerID()).getRobot().setXY(targetBoardElement.getXY());

                String movement = messageHandler.buildMessage("Movement", new Movement(target.getPlayerID(), gameState.playerMatHashMap.get(target.getPlayerID()).getRobot().getRobotPosition()));
                server.sendMessageToAllUsers(movement);

            } else {

                MoveHandler moveHandler = new MoveHandler();

                moveHandler.move(this, gameState, target.getPlayerID(), oldPosition, newPosition, target.getMovingDirection(), false, true);

            }

        }

    }

    public void activatePushPanels() {

        for (PlayerMat playerMat : gameState.playerMatList) {

            Position position = playerMat.getRobot().getRobotXY();

            if (gameState.gameBoard.getPushPanels().containsKey(position)) {

                if (contains(gameState.gameBoard.getPushPanels().get(position).getPushPanelRegister(), gameState.register)) {

                    gameState.gameBoard.getPushPanels().get(position).activate(this, gameState, playerMat.getPlayer().getPlayerID());

                }

            }

        }

    }

    public void activateGears() {

        for (PlayerMat playerMat : gameState.playerMatList) {

            Position playerPosition = playerMat.getRobot().getRobotXY();

            if (gameState.gameBoard.getGears().containsKey(playerPosition)) {

                gameState.gameBoard.getGears().get(playerPosition).activate(this, gameState, playerMat.getPlayer().getPlayerID());

            }

        }

    }

    private boolean contains(int[] array, int c) {

        boolean returnValue = false;

        for (int i : array) {

            if (c == i) {

                returnValue = true;
                break;

            }

        }

        return returnValue;

    }

    public void activateLaser() {

        LaserHandler laserHandler = new LaserHandler();

        for (BoardElement laser : gameState.gameBoard.getLasers().values()) {

            laserHandler.handleBoardLaserFire(gameState, laser);

        }

    }

    public void activateRobotLaser() {

        String playerShooting = messageHandler.buildMessage("PlayerShooting", new PlayerShooting());
        server.sendMessageToAllUsers(playerShooting);

        LaserHandler laserHandler = new LaserHandler();
        BoardElement[][] gameBoard = getGameBoard().getGameBoard();

        for (PlayerMat playerMat : gameState.playerMatList) {

            laserHandler.handleRobotFire(gameState, gameBoard[playerMat.getRobot().getRobotXY().getY()][playerMat.getRobot().getRobotXY().getX()]);

        }

    }

    public void activateEnergySpace() {

        for (PlayerMat playerMat : gameState.playerMatList) {

            Position playerPosition = playerMat.getRobot().getRobotXY();

            if (gameState.gameBoard.getEnergySpaces().containsKey(playerPosition)) {

                gameState.gameBoard.getEnergySpaces().get(playerPosition).activate(this, gameState, playerMat.getPlayer().getPlayerID());

            }

        }

    }

    public void activateCheckPoint() {

        for (PlayerMat playerMat : gameState.playerMatList) {

            Position playerPosition = playerMat.getRobot().getRobotXY();

            if (gameState.gameBoard.getCheckPoints().containsKey(playerPosition)) {

                gameState.gameBoard.getCheckPoints().get(playerPosition).activate(this, gameState, playerMat.getPlayer().getPlayerID());

            }

        }

    }

}
