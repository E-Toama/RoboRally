package utilities.messages;

/**
 * 
 * @author
 */
public class GameWon extends MessageBody {
  private final int playerID;

  public GameWon(int playerID) {
    this.playerID = playerID;
  }

  public int getPlayerID() {
    return playerID;
  }

}
