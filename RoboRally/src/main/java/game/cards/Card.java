package game.cards;

import game.Game;
import game.cards.damagecards.Spam;
import game.cards.damagecards.TrojanHorse;
import game.cards.damagecards.Virus;
import game.cards.damagecards.Worm;
import game.cards.programmingcards.*;
import game.utilities.GameState;
import utilities.MessageHandler;

public abstract class Card {

    protected String name;
    protected final MessageHandler messageHandler = new MessageHandler();

    public String getName() {
        return this.name;
    }

    public static String[] toStringArray(Card[] cards) {

        String[] returnValue = new String[cards.length];

        for (int i = 0; i < cards.length; i++) {

            returnValue[i] = cards[i].getName();

        }

        return returnValue;

    }

    public static Card getCardByString(String card) {

        return switch (card) {
            case "Again" -> new Again();
            case "Backup" -> new Backup();
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
            default -> null;
        };

    }

    public abstract void action(Game game, GameState gameState, int PlayerID);

}
