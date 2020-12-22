package server.messages;

/**
 * 
 * @author
 */
public class PlayerTurning extends MessageBody {
  private final int playerID;
  private final String direction;

  public PlayerTurning(int playerID, String direction) {
    this.playerID = playerID;
    this.direction = direction;
  }

  public int getPlayerID() {
    return playerID;
  }

  public String getDirection() {
    return direction;
  }

}
