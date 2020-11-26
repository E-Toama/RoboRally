package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * @author Yashar
 */
public class Princess extends Card {

    public Princess() {

        value = 8;
        name = "Princess";
        description = "Lose if discarded.";

    }

    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "You're out!");
        game.getNextRoundActivePlayerList().add(0, player);
        game.getActivePlayerList().remove(player);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
