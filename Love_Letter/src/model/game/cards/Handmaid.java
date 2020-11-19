package model.game.cards;

public class Handmaid extends Card {
    int value = 4;
    String description = "Protected for the next round";

    @Override
    public int getValue() {
        return value;
    }
}
