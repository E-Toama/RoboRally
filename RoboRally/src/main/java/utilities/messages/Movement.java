package utilities.messages;

/**
 * This class is responsible for the movement of each player robot. The movement
 * happens between the fields of the game board without rotation.
 * 
 */
public class Movement extends MessageBody {
  private final int playerID;
  private final int to;

  /**
   * Constructor for initializing the player id and the destination position of
   * his robot.
   * 
   * @param playerID
   *          the player id
   * @param to
   *          the destination position of the player robot
   */
  public Movement(int playerID, int to) {
    this.playerID = playerID;
    this.to = to;
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
   * The method returns the destination position of the robot.
   * 
   * @return the destination position of the robot.
   */
  public int getTo() {
    return to;
  }

}
