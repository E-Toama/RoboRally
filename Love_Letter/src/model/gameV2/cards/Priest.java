package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

public class Priest extends Card {

    public Priest() {

        value = 2;
        name = "Priest";
        description = "Look at a player's hand.";

    }

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
