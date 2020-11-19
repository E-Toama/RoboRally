package model.game.cards;

public class Priest extends Card {
    int value = 2;
    String description = "Look at another player's card";
    @Override
    public int getValue() {
        return value;
    }
}
