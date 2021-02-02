package utilities.messages;

/**
 * This class represents that the player id should shuffle his deck.
 * 
 * @author
 */
public class ShuffleCoding extends MessageBody {
  private final int playerID;

  /**
   * Constructor for initializing the player id.
   * 
   * @param playerID
   *          the player id
   */
  public ShuffleCoding(int playerID) {
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
