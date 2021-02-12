package utilities.messages;

/**
 * This class is responsible for saving the cards that the player has already
 * chosen.
 * 
 */
public class CardsYouGotNow extends MessageBody {
  private final String[] cards;

  /**
   * Constructor for initializing the cards array.
   * 
   * @param cards
   *          the cards that the player already chose
   */
  public CardsYouGotNow(String[] cards) {
    this.cards = cards;
  }

  /**
   * The method returns the cards that the player already chose.
   * 
   * @return the cards that the player already chose
   */
  public String[] getCards() {
    return cards;
  }
}
