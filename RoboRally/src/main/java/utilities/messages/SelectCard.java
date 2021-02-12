package utilities.messages;

/**
 * This class represents the player selection of the card that he want to play.
 * 
 */
public class SelectCard extends MessageBody {
  private final String card;
  private final int register;

  /**
   * Constructor for initializing the card and the register where the card
   * should be put.
   * 
   * @param card
   *          the card that the player chose
   * @param register
   *          the register where the card should be put
   */
  public SelectCard(String card, int register) {
    this.card = card;
    this.register = register;
  }

  /**
   * The method returns the card.
   * 
   * @return the card
   */
  public String getCards() {
    return card;
  }

  /**
   * The method returns the register of the card.
   * 
   * @return the register
   */
  public int getRegister() {
    return register;
  }

}
