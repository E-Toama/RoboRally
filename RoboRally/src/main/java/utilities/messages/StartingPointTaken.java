package utilities.messages;

/**
 * 
 * @author
 */
public class StartingPointTaken extends MessageBody {
  private final int playerID;
  private final int position;

  public StartingPointTaken(int playerID, int position) {
    this.playerID = playerID;
    this.position = position;
  }

  public int getPlayerID() {
    return playerID;
  }

  public int getPosition() {
    return position;
  }

}
