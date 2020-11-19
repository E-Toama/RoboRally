package model.game.cards;

public class Princess extends Card {
    int value = 8;
    String description = "You lose if you discard this card";
    @Override
    public int getValue() {
        return value;
    }
}
