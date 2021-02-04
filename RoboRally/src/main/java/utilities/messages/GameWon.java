package utilities.messages;

/**
 * This class is responsible for saving the player who reached all of the
 * checkpoints in the game, meaning he won it.
 * 
 * @author
 */
public class GameWon extends MessageBody {
  private final int playerID;

  /**
   * Constructor for initializing the player id.
   * 
   * @param playerID
   *          the player id
   */
  public GameWon(int playerID) {
    this.playerID = playerID;
  }

  /**
   * The method returns the player id.
   * 
   * @return the player id
   */
  public int getPlayerID() {
    return playerID;
  }

}
