package utilities.messages;

/**
 * This class is responsible for saving the player id that has to reboot.
 * 
 */
public class Reboot extends MessageBody {
  private final int playerID;

  /**
   * Constructor for initializing the player id.
   * 
   * @param playerID
   *          the player id
   */
  public Reboot(int playerID) {
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