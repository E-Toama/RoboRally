package model.game.cards;

/**
 * Represents a playing card in LoveLetter,
 * provides toString()-method that returns the simple Class name, e.g. "Princess"
 *
 * @author Elias
 */
public abstract class Card {

    public abstract int getValue();

    public abstract String getDescription();

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
