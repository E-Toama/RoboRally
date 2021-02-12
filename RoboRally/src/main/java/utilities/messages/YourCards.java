package utilities.messages;

/**
 * The YourCards class represents the nine cards of each player.
 */
public class YourCards extends MessageBody {
  private final String[] cards;
  private final int cardsInPile;

  /**
   * Constructor for initializing the nine cards and the cards that are in the
   * pile.
   * 
   * @param cards
   *          is the array of the nine cards that were drawn from the pile
   * @param cardsInPile
   *          the count of the cards in the pile
   */
  public YourCards(String[] cards, int cardsInPile) {
    this.cards = cards;
    this.cardsInPile = cardsInPile;
  }

  /**
   * The method returns the array of the nine drawn cards.
   * 
   * @return the drawn cards
   */
  public String[] getCards() {
    return cards;
  }

  /**
   * The method returns the count of the cards in the pile.
   * 
   * @return cards in the pile
   */
  public int getCardsInPile() {
    return cardsInPile;
  }

}
