package utilities.messages;

/**
 * This class represents the player choice of the specific register.
 * 
 * @author
 */
public class CardSelected extends MessageBody {
  private final int playerID;
  private final int register;

  /**
   * Constructor for initializing the player id with the register number.
   * 
   * @param playerID
   *          the player id
   * @param register
   *          the number of the register
   */
  public CardSelected(int playerID, int register) {
    this.playerID = playerID;
    this.register = register;
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
   * The method returns the number of the register.
   * 
   * @return the number of the register
   */
  public int getRegister() {
    return register;
  }

}
