package utilities.messages;

/**
 * 
 * @author
 */
public class CheckpointReached extends MessageBody {
  private final int playerID;
  private final int number;

  public CheckpointReached(int playerID, int number) {
    this.playerID = playerID;
    this.number = number;
  }

  public int getNumber() {
    return number;
  }

  public int getPlayerID() {
    return playerID;
  }

}
