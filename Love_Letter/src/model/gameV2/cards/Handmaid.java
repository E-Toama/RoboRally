package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * Represents the the Handmaid Card and implements the action when it's played.
 * Extends Card.
 *
 * @author Dennis
 */
public class Handmaid extends Card {

    /**
     * A Constructor to initialize the Handmaid's value, name and description.
     */
    public Handmaid() {

        value = 4;
        name = "Handmaid";
        description = "You cannot be chosen until your next turn.";

    }

    /**
     * Set's the Protected Property of the Player to "true", when played (called).
     * Calls the next player to make his move.
     *
     * @param game      Current Game State
     * @param player    Active Player, who played the Card
     */
    public void play(Game game, Player player) {

        player.setProtected(true);
        game.server.sendMessageToSingleUser(player.userName, "You are protected for the next round!");
        game.getActivePlayerList().remove(player);
        game.getActivePlayerList().add(player);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
