package server.messages;

/**
 * 
 * @author
 */
public class Reboot extends MessageBody {
  private final int playerID;

  public Reboot(int playerID) {
    this.playerID = playerID;
  }

  public int getPlayerID() {
    return playerID;
  }

}