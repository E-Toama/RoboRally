package game.utilities;

import game.cards.Card;
import utilities.MessageHandler;
import utilities.messages.DrawDamage;
import utilities.messages.PickDamage;

import java.util.ArrayList;
import java.util.List;

public class DrawDamageCardHandler {

    private MessageHandler messageHandler = new MessageHandler();
    private GameState gameState;

    public DrawDamageCardHandler(GameState gameState) {
        this.gameState = gameState;
    }

    private List<Card> drawnDamageCardsList;
    private int notPossibleCount;

    public synchronized void drawDamageCards(int playerID, String[] wantedDamageCards) {

        drawnDamageCardsList = new ArrayList<>();
        notPossibleCount = 0;

        for (String wantedDamageCard : wantedDamageCards) {

            drawDamageCard(getDamageCardDeck(wantedDamageCard));

        }

        if (notPossibleCount > 0) {

            pickDamage(playerID);

        } else {

            finishDamageCardDrawing(playerID);

        }


    }

    private void finishDamageCardDrawing(int playerID) {

        String[] damageCardsStrings = new String[drawnDamageCardsList.size()];

        for (int i = 0; i < drawnDamageCardsList.size(); i++) {

            damageCardsStrings[i] = drawnDamageCardsList.get(i).getName();
            System.out.println(drawnDamageCardsList.get(i).getName());
            gameState.playerMatHashMap.get(playerID).addDiscardedCard(drawnDamageCardsList.get(i));

        }

        String drawDamage = messageHandler.buildMessage("DrawDamage", new DrawDamage(playerID, damageCardsStrings));
        System.out.println(drawDamage);
        gameState.server.sendMessageToAllUsers(drawDamage);

    }

    private void drawDamageCard(List<Card> cards) {

        if (cards.size() > 0) {

            drawnDamageCardsList.add(cards.remove(0));

        } else {

            notPossibleCount = notPossibleCount + 1;

        }

    }

    private void pickDamage(int playerID) {

        String pickDamage = messageHandler.buildMessage("PickDamage", new PickDamage(notPossibleCount));
        gameState.server.sendMessageToSingleUser(pickDamage, playerID);

    }

    public synchronized void selectDamage(int playerID, String[] selectedDamageCards) {

        notPossibleCount = 0;

        for (String selectedDamageCard : selectedDamageCards) {

            drawDamageCard(getDamageCardDeck(selectedDamageCard));

        }

        if (notPossibleCount > 0) {

            pickDamage(playerID);

        } else {

            finishDamageCardDrawing(playerID);

        }

    }

    private List<Card> getDamageCardDeck(String damageCardType) {

        System.out.println("get damage card array: " + damageCardType);

        return switch (damageCardType) {

            case "Spam" -> gameState.spamCards;
            case "TrojanHorse" -> gameState.trojanHorseCards;
            case "Worm" -> gameState.wormCards;
            case "Virus" -> gameState.virusCards;
            default -> null;

        };

    }

}
