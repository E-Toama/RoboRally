package utilities.messages;

import game.player.Player;

/**
 * The Class PlayerAdded is for the confirmation that a new player was added.
 * 
 */
public class PlayerAdded extends MessageBody {

  private final Player player;

  /**
   * A Constructor for initializing the new player.
   * 
   * @param player
   *          is the player with his id, name and his robot figure selection
   */
  public PlayerAdded(Player player) {

    this.player = player;

  }

  /**
   * The method returns the player.
   * 
   * @return the player
   */
  public Player getPlayer() {
    return player;
  }
}
