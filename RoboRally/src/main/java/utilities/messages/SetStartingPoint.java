package utilities.messages;

/**
 * This class is for setting the starting position of the player.
 * 
 */
public class SetStartingPoint extends MessageBody {
  private final int position;

  /**
   * Constructor for initializing the position.
   * 
   * @param position
   *          is the position on the game board
   */
  public SetStartingPoint(int position) {
    this.position = position;
  }

  /**
   * The method returns the starting position.
   * 
   * @return the position
   */
  public int getPosition() {
    return position;
  }

}
