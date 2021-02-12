package utilities.messages;

/**
 * This class represents for each player the number of checkpoints he reached.
 * 
 */
public class CheckpointReached extends MessageBody {
  private final int playerID;
  private final int number;

  /**
   * Constructor for initializing the player id with the number of the
   * checkpoints reached by him.
   * 
   * @param playerID
   *          the player id
   * @param number
   *          the number of checkpoints reached
   */
  public CheckpointReached(int playerID, int number) {
    this.playerID = playerID;
    this.number = number;
  }

  /**
   * The method returns the number of checkpoints reached.
   * 
   * @return number of checkpoints reached
   */
  public int getNumber() {
    return number;
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
