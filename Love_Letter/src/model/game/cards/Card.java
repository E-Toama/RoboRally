package model.game.cards;

public abstract class Card {
    int value;
    String description;

    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract int getValue();
}
