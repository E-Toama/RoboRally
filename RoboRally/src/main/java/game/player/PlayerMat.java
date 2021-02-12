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
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the player Mat within the activation phase and
 * contains the current programming Cards in the registers and game relevant values
 * @author
 */
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
    private int energyCubes = 5;

    private List<Card> currentHand = new ArrayList<>();

    /**
     * Constructor of PlayerMat
     *
     * @param player is the player who belongs to the player mat
     * @param robot is the robot of the player
     * @param server is the running server
     */
    public PlayerMat(Player player, Robot robot, Server server) {

        this.player = player;
        this.robot = robot;
        this.server = server;

    }

    /**
     *
     * @param wasRebootedThisRound flag for whether a robot was rebooted or not
     */
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

    /**
     * calculates distance between the position of each robot from the priority antenna
     * @param antennaPosition
     */
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

    /**
     *
     * @param register is one of five registers
     * @param card is the chosen programming card
     */
    public void addCardToRegister(int register, Card card) {
        this.register[register] = card;
    }

    /**
     * adds played programming card to the discarded Cards pile
     *
     * @param discardedCard is the played card
     */
    public void addDiscardedCard(Card discardedCard) {

        if (discardedCard != null) {

            this.discardedCards.add(discardedCard);

        }

    }

    /**
     * initializes the card deck with a total
     * of 20 programming cards
     +
     */
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

    /**
     * draws a random programming card
     * @return a random card
     */
    public Card drawRandomCard() {

        if (deck.size() == 0) {

            shuffleDeck();

        }

        return deck.remove( (int) (Math.random() * (deck.size() - 1)));

    }

    /**
     * is responsible for drawing a random programming card when
     *
     * @param currentRegisterNumber
     * @return a random programming card
     */
    public Card drawRandomCardForDamageCardAction(int currentRegisterNumber) {

        Card drawnCard = drawRandomCard();

        if (drawnCard.getName().equals("Again") && currentRegisterNumber == 1) {

            Card tmpCard = drawRandomCard();

            deck.add(drawnCard);

            drawnCard = tmpCard;

        }

        return drawnCard;

    }

    /**
     * shuffles the discarded cards into the deck
     */
    public void shuffleDeck() {

        deck.addAll(discardedCards);
        discardedCards = new ArrayList<>();

        String shuffleCoding = messageHandler.buildMessage("ShuffleCoding", new ShuffleCoding(player.getPlayerID()));
        server.sendMessageToAllUsers(shuffleCoding);

    }

    /**
     * draws nine random programming cards in each programming phase
     * @return a deck of nine programming cards
     */
    public Card[] drawNineCards() {

        Card[] returnValue = new Card[9];

        for (int i = 0; i < 9; i++) {
            returnValue[i] = drawRandomCard();
        }

        currentHand.addAll(Arrays.asList(returnValue));
        return returnValue;

    }

    /**
     * draws five programming cards
     * used for players who were to slow to choose five cards before the timer ends
     * @return five random programming cards
     */
    public Card[] drawFiveCards() {

        Card[] returnValue = new Card[5];

        for (int i = 0; i < 5; i++) {
            returnValue[i] = drawRandomCard();
        }

        if (returnValue[0].getName().equals("Again")) {

            Card secondValue = returnValue[1];
            returnValue[1] = returnValue[0];
            returnValue[0] = secondValue;

        }

        return returnValue;

    }

    /**
     * adds the remaining programming cards which were not chosen
     * in the programming phase to the discarded cards pile and
     * resets the current hand to empty
     */
    public void addRemainingCardsToDiscardedPile() {

        for (Card registerCard : register) {

            for (Card card : currentHand) {

                if(registerCard.getName().equals(card.getName())) {
                    currentHand.remove(card);
                    break;
                }

            }

        }

        for (Card card : currentHand) {

            if (card != null) {

                discardedCards.add(card);

            }

        }

        currentHand = new ArrayList<>();

    }

    /**
     * adds the complete current hand of a player to the discarded cards pile
     * and resets the current hand to empty
     */
    public void addCompleteHandToDiscardedPile() {

        for (Card card : currentHand) {

            if (card != null) {

                discardedCards.add(card);

            }

        }

        currentHand = new ArrayList<>();

    }

    /**
     * adds the card in the registers to the discarded card pile
     * and resets the five registers
     */
    public void discardRegister() {

        for (Card card : register) {

            if (card != null) {

                discardedCards.add(card);

            }

        }

        register = new Card[5];

    }

    /**
     * is responsible  for rebooting a player to the starting point or
     * restarting point with the correct orientation
     * and also drawing a damage card
     *
     * @param game is the current game
     * @param gameState
     * @param isPlayerAction is a player
     */
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
