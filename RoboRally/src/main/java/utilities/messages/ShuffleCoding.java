package utilities.messages;

/**
 * 
 * @author
 */
public class ShuffleCoding extends MessageBody {
  private final int playerID;

  public ShuffleCoding(int playerID) {
    this.playerID = playerID;
  }

  public int getPlayerID() {
    return playerID;
  }

}
