package game.cards;

import game.Game;
import game.cards.damagecards.Spam;
import game.cards.damagecards.TrojanHorse;
import game.cards.damagecards.Virus;
import game.cards.damagecards.Worm;
import game.cards.programmingcards.*;
import game.utilities.GameState;
import utilities.MessageHandler;

/**
 * The abstract class Card is a parent class for all the different kinds of cards classes.
 * 
 */
public abstract class Card {

    protected String name;
    protected final MessageHandler messageHandler = new MessageHandler();

    /**
     * The method returns the card name.
     * 
     * @return the card name
     */
    public String getName() {
        return this.name;
    }

    /**
     * The method returns a list of cards names as string.
     * @param cards 
     *          the list of type Card
     *          
     * @return a list of cards names.
     */
    public static String[] toStringArray(Card[] cards) {

        String[] returnValue = new String[cards.length];

        for (int i = 0; i < cards.length; i++) {

            returnValue[i] = cards[i].getName();

        }

        return returnValue;

    }

    /**
     * This method returns a new card object from the card string.
     * 
     * @param card 
     *          the name of the card
     * 
     * @return an object of type card
     */
    public static Card getCardByString(String card) {

        return switch (card) {
            case "Again" -> new Again();
            case "BackUp" -> new BackUp();
            case "LeftTurn" -> new LeftTurn();
            case "MoveOne" -> new MoveOne();
            case "MoveTwo" -> new MoveTwo();
            case "MoveThree" -> new MoveThree();
            case "PowerUp" -> new PowerUp();
            case "RightTurn" -> new RightTurn();
            case "Spam" -> new Spam();
            case "TrojanHorse" -> new TrojanHorse();
            case "Virus" -> new Virus();
            case "Worm" -> new Worm();
            case "U-Turn" -> new UTurn();
            default -> null;
        };

    }

    /**
     * An abstract method for defining the action of the different existing cards.
     * 
     * @param game 
     *          is an object of game class
     * @param gameState
     *          is an object of GameState class
     * @param PlayerID
     *          is the player id
     */
    public abstract void action(Game game, GameState gameState, int PlayerID);

}
