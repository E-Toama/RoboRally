package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * Represents the Guard Card and implements the action when it's played.
 * Extends Card.
 *
 * @author Dennis
 */
public class Guard extends Card {

    private static Player tmpChosenPlayer = null;

    public Guard() {

        value = 1;
        name = "Guard";
        description = "Guess a player's hand, if correct the player is out.";

    }

    /**
     * Asks the active Player, whose card he wants to guess.
     *
     * @param game      Current Game State
     * @param player     Active Player, who played the Card
     */
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

    /**
     * Checks if the chosen player is protected, has a Guard on his Hand (because then you can't guess right).
     * If both conditions are wrong, the player is asked to guess a card.
     *
     * @param game              Current Game State
     * @param activePlayer      Active Player
     * @param chosenPlayer      Chosen Player
     */
    public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

        if (chosenPlayer.isProtected()) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no Effekt!");

            game.getActivePlayerList().remove(activePlayer);
            game.getActivePlayerList().add(activePlayer);
            game.gameMove(game.getActivePlayerList().get(0));

        } else {

            game.server.sendMessageToSingleUser(activePlayer.userName, "Guess what card " + chosenPlayer.userName + " has on his Hand!");
            game.server.sendMessageToSingleUser(activePlayer.userName, "Use the command !GUESSCARD <cardvalue> !");
            tmpChosenPlayer = chosenPlayer;

        }

    }

    /**
     * Checks if the guess was right, if so it removes the chosen Player from the active Round.
     * Calls the next Player to make his move.
     *
     * @param game              Current Game State
     * @param activePlayer      Active Player
     * @param cardValue         Guessed Card Value
     */
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
