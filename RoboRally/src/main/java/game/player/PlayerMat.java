package game.player;

import game.Game;
import game.robots.Robot;
import game.cards.Card;
import game.cards.programmingcards.*;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;
import server.network.Server;
import utilities.MessageHandler;
import utilities.messages.PlayerTurning;
import utilities.messages.Reboot;
import utilities.messages.ShuffleCoding;

import java.util.ArrayList;
import java.util.List;

public class PlayerMat {

    private final Player player;
    private final Robot robot;

    private final Server server;
    private final MessageHandler messageHandler = new MessageHandler();

    private int distanceToAntenna;

    private List<Card> deck = new ArrayList<>();
    private List<Card> discardedCards = new ArrayList<>();
    private Card[] register = new Card[5];

    private boolean wasRebootedThisRound = false;

    private int checkpointsReached = 0;
    private int energyCubes = 0;

    private List<Card> currentHand = new ArrayList<>();

    public PlayerMat(Player player, Robot robot, Server server) {

        this.player = player;
        this.robot = robot;
        this.server = server;

    }

    public List<Card> getCurrentHand() {
        return currentHand;
    }

    public void setWasRebootedThisRound(boolean wasRebootedThisRound) {
        this.wasRebootedThisRound = wasRebootedThisRound;
    }

    public boolean getWasRebootedThisRound() {
        return wasRebootedThisRound;
    }

    public Player getPlayer() {
        return player;
    }

    public Robot getRobot() {
        return robot;
    }

    public int getDistanceToAntenna() {
        return distanceToAntenna;
    }

    public int getEnergyCubes() {
        return energyCubes;
    }

    public void setEnergyCubes(int energyCubes) {
        this.energyCubes = energyCubes;
    }

    public void calculateDistanceToAntenna(Position antennaPosition) {

        Position robotPosition = robot.getRobotXY();

        distanceToAntenna = Position.calculateDistance(robotPosition, antennaPosition);

    }

    public Card[] getRegister() {
        return register;
    }

    public void setRegister(Card[] register) {
        this.register = register;
    }

    public int getCheckpointsReached() {
        return checkpointsReached;
    }

    public void setCheckpointsReached() {

        this.checkpointsReached = checkpointsReached + 1;


    }

    public int getDeckCount() {
        return deck.size();
    }

    public void addCardToRegister(int register, Card card) {
        this.register[register] = card;
    }

    public void addDiscardedCard(Card discardedCard) {

        if (discardedCard != null) {

            this.discardedCards.add(discardedCard);

        } else {

            System.out.println("Tried adding an empty Card to discarded Pile @addDiscardedCard");

        }

    }

    public void initializeDeck() {

        deck.add(new MoveOne());
        deck.add(new MoveOne());
        deck.add(new MoveOne());
        deck.add(new MoveOne());
        deck.add(new MoveOne());
        deck.add(new MoveTwo());
        deck.add(new MoveTwo());
        deck.add(new MoveTwo());
        deck.add(new MoveThree());
        deck.add(new RightTurn());
        deck.add(new RightTurn());
        deck.add(new RightTurn());
        deck.add(new LeftTurn());
        deck.add(new LeftTurn());
        deck.add(new LeftTurn());
        deck.add(new BackUp());
        deck.add(new PowerUp());
        deck.add(new Again());
        deck.add(new Again());
        deck.add(new UTurn());

    }

    public Card drawRandomCard() {

        if (deck.size() <= 0) {

            shuffleDeck();

        }

        return deck.remove( (int) (Math.random() * (deck.size() - 1)));

    }

    public void shuffleDeck() {

        deck = discardedCards;
        discardedCards = new ArrayList<>();

        String shuffleCoding = messageHandler.buildMessage("ShuffleCoding", new ShuffleCoding(player.getPlayerID()));
        server.sendMessageToAllUsers(shuffleCoding);

    }

    public Card[] drawNineCards() {

        Card[] returnValue = new Card[9];

        if (deck.size() >= 9) {

            for (int i = 0; i < 9; i++) {
                returnValue[i] = drawRandomCard();
                currentHand.add(returnValue[i]);
            }

            return returnValue;

        } else {

            int remainingCards = deck.size();

            for (int i = 0; i < remainingCards; i++) {

                returnValue[i] = deck.get(i);
                currentHand.add(returnValue[i]);

            }

            shuffleDeck();

            for (int j = remainingCards; j < 9; j++) {

                returnValue[j] = drawRandomCard();
                currentHand.add(returnValue[j]);

            }

            return returnValue;

        }

    }

    public Card[] drawFiveCards() {

        Card[] returnValue = new Card[5];

        if (deck.size() >= 5) {

            for (int i = 0; i < 5; i++) {
                returnValue[i] = drawRandomCard();
            }

        } else {

            int remainingCards = deck.size();

            for (int i = 0; i < remainingCards; i++) {

                returnValue[i] = deck.get(i);

            }

            shuffleDeck();

            for (int i = remainingCards; i < 5; i++) {

                returnValue[i] = drawRandomCard();

            }

        }

        if (returnValue[0].getName().equals("Again")) {

            Card secondValue = returnValue[1];
            returnValue[1] = returnValue[0];
            returnValue[0] = secondValue;

        }

        return returnValue;

    }

    public void addRemainingCardsToDiscardedPile() {

        for (Card card : register) {

            currentHand.remove(card);

        }

        for (Card card : currentHand) {

            if (card != null) {

                discardedCards.add(card);

            }

        }


        currentHand = new ArrayList<>();

    }

    public void addCompleteHandToDiscardedPile() {

        for (Card card : currentHand) {

            if (card != null) {

                discardedCards.add(card);

            }

        }

        currentHand = new ArrayList<>();

    }

    public void discardRegister() {

        for (Card card : register) {

            if (card != null) {

                discardedCards.add(card);

            }

        }

        register = new Card[5];

    }

    public void reboot(Game game, GameState gameState, boolean isPlayerAction) {

        wasRebootedThisRound = true;

        String reboot = messageHandler.buildMessage("Reboot", new Reboot(player.getPlayerID()));
        server.sendMessageToAllUsers(reboot);

        String[] wantedDamageCards = {"Spam", "Spam"};

        gameState.drawDamageCardHandler.drawDamageCards(player.getPlayerID(), wantedDamageCards);

        gameState.nextRegisterList.remove(this);

        discardRegister();

        List<String> turnings = new ArrayList<>();

        switch (robot.getOrientation()) {
            case "left" -> turnings.add("clockwise");
            case "right" -> turnings.add("counterClockwise");
            case "down" -> {
                turnings.add("clockwise");
                turnings.add("clockwise");
            }
        }

        for (String turning : turnings) {

            String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(player.getPlayerID(), turning));
            server.sendMessageToAllUsers(playerTurning);

        }

        robot.setOrientation("up");

        MoveHandler moveHandler = new MoveHandler();

        if (robot.getRobotXY().getX() < 3) {

            moveHandler.move(game, gameState, player.getPlayerID(), robot.getRobotXY(), robot.getStartingPosition(), "up", isPlayerAction, true);

        } else {

            moveHandler.move(game, gameState, player.getPlayerID(), robot.getRobotXY(), gameState.gameBoard.getRestartPoint().getXY(), gameState.gameBoard.getRestartPoint().getRestartOrientation(), isPlayerAction, true);


        }

    }

}
