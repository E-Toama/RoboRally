package game.utilities;

import game.cards.Card;
import utilities.MessageHandler;
import utilities.messages.DrawDamage;
import utilities.messages.PickDamage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is represents the draw damage cards process.
 * 
 */
public class DrawDamageCardHandler {

    private MessageHandler messageHandler = new MessageHandler();
    private GameState gameState;

    /**
     * Constructor for initializing the current game state.
     * 
     * @param gameState
     *          the current game state
     */
    public DrawDamageCardHandler(GameState gameState) {
        this.gameState = gameState;
    }

    private List<Card> drawnDamageCardsList;
    private int notPossibleCount;

    /**
     * This method is for drawing the damage cards that should be drawn if damage is received.
     * 
     * @param playerID
     *          the player id
     *          
     * @param wantedDamageCards
     *          the damage cards that should be drawn
     */
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
            gameState.playerMatHashMap.get(playerID).addDiscardedCard(drawnDamageCardsList.get(i));

        }

        String drawDamage = messageHandler.buildMessage("DrawDamage", new DrawDamage(playerID, damageCardsStrings));
        gameState.server.sendMessageToAllUsers(drawDamage);

    }

    private void drawDamageCard(List<Card> cards) {

        if (cards.size() > 0) {

            if (cards.get(0) != null) {

                drawnDamageCardsList.add(cards.remove(0));

            }

        } else {

            notPossibleCount = notPossibleCount + 1;

        }

    }

    private void pickDamage(int playerID) {

        String pickDamage = messageHandler.buildMessage("PickDamage", new PickDamage(notPossibleCount));
        gameState.server.sendMessageToSingleUser(pickDamage, playerID);

    }

    /**
     * This method is for handling the players choosing of the damage cards.
     * 
     * @param playerID 
     *          the player id
     * @param selectedDamageCards
     *          the list of the selected damage cards
     */
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

    /**
     * This method returns a list of the damage cards deck.
     * 
     * @param damageCardType 
     *              the name of the damage card
     *              
     * @return damage cards deck
     */
    private List<Card> getDamageCardDeck(String damageCardType) {

        return switch (damageCardType) {

            case "Spam" -> gameState.spamCards;
            case "TrojanHorse" -> gameState.trojanHorseCards;
            case "Worm" -> gameState.wormCards;
            case "Virus" -> gameState.virusCards;
            default -> null;

        };

    }

}
