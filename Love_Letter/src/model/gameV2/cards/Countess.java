package model.gameV2.cards;

import model.gameV2.Game;
import model.gameV2.Player;

/**
 * This class extends the Card class to implement the play method. It represents the Countess card.
 * 
 * @author Elias
 */
public class Countess extends Card {

  /**
   * A Constructor to initialize the Countess's value, name and description.
   */
  public Countess() {

    value = 7;
    name = "Countess";
    description = "Must be played if you have King or Prince in hand.";

  }

  /**
   * This method is responsible for playing the Countess card.
   * 
   * @param game   The current game state.
   * @param player The player who played the Countess card.
   */
  public void play(Game game, Player player) {

    game.getActivePlayerList().remove(player);
    game.getActivePlayerList().add(player);
    game.gameMove(game.getActivePlayerList().get(0));

  }

}
