package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * This class is responsible to represent, define, and implement the
 * logic of the Priest card in the game.
 *
 * @author Yashar
 */
public class Priest extends Card {
    /**
     * A Constructor to initialize the Priest's value, name and description.
     */
    public Priest() {

        value = 2;
        name = "Priest";
        description = "Look at a player's hand.";

    }
    /**
     * This method is responsible for playing the Priest Card.
     * @param game   The current game.
     * @param player The player who played the Princess card.
     * @author Yashar
     */
    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "Choose a player whose hand you want to see!");
        game.server.sendMessageToSingleUser(player.userName, "You can choose between :");
        for (Player player1 : game.getActivePlayerList()) {

            if(player != player1) {

                game.server.sendMessageToSingleUser(player.userName, player1.userName);

            }

        }
        game.server.sendMessageToSingleUser(player.userName, "Use the command !CHOOSEANOTHERPLAYER <choosenplayer> !");

    }
    /**
     * This method is responsible for applying the effects of the Priest card.
     * @param game         The current game state.
     * @param activePlayer The current player.
     * @param chosenPlayer The player chosen by the current player.


     */
    public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

        if (chosenPlayer.isProtected()) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no Effekt!");

        } else {

            game.server.sendMessageToSingleUser(activePlayer.userName, chosenPlayer.userName + " has a " + chosenPlayer.getCards().get(0).getName() + " on his Hand!");

        }

        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
