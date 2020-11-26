package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

public class Handmaid extends Card {

    public Handmaid() {

        value = 4;
        name = "Handmaid";
        description = "You cannot be chosen until your next turn.";

    }

    public void play(Game game, Player player) {

        player.setProtected(true);
        game.server.sendMessageToSingleUser(player.userName, "You are protected for the next round!");
        game.getActivePlayerList().remove(player);
        game.getActivePlayerList().add(player);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
