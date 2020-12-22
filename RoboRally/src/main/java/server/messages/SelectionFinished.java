package server.messages;

/**
 * 
 * @author
 */
public class SelectionFinished extends MessageBody {
  private final int playerID;

  public SelectionFinished(int playerID) {
    this.playerID = playerID;
  }

  public int getPlayerID() {
    return playerID;
  }

}
