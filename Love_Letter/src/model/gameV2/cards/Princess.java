package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * This class is responsible to represent, define, and implement the
 * logic of the Princess card in the game.
 *
 * @author Yashar
 */

public class Princess extends Card {
    /**
     * A Constructor to initialize the Princess' value, name and description.
     */
    public Princess() {

        value = 8;
        name = "Princess";
        description = "Lose if discarded.";

    }

    /**
     This method is responsible for applying the cards effects and kicking the player out of the round.
     * @param game   The current game.
     * @param player The player who played the Princess card.
     * @author Yashar
     */
    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "You're out!");
        game.getNextRoundActivePlayerList().add(0, player);
        game.getActivePlayerList().remove(player);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
