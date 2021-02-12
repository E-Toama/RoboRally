package utilities.messages;

/**
 * The class SelectionFinished represents that the player put five cards on the
 * five registers and he is finished.
 * 
 */
public class SelectionFinished extends MessageBody {
  private final int playerID;

  /**
   * Constructor for initializing the player id.
   * 
   * @param playerID
   *          the player id
   */
  public SelectionFinished(int playerID) {
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
