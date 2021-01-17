package utilities.messages;

/**
 * 
 * @author
 */
public class DiscardHand extends MessageBody {
  private final int playerID;

  public DiscardHand(int playerID) {
    this.playerID = playerID;
  }

  public int getPlayerID() {
    return playerID;
  }

}
