package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * @author Elias
 */
public class Baron extends Card {

    public Baron() {

        value = 3;
        name = "Baron";
        description = "Compare hands with another player, the one with the lower value is out.";

    }

    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "Choose a player with whom you want to compare hands!");
        game.server.sendMessageToSingleUser(player.userName, "You can choose between :");
        for (Player player1 : game.getActivePlayerList()) {

            if(player != player1) {

                game.server.sendMessageToSingleUser(player.userName, player1.userName);

            }

        }
        game.server.sendMessageToSingleUser(player.userName, "Use the command !CHOOSEANOTHERPLAYER <choosenplayer> !");

    }

    public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

        if (chosenPlayer.isProtected()) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no effect!");

            game.getActivePlayerList().remove(activePlayer);
            game.getActivePlayerList().add(activePlayer);
            game.gameMove(game.getActivePlayerList().get(0));

        } else {

            if (activePlayer.getCards().get(0).getValue() > chosenPlayer.getCards().get(0).getValue()) {

                game.server.sendMessageToAllUsers(chosenPlayer.userName + " has played the " + chosenPlayer.getCards().get(0).getName() + " without effect and is out of the game!");
                game.discardCard(chosenPlayer.getCards().remove(0));
                game.getActivePlayerList().remove(chosenPlayer);
                game.getNextRoundActivePlayerList().add(0, chosenPlayer);

                game.getActivePlayerList().remove(activePlayer);
                game.getActivePlayerList().add(activePlayer);
                game.gameMove(game.getActivePlayerList().get(0));

            } else if (activePlayer.getCards().get(0).getValue() < chosenPlayer.getCards().get(0).getValue()) {

                game.server.sendMessageToAllUsers(activePlayer.userName + " has played the " + activePlayer.getCards().get(0).getName() + " without effect and is out of the game!");
                game.discardCard(activePlayer.getCards().remove(0));

                game.getActivePlayerList().remove(activePlayer);
                game.getNextRoundActivePlayerList().add(0, activePlayer);
                game.gameMove(game.getActivePlayerList().get(0));

            } else {

                game.getActivePlayerList().remove(activePlayer);
                game.getActivePlayerList().add(activePlayer);
                game.gameMove(game.getActivePlayerList().get(0));

            }

        }

    }

}
