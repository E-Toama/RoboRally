package utilities.messages;

/**
 * This class is responsible for saving the player id which did not fill all of
 * his five registers before the timer ended.
 * 
 * @author
 */
public class DiscardHand extends MessageBody {
  private final int playerID;

  /**
   * Constructor for initializing the player id.
   * 
   * @param playerID
   *          the player id
   */
  public DiscardHand(int playerID) {
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
