package game.player;

import game.Robots.Robot;
import game.cards.Card;
import game.cards.programmingcards.*;
import server.network.Server;
import utilities.MessageHandler;
import utilities.messages.ShuffleCoding;

import java.util.ArrayList;
import java.util.List;

public class PlayerMat {

    private final Player player;
    private final Robot robot;

    private final Server server;
    private final MessageHandler messageHandler = new MessageHandler();

    private int priority;

    private List<Card> deck = new ArrayList<>();
    private List<Card> discardedCards = new ArrayList<>();
    private Card[] register = new Card[5];

    private int checkpointsReached = 0;
    private int deckCount = deck.size();
    private int discardedCount = discardedCards.size();

    private Card[] tmpCardStack;

    public PlayerMat(Player player, Robot robot, Server server) {

        this.player = player;
        this.robot = robot;
        this.server = server;

    }

    public Player getPlayer() {
        return player;
    }

    public Robot getRobot() {
        return robot;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
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

    public void setCheckpointsReached(int checkpointsReached) {
        this.checkpointsReached = checkpointsReached;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    public int getDiscardedCount() {
        return discardedCount;
    }

    public void addCardToRegister(int register, Card card) {
        this.register[register] = card;
    }

    public void resetRegister() {
        this.register = new Card[5];
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
        deck.add(new Backup());
        deck.add(new PowerUp());
        deck.add(new Again());
        deck.add(new Again());

    }

    public Card drawRandomCard() {
        return deck.remove( (int) (Math.random() * (deck.size() - 1)));
    }

    public Card[] drawNineCards() {

        Card[] returnValue = new Card[9];

        if (deck.size() >= 9) {

            for (int i = 0; i < 9; i++) {
                returnValue[i] = drawRandomCard();
            }

            return returnValue;

        } else {

            int remainingCards = deck.size();

            for (int i = 0; i < remainingCards; i++) {

                returnValue[i] = deck.get(i);

            }

            deck = discardedCards;
            discardedCards = new ArrayList<>();

            String shuffleCoding = messageHandler.buildMessage("ShuffleCoding", new ShuffleCoding(player.getPlayerID()));
            server.sendMessageToAllUsers(shuffleCoding);

            for (int i = remainingCards; i < 9; i++) {

                returnValue[i] = drawRandomCard();

            }

            tmpCardStack = returnValue;
            return returnValue;

        }

    }

    public Card[] drawFiveCards() {

        Card[] returnValue = new Card[5];

        if (deck.size() >= 5) {

            for (int i = 0; i < 5; i++) {
                returnValue[i] = drawRandomCard();
            }

            return returnValue;

        } else {

            int remainingCards = deck.size();

            for (int i = 0; i < remainingCards; i++) {

                returnValue[i] = deck.get(i);

            }

            deck = discardedCards;
            discardedCards = new ArrayList<>();

            String shuffleCoding = messageHandler.buildMessage("ShuffleCoding", new ShuffleCoding(player.getPlayerID()));
            server.sendMessageToAllUsers(shuffleCoding);

            for (int i = remainingCards; i < 5; i++) {

                returnValue[i] = drawRandomCard();

            }

            tmpCardStack = returnValue;
            return returnValue;

        }

    }


}
