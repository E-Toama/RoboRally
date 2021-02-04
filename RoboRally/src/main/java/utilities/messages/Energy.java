package utilities.messages;

/**
 * This class represents the energy cubes each player has.
 * 
 * @author
 */
public class Energy extends MessageBody {
  private final int playerID;
  private final int count;

  /**
   * Constructor for initializing the player id with the number of energy cubes
   * he has.
   * 
   * @param playerID
   *          the player id
   * @param count
   *          the number of energy cubes
   */
  public Energy(int playerID, int count) {
    this.playerID = playerID;
    this.count = count;
  }

  /**
   * The method returns the player id.
   * 
   * @return the player id
   */
  public int getPlayerID() {
    return playerID;
  }

  /**
   * The method returns the number of energy cubes.
   * 
   * @return the number of energy cubes
   */
  public int getCount() {
    return count;
  }

}
