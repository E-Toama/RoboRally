package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * This class extends the card class to implement the play method and to represent and define the
 * logic of the baron card in the game.
 * 
 * @author Elias
 */
public class Baron extends Card {

  /**
   * A Constructor to initialize the Baron's value, name and description.
   */
  public Baron() {

    value = 3;
    name = "Baron";
    description = "Compare hands with another player, the one with the lower value is out.";

  }

  /**
   * This method is responsible for playing the Baron card.
   * 
   * @param game   The current game state.
   * @param player The player who played the Baron card.
   */
  public void play(Game game, Player player) {

    game.server.sendMessageToSingleUser(player.userName,
        "Choose a player with whom you want to compare hands!");
    game.server.sendMessageToSingleUser(player.userName, "You can choose between :");
    for (Player player1 : game.getActivePlayerList()) {

      if (player != player1) {

        game.server.sendMessageToSingleUser(player.userName, player1.userName);

      }

    }
    game.server.sendMessageToSingleUser(player.userName,
        "Use the command !CHOOSEANOTHERPLAYER <choosenplayer> !");

  }

  /**
   * This method represents the logic behind playing the Baron card.
   * 
   * @param game         The current game state.
   * @param activePlayer The current player.
   * @param chosenPlayer The choice of the current player.
   */
  public void completePlay(Game game, Player activePlayer, Player chosenPlayer) {

            game.server.sendMessageToSingleUser(activePlayer.userName, "The chosen player is protected, your card has no effect!");

      game.server.sendMessageToSingleUser(activePlayer.userName,
          "The chosen player is protected, your card has no effect!");

      game.getActivePlayerList().remove(activePlayer);
      game.getActivePlayerList().add(activePlayer);
      game.gameMove(game.getActivePlayerList().get(0));

    } else {

                game.server.sendMessageToAllUsers(chosenPlayer.userName + " has played the " + chosenPlayer.getCards().get(0).getName() + " without effect and is out of the game!");
                game.discardCard(chosenPlayer.getCards().remove(0));
                game.getActivePlayerList().remove(chosenPlayer);
                game.getNextRoundActivePlayerList().add(0, chosenPlayer);

        game.server.sendMessageToAllUsers(chosenPlayer.userName + " has played the "
            + chosenPlayer.getCards().get(0).getDescription() + " without effect!");
        game.discardCard(chosenPlayer.getCards().remove(0));
        game.getActivePlayerList().remove(chosenPlayer);
        game.getNextRoundActivePlayerList().add(0, chosenPlayer);

        game.getActivePlayerList().remove(activePlayer);
        game.getActivePlayerList().add(activePlayer);
        game.gameMove(game.getActivePlayerList().get(0));

                game.server.sendMessageToAllUsers(activePlayer.userName + " has played the " + activePlayer.getCards().get(0).getName() + " without effect and is out of the game!");
                game.discardCard(activePlayer.getCards().remove(0));

        game.server.sendMessageToAllUsers(activePlayer.userName + " has played the "
            + activePlayer.getCards().get(0).getDescription() + " without effect!");
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
