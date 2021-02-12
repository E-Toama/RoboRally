package utilities.messages;

/**
 * This class is responsible for saving the damage cards that the player chose.
 * 
 */
public class SelectDamage extends MessageBody {

  private final String[] cards;

  /**
   * Constructor for initializing the choice of the damage cards of the player.
   * 
   * @param cards
   *          the list of damage cards
   */
  public SelectDamage(String[] cards) {
    this.cards = cards;
  }

  /**
   * This method returns the list of damage cards.
   * 
   * @return the list of damage cards
   */
  public String[] getCards() {
    return cards;
  }

}
