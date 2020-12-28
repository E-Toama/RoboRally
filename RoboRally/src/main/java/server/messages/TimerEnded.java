package server.messages;

/**
 * 
 * @author
 */
public class TimerEnded extends MessageBody {
  private final int[] playerIDs;

  public TimerEnded(int[] playerIDs) {
    this.playerIDs = playerIDs;
  }

  public int[] getPlayerIDs() {
    return playerIDs;
  }

}
