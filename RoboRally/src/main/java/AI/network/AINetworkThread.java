package AI.network;

import AI.logic.AIGameState;
import AI.logic.utilities.AICardHandler;
import game.gameboard.BoardElement;
import game.gameboard.GameBoard;
import game.player.Player;
import game.utilities.Position;
import game.utilities.PositionLookUp;
import utilities.MessageHandler;
import utilities.messages.*;
import utilities.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Similar functionality to ClientThread, only with reduced methods (without GUI)
 */
public class AINetworkThread implements Runnable {

    private final Socket socket;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final MessageHandler messageHandler = new MessageHandler();
    private final AIGameState aiGameState;
    private final double protocolVersion = 1.0;
    private final String group = "NeidischeNarwale";
    private int playerID;
    private HashMap<Integer, Player> playerList = new HashMap();
    private AICardHandler aiCardHandler;

    public AINetworkThread(int port, AIGameState aiGameState) throws IOException {

        this.socket = new Socket("localhost", port);
        this.incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outgoing = new PrintWriter(socket.getOutputStream(), true);
        this.aiGameState = aiGameState;
        this.aiCardHandler = new AICardHandler(this, aiGameState);

    }

    @Override
    public void run() {

        try {

            establishConnection();

            handleIncomingMessages();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                socket.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void establishConnection() throws IOException {

        String firstIncomingJSON = incoming.readLine();

        Message firstIncomingMessage = messageHandler.handleMessage(firstIncomingJSON);

        if (firstIncomingMessage.getMessageType().equals("HelloClient") && firstIncomingMessage.getMessageBody() instanceof HelloClient) {

            String helloServer = messageHandler.buildMessage("HelloServer", new HelloServer(protocolVersion, group, true));

            outgoing.println(helloServer);

            String secondIncomingJSON = incoming.readLine();

            Message secondIncomingMessage = messageHandler.handleMessage(secondIncomingJSON);

            if (secondIncomingMessage.getMessageType().equals("Welcome") && secondIncomingMessage.getMessageBody() instanceof Welcome) {

                Welcome receivedMessage = (Welcome) secondIncomingMessage.getMessageBody();
                this.playerID = receivedMessage.getPlayerID();

            } else if (secondIncomingMessage.getMessageType().equals("Error") && secondIncomingMessage.getMessageBody() instanceof utilities.messages.Error) {

                utilities.messages.Error receivedMessage = (Error) secondIncomingMessage.getMessageBody();

                throw new IOException(receivedMessage.getError());

            } else {

                throw new IOException("Something went wrong! Couldn't establish Connection!");

            }

        } else {

            throw new IOException("Something went wrong! Couldn't establish Connection!");

        }

    }

    public void choosePlayerValues() throws IOException {

        String name = Integer.toString(playerID);
        int figure = getFirstFreeRobotFigure(0);

        String playerValues = messageHandler.buildMessage("PlayerValues", new PlayerValues(name, figure));
        sendJson(playerValues);

    }

    private int getFirstFreeRobotFigure(int figure) {

        for (Player player : playerList.values()) {

            if (player.getFigure() == figure) {

                return getFirstFreeRobotFigure(figure + 1);

            }

        }

        return figure;

    }

    private void handleIncomingMessages() throws IOException {

        while (true) {

            try {

                String incomingJSON = incoming.readLine();

                if (incomingJSON == null) {

                    return;

                }

                Message incomingMessage = messageHandler.handleMessage(incomingJSON);

                switch (incomingMessage.getMessageType()) {

                    case "PlayerAdded":
                        handlePlayerAdded(incomingMessage);
                        break;

                    case "PlayerStatus":
                        handlePlayerStatus(incomingMessage);
                        break;

                    case "SelectMap":
                        handleSelectMap(incomingMessage);
                        break;

                    case "MapSelected":
                        handleMapSelected(incomingMessage);

                    case "GameStarted":
                        handleGameStarted(incomingMessage);
                        break;

                    case "ReceivedChat":
                        handleReceivedChat(incomingMessage);
                        break;

                    case "Error":
                        handleError(incomingMessage);
                        break;

                    case "ConnectionUpdate":
                        handleConnectionUpdate(incomingMessage);
                        break;

                    case "CardPlayed":
                        handleCardPlayed(incomingMessage);
                        break;

                    case "CurrentPlayer":
                        handleCurrentPlayer(incomingMessage);
                        break;

                    case "ActivePhase":
                        handleActivePhase(incomingMessage);
                        break;

                    case "StartingPointTaken":
                        handleStartingPointTaken(incomingMessage);
                        break;

                    case "YourCards":
                        handleYourCards(incomingMessage);
                        break;

                    case "NotYourCards":
                        handleNotYourCards(incomingMessage);
                        break;

                    case "CardSelected":
                        handleCardSelected(incomingMessage);
                        break;

                    case "SelectionFinished":
                        handleSelectionFinished(incomingMessage);
                        break;

                    case "TimerStarted":
                        handleTimerStarted(incomingMessage);
                        break;

                    case "TimerEnded":
                        handleTimerEnded(incomingMessage);
                        break;

                    case "DiscardHand":
                        handleDiscardHand(incomingMessage);
                        break;

                    case "CardsYouGotNow":
                        handleCardsYouGotNow(incomingMessage);
                        break;

                    case "CurrentCards":
                        handleCurrentCards(incomingMessage);
                        break;

                    case "Movement":
                        handleMovement(incomingMessage);
                        break;

                    case "DrawDamage":
                        handleDrawDamage(incomingMessage);
                        break;

                    case "PlayerShooting":
                        handlePlayerShooting(incomingMessage);
                        break;

                    case "Reboot":
                        handleReboot(incomingMessage);
                        break;

                    case "PlayerTurning":
                        handlePlayerTurning(incomingMessage);
                        break;

                    case "Energy":
                        handleEnergy(incomingMessage);
                        break;

                    case "CheckPointReached":
                        handleCheckPointReached(incomingMessage);
                        break;

                    case "GameWon":
                        handleGameWon(incomingMessage);
                        return;

                    case "PickDamage":
                        handlePickDamage(incomingMessage);

                    default:
                        break;

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void handlePlayerAdded(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof PlayerAdded) {

            PlayerAdded receivedMessage = (PlayerAdded) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayer().getPlayerID() == playerID) {

                String setStatus = messageHandler.buildMessage("SetStatus", new SetStatus(true));
                sendJson(setStatus);

            }

            playerList.put(receivedMessage.getPlayer().getPlayerID(), receivedMessage.getPlayer());

        }


    }

    private void handlePlayerStatus(Message incomingMessage) {
    }

    private void handleGameStarted(Message incomingMessage) {
    }

    private void handleSelectMap(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof SelectMap) {

            SelectMap receivedMessage = (SelectMap) incomingMessage.getMessageBody();
            String[] availableMaps = receivedMessage.getAvailableMaps();

            String[] selectedMap = {availableMaps[1]};

            String mapSelected = messageHandler.buildMessage("MapSelected", new MapSelected(selectedMap));
            sendJson(mapSelected);

        }

    }

    private void handleMapSelected(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof MapSelected) {

            MapSelected receivedMessage = (MapSelected) incomingMessage.getMessageBody();

            aiGameState.setGameBoardName(receivedMessage.getMap()[0]);

            aiGameState.setTargetCheckpoint(1);

            aiGameState.setGameBoard(new GameBoard(receivedMessage.getMap()[0]));

            aiGameState.setRatingMaps(receivedMessage.getMap()[0], 1);

        }

    }

    private void handleReceivedChat(Message incomingMessage) {
    }

    private void handleError(Message incomingMessage) {
    }

    private void handleConnectionUpdate(Message incomingMessage) {
    }

    private void handleCardPlayed(Message incomingMessage) {
    }

    private void handleCurrentPlayer(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof CurrentPlayer) {

            CurrentPlayer receivedMessage = (CurrentPlayer) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayerID() == playerID) {

                if (aiGameState.getActivePhase() == 0) {

                    Map.Entry<Position, BoardElement> entry = aiGameState.getGameBoard().getStartingPoints().entrySet().iterator().next();

                    Position startingPosition = entry.getKey();

                    int startingPositionAsInt = PositionLookUp.XYToPosition.get(startingPosition);

                    String setStartingPoint = messageHandler.buildMessage("SetStartingPoint", new SetStartingPoint(startingPositionAsInt));
                    sendJson(setStartingPoint);

                } else if (aiGameState.getActivePhase() == 3) {

                    String playIt = messageHandler.buildMessage("PlayIt", new PlayIt());
                    sendJson(playIt);

                }

            }

        }

    }

    private void handleActivePhase(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof ActivePhase) {

            ActivePhase receivedMessage = (ActivePhase) incomingMessage.getMessageBody();

            aiGameState.setActivePhase(receivedMessage.getPhase());

        }

    }

    private void handleStartingPointTaken(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof StartingPointTaken) {

            StartingPointTaken receivedMessage = (StartingPointTaken) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayerID() != playerID) {

                Position takenPosition = PositionLookUp.positionToXY.get(receivedMessage.getPosition());

                aiGameState.getGameBoard().getStartingPoints().remove(takenPosition);

            } else {

                Position startPosition = PositionLookUp.positionToXY.get(receivedMessage.getPosition());
                BoardElement startBoardElement = aiGameState.getGameBoard().getGameBoard()[startPosition.getY()][startPosition.getX()];

                aiGameState.setStartPosition(startPosition);
                aiGameState.setStartBoardElement(startBoardElement);

                aiGameState.setCurrentPosition(startPosition);
                aiGameState.setCurrentBoardElement(startBoardElement);

            }

        }

    }

    private void handleYourCards(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof YourCards) {

            YourCards yourCards = (YourCards) incomingMessage.getMessageBody();
            String[] cards = yourCards.getCards();


            aiCardHandler.handleCards(cards);

        }

    }

    private void handleNotYourCards(Message incomingMessage) {
    }

    private void handleCardSelected(Message incomingMessage) {
    }

    private void handleSelectionFinished(Message incomingMessage) {
    }

    private void handleTimerStarted(Message incomingMessage) {
    }

    private void handleTimerEnded(Message incomingMessage) {
    }

    private void handleDiscardHand(Message incomingMessage) {
    }

    private void handleCardsYouGotNow(Message incomingMessage) {
    }

    private void handleCurrentCards(Message incomingMessage) {
    }

    private void handleMovement(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof Movement) {

            Movement receivedMessage = (Movement) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayerID() == playerID) {

                Position position = PositionLookUp.positionToXY.get(receivedMessage.getTo());

                aiGameState.setCurrentPosition(position);

            }

        }

    }

    private void handleDrawDamage(Message incomingMessage) {
    }

    private void handlePlayerShooting(Message incomingMessage) {
    }

    private void handleReboot(Message incomingMessage) {
    }

    private void handlePlayerTurning(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof PlayerTurning) {

            PlayerTurning receivedMessage = (PlayerTurning) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayerID() == playerID) {

                switch (receivedMessage.getDirection()) {

                    case "clockwise" -> aiGameState.turningClockwise();
                    case "counterClockwise" -> aiGameState.turningCounterClockwise();

                }

            }

        }

    }

    private void handleEnergy(Message incomingMessage) {
    }

    private void handleCheckPointReached(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof CheckpointReached) {

            CheckpointReached receivedMessage = (CheckpointReached) incomingMessage.getMessageBody();

            aiGameState.setTargetCheckpoint(receivedMessage.getNumber() + 1);

            aiGameState.setRatingMaps(aiGameState.getGameBoardName(), aiGameState.getTargetCheckpoint());

        }

    }

    private void handleGameWon(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof GameWon) {

            GameWon receivedMessage = (GameWon) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayerID() == playerID) {

                System.out.println("Won the Game!");

            } else {

                System.out.println("Lost the Game!");

            }

        }

    }

    public void handlePickDamage(Message incomingMessage) {

        if (incomingMessage.getMessageBody() instanceof PickDamage) {

            PickDamage receivedMessage = (PickDamage) incomingMessage.getMessageBody();
            int count = receivedMessage.getCount();

            String[] damageCardsToChooseFrom = {"Virus", "Worm", "TrojanHorse"};
            String[] chosenDamageCards = new String[count];
            Random random = new Random();

            for (int i = 0; i < count; i++) {
                chosenDamageCards[i] = damageCardsToChooseFrom[random.nextInt(3)];
            }


            String chosenCardsMessage = messageHandler.buildMessage("SelectDamage", new SelectDamage(chosenDamageCards));
            sendJson(chosenCardsMessage);

        }

    }


    public void sendJson(String Json) {
        outgoing.println(Json);
    }

}
