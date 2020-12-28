package server.messages;

/**
 * 
 * @author
 */
public class CardSelected extends MessageBody {
  private final int playerID;
  private final int register;

  public CardSelected(int playerID, int register) {
    this.playerID = playerID;
    this.register = register;
  }

  public int getPlayerID() {
    return playerID;
  }

  public int getRegister() {
    return register;
  }

}
