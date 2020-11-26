package model.game.cards;

public class Guard extends Card {

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Guess a player's hand, if correct the player is out.";
    }

}
