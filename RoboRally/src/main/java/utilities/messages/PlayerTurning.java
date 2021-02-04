package utilities.messages;

/**
 * This class is responsible for the direction of the robot rotation.
 * 
 * @author
 */
public class PlayerTurning extends MessageBody {
  private final int playerID;
  private final String direction;

  /**
   * Constructor for initializing the player id with its direction.
   * 
   * @param playerID
   *          the player id
   * @param direction
   *          the direction in which the robot should be rotating
   */
  public PlayerTurning(int playerID, String direction) {
    this.playerID = playerID;
    this.direction = direction;
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
   * The method returns the direction of the robot.
   * 
   * @return the direction in which the robot should rotate
   */
  public String getDirection() {
    return direction;
  }

}
