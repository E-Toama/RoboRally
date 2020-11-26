package model.game.cards;

public class Baron extends Card {

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "Compare hands with another player, the one with the lower number is out.";
    }

}
