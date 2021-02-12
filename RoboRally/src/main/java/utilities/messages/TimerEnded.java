package utilities.messages;

/**
 * The class TimerEnded is responsible for saving the players id who were not
 * done after the timer has ended.
 * 
 */
public class TimerEnded extends MessageBody {
  private final int[] playerIDs;

  /**
   * Constructor for initializing the players id array.
   * 
   * @param playerIDs
   *          the players id array
   */
  public TimerEnded(int[] playerIDs) {
    this.playerIDs = playerIDs;
  }

  /**
   * The method returns the players id array.
   * 
   * @return the players id array
   */
  public int[] getPlayerIDs() {
    return playerIDs;
  }

}
