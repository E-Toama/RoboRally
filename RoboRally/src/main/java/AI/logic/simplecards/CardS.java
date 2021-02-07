package AI.logic.simplecards;

import AI.logic.AIGameState;
import AI.logic.simplecards.simpledamagecards.SpamS;
import AI.logic.simplecards.simpledamagecards.TrojanHorseS;
import AI.logic.simplecards.simpledamagecards.VirusS;
import AI.logic.simplecards.simpledamagecards.WormS;
import AI.logic.simplecards.simpleprogrammingcards.*;
import utilities.MessageHandler;

public abstract class CardS {

    protected String name;
    protected final MessageHandler messageHandler = new MessageHandler();

    public String getName() {
        return this.name;
    }

    public abstract void action(AIGameState aiGameState, int currentRegisterNumber);

    public static CardS getCardByString(String card) {

        return switch (card) {
            case "Again" -> new AgainS();
            case "BackUp" -> new BackUpS();
            case "LeftTurn" -> new LeftTurnS();
            case "MoveOne" -> new MoveOneS();
            case "MoveTwo" -> new MoveTwoS();
            case "MoveThree" -> new MoveThreeS();
            case "PowerUp" -> new PowerUpS();
            case "RightTurn" -> new RightTurnS();
            case "Spam" -> new SpamS();
            case "TrojanHorse" -> new TrojanHorseS();
            case "Virus" -> new VirusS();
            case "Worm" -> new WormS();
            case "U-Turn" -> new UTurnS();
            default -> null;
        };

    }

    public static String[] toStringArray(CardS[] cards) {

        String[] returnValue = new String[cards.length];

        for (int i = 0; i < cards.length; i++) {

            returnValue[i] = cards[i].getName();

        }

        return returnValue;

    }

}
