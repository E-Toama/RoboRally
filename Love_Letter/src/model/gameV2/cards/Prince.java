package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * Implementation of the card Prince
 *
 * @author Ehbal
 */
public class Prince extends Card {

    public Prince() {

        value = 5;
        name = "Prince";
        description = "Player can choose any player to discard their hand and draw a new one.";

    }

    public void play(Game game, Player player) {

        if (checkIfAllOtherPlayersAreProtected(game, player)) {

            game.server.sendMessageToSingleUser(player.userName, "You are forced to choose yourself, because all other players are protected!");
            completePlay(game, player, player);

        } else {

            game.server.sendMessageToSingleUser(player.userName, "Choose a player who has to play a card and must draw a new one!");
            game.server.sendMessageToSingleUser(player.userName, "You can choose between: ");
            for (Player player1 : game.getActivePlayerList()) {

                game.server.sendMessageToSingleUser(player.userName, player1.userName);

            }
            game.server.sendMessageToSingleUser(player.userName, "Use the command !CHOOSEANYPLAYER <choosenplayer> !");

        }

    }

    public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

        if (chosenPlayer.isProtected()) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no effect!");

        } else {

            game.playCardWithoutEffect(chosenPlayer.getCards().remove(0), chosenPlayer);

        }

        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

    }

    public boolean checkIfAllOtherPlayersAreProtected(Game game, Player player) {

        for (Player otherPlayer : game.getActivePlayerList()) {

            if (!otherPlayer.userName.equals(player.userName)) {

                if (!otherPlayer.isProtected()) {

                    return false;

                }

            }

        }

        return true;

    }

}
