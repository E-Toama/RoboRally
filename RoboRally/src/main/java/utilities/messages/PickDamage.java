package utilities.messages;

/**
 * This class represents the pickdamage message that the server sends to the
 * client.
 * 
 */
public class PickDamage extends MessageBody {
  private final int count;

  /**
   * Constructor for initializing the number of damage cards the player should
   * draw.
   * 
   * @param count
   *          the number of damage cards the player should draw
   */
  public PickDamage(int count) {
    this.count = count;
  }

  /**
   * The method returns the number of damage cards the player should draw.
   * 
   * @return the number of damage cards.
   */
  public int getCount() {
    return count;
  }
}
