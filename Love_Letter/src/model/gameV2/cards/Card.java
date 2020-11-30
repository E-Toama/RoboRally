package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * Represents a general card, its functions and its properties.
 *
 * author Dennis
 */
public abstract class Card {

    protected int value;
    protected String name;
    protected String description;

    /**
     * Get the card value (1-8)
     * @return The card value
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the name of the card (e.g. "Princess")
     * @return The card name
     */
    public String getName() {
        return name;
    }

    /**
     * Get a short description of the Card's action
     * @return The description of the card
     */
    public String getDescription() {
        return description;
    }

    /**
     * Abstract play method for all cards
     * @param game The current game
     * @param player The player who played the card
     */
    public abstract void play(Game game, Player player);

}
