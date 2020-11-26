package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * @author Dennis
 */
public class Guard extends Card {

    private static Player tmpChosenPlayer = null;

    public Guard() {

        value = 1;
        name = "Guard";
        description = "Guess a player's hand, if correct the player is out.";

    }

    public void play(Game game, Player player) {

        game.server.sendMessageToSingleUser(player.userName, "Choose a player whose card you want to guess!");
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

            game.getActivePlayerList().remove(activePlayer);
            game.getActivePlayerList().add(activePlayer);
            game.gameMove(game.getActivePlayerList().get(0));

        } else if (chosenPlayer.getCards().get(0).getValue() == 1) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player also has a Guard! Your card has mo Effekt");

            game.getActivePlayerList().remove(activePlayer);
            game.getActivePlayerList().add(activePlayer);
            game.gameMove(game.getActivePlayerList().get(0));

        } else {

            game.server.sendMessageToSingleUser(activePlayer.userName, "Guess what card " + chosenPlayer.userName + " has on his Hand!");
            game.server.sendMessageToSingleUser(activePlayer.userName, "Use the command !GUESSCARD <cardvalue> !");
            tmpChosenPlayer = chosenPlayer;

        }

    }

    public void guessCard(Game game, Player activePlayer, int cardValue) {

        Player chosenPlayer = tmpChosenPlayer;
        tmpChosenPlayer = null;

        if (chosenPlayer.getCards().get(0).getValue() == cardValue) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "You guessed right!");
            game.server.sendMessageToSingleUser(chosenPlayer.userName, "You're out!");
            game.server.sendMessageToAllUsers(chosenPlayer.userName + "has played the " + chosenPlayer.getCards().get(0).getName() + " without Effekt!");
            game.discardCard(chosenPlayer.getCards().remove(0));
            game.getActivePlayerList().remove(chosenPlayer);
            game.getNextRoundActivePlayerList().add(0, chosenPlayer);

        } else {

            game.server.sendMessageToSingleUser(activePlayer.userName, "You guessed wrong!");

        }
        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

    }

}
