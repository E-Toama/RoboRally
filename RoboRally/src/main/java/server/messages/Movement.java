package server.messages;

/**
 * 
 * @author
 */
public class Movement extends MessageBody {
  private final int playerID;
  private final int to;

  public Movement(int playerID, int to) {
    this.playerID = playerID;
    this.to = to;
  }

  public int getPlayerID() {
    return playerID;
  }

  public int getTo() {
    return to;
  }

}
