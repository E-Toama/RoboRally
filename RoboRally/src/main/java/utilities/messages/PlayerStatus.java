package utilities.messages;

/**
 * The class PlayerStatus is for saving the player id and his status.
 * 
 * @author
 */
public class PlayerStatus extends MessageBody {

  private final int playerID;
  private final Boolean ready;

  /**
   * A Constructor for initializing the player id and his status.
   * 
   * @param playerID
   *          the player id
   * @param ready
   *          the player status
   */
  public PlayerStatus(int playerID, Boolean ready) {

    this.playerID = playerID;
    this.ready = ready;

  }

  /**
   * The method returns the player id.
   * 
   * @return the player id
   */
  public int getPlayerID() {
    return playerID;
  }

  /**
   * The method returns the player status.
   * 
   * @return the player status
   */
  public Boolean getReady() {
    return ready;
  }
}
