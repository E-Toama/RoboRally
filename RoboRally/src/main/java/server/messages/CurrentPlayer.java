package server.messages;

/**
 * 
 * @author
 */
public class CurrentPlayer extends MessageBody {
  private final int playerID;

  public CurrentPlayer(int playerID) {
    this.playerID = playerID;
  }

  public int getPlayerID() {
    return playerID;
  }

}
