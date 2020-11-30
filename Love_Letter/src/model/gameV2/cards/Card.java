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

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void play(Game game, Player player);

}
