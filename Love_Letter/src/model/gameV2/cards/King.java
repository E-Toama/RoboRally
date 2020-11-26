package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

public class King extends Card{

    public King() {

        value = 6;
        name = "King";
        description = "Trade hands with another player.";

    }

    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "Choose a player with whom you want to change hands!");
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

            Card activePlayerCard = activePlayer.getCards().remove(0);
            Card chosenPlayerCard = chosenPlayer.getCards().remove(0);

            activePlayer.addCard(chosenPlayerCard);
            chosenPlayer.addCard(activePlayerCard);

            game.server.sendMessageToSingleUser(activePlayer.userName, "Your new Card is a " + chosenPlayerCard.getDescription() + "!");
            game.server.sendMessageToSingleUser(chosenPlayer.userName, "Your new Card is a " + activePlayerCard.getDescription() + "!");

        }

        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
