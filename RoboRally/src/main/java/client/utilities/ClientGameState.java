package client.utilities;

import game.cards.ActiveCard;
import game.gameboard.GameBoard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ClientGameState {

    private int ActivePhase = 0;
    private String selectedMap = "";
    private GameBoard gameBoard;



    public String getSelectedMap() {
        return selectedMap;
    }

    public void setSelectedMap(String selectedMap) {
        this.selectedMap = selectedMap;
    }

    // Initial count of DamageCards according to Rules / FAQ
    private int spamCardPile = 38;
    private int virusCardPile = 18;
    private int trojanCardPile = 12;
    private int wormCardPile = 6;

    private HashMap<String, Integer> availableDamageCards;

    public ClientGameState() {

        initializeDamageCardCount();

    }

    private void initializeDamageCardCount() {
        availableDamageCards = new HashMap<>();
        availableDamageCards.put("Spam", spamCardPile);
        availableDamageCards.put("Virus", virusCardPile);
        availableDamageCards.put("TrojanHorse", trojanCardPile);
        availableDamageCards.put("Worm", wormCardPile);
    }

    public int getActivePhase() {
        return ActivePhase;
    }

    public void setActivePhase(int activePhase) {
        ActivePhase = activePhase;
    }

    public LinkedList<String> getAvailableDamageCards() {
        LinkedList<String> availableCards = new LinkedList<>();
        for (Map.Entry<String, Integer> cardPile : availableDamageCards.entrySet()) {
            if (cardPile.getValue() > 0 && !cardPile.getKey().equals("Spam")) {
                availableCards.add(cardPile.getKey());
            }
        }
        return availableCards;
    }


    public void decreaseDamageCardCount(String[] damageCards) {
        for (String card : damageCards) {
            switch (card) {
                case "Spam":
                    if (spamCardPile > 0) {
                        spamCardPile--;
                    }
                    availableDamageCards.replace("Spam", spamCardPile);
                    break;
                case "Virus":
                    if (virusCardPile > 0) {
                        virusCardPile--;
                    }
                    availableDamageCards.replace("Virus", virusCardPile);
                    break;
                case "TrojanHorse":
                    if (trojanCardPile > 0) {
                        trojanCardPile--;
                    }
                    availableDamageCards.replace("TrojanHorse", trojanCardPile);
                    break;
                case "Worm":
                    if (wormCardPile > 0) {
                        wormCardPile--;
                    }
                    availableDamageCards.replace("Worm", wormCardPile);
                    break;
                default:
                    break;
            }
        }
    }

    public void increaseDamageCardCount(ActiveCard[] activeCards) {
        for (ActiveCard card : activeCards) {
            switch (card.getCard()) {
                case "Spam":
                    spamCardPile++;
                    availableDamageCards.replace("Spam", spamCardPile);
                    break;
                case "Virus":
                    virusCardPile++;
                    availableDamageCards.replace("Virus", virusCardPile);
                    break;
                case "TrojanHorse":
                    trojanCardPile++;
                    availableDamageCards.replace("TrojanHorse", trojanCardPile);
                    break;
                case "Worm":
                    wormCardPile++;
                    availableDamageCards.replace("Worm", wormCardPile);
                    break;
                default:
                    break;
            }
        }

    }

}
