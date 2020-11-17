package model.game.cards;

public class Guard extends Card {
    int value = 1;
    String description = "Guess card";

    @Override
    public int getValue() {
        return value;
    }
}
