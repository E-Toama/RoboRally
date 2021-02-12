package utilities.messages;

/**
 * This class is for saving each player a starting position that he picked.
 * 
 */
public class StartingPointTaken extends MessageBody {
  private final int playerID;
  private final int position;

  /**
   * Constructor for initializing the player id and his starting position.
   * 
   * @param playerID
   *          the player id
   * @param position
   *          the starting position
   */
  public StartingPointTaken(int playerID, int position) {
    this.playerID = playerID;
    this.position = position;
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
   * The method returns the starting position of the player.
   * 
   * @return the starting position
   */
  public int getPosition() {
    return position;
  }

}
