package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * Implementation of the card King
 *
 * @author Ehbal
 */
public class King extends Card {

    public King() {

        value = 6;
        name = "King";
        description = "Player trades hands with any other player.";

    }

    /**
     * This method is responsible for playing the King card
     *
     * @param game   the current game state
     * @param player the player who played the card
     */
    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "Choose a player with whom you want to change hands!");
        game.server.sendMessageToSingleUser(player.userName, "You can choose between :");

        for (Player player1 : game.getActivePlayerList()) {

            if (player != player1) {

                game.server.sendMessageToSingleUser(player.userName, player1.userName);

            }

        }
        game.server.sendMessageToSingleUser(player.userName, "Use the command !CHOOSEANOTHERPLAYER <choosenplayer> !");

    }

    /**
     * This method represents the logic behind playing the King card.
     *
     * @param game         the current game state
     * @param activePlayer the player who played the king
     * @param chosenPlayer the chosen player who has to change hands
     */

    public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

        if (chosenPlayer.isProtected()) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no effect!");

        } else {
            //Card trade
            Card activePlayerCard = activePlayer.getCards().remove(0);
            Card chosenPlayerCard = chosenPlayer.getCards().remove(0);

            activePlayer.addCard(chosenPlayerCard);
            chosenPlayer.addCard(activePlayerCard);

            game.server.sendMessageToSingleUser(activePlayer.userName, "Your new Card is a " + chosenPlayerCard.getName() + "!");
            game.server.sendMessageToSingleUser(chosenPlayer.userName, "Your new Card is a " + activePlayerCard.getName() + "!");

        }

        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
