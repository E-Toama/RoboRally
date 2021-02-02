package utilities.messages;

/**
 * The Welcome class represents that the Connection was successful between the
 * server and the client and saves a player ID.
 * 
 * @author
 */
public class Welcome extends MessageBody {

  private final int playerID;

  /**
   * A Constructor to initialize the player id.
   * 
   * @param id
   *          is the given player ID
   */
  public Welcome(int id) {

    this.playerID = id;

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
