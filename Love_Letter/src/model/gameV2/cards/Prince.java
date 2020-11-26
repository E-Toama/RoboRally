package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

public class Prince extends Card {

    public Prince() {

        value = 5;
        name = "Prince";
        description = "Choose a player. They discard their hand and draw a new card.";

    }

    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "Choose a player who has to play his card and must draw a new one!");
        game.server.sendMessageToSingleUser(player.userName, "You can choose between: ");
        for (Player player1 : game.getActivePlayerList()) {

            game.server.sendMessageToSingleUser(player.userName, player1.userName);

        }
        game.server.sendMessageToSingleUser(player.userName, "Use the command !CHOOSEANYPLAYER <choosenplayer> !");

    }

    public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

        if (chosenPlayer.isProtected()) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no Effekt!");

        } else {

            game.playCardWithoutEffect(chosenPlayer.getCards().remove(0), chosenPlayer);

        }

        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
