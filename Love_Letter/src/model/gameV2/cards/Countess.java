package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

public class Countess extends Card {

    public Countess() {

        value = 7;
        name = "Countess";
        description = "Must be played if you have King or Prince in hand.";

    }

    public void play(Game game, Player player) {

        game.getActivePlayerList().remove(player);
        game.getActivePlayerList().add(player);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
