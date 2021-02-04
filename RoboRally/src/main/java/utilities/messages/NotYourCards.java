package utilities.messages;

/**
 * This class is for representing the cards of the other players.
 * 
 * @author
 */
public class NotYourCards extends MessageBody {
  private final int playerID;
  private final int cardsInHand;
  private final int cardsInPile;

  /**
   * Constructor for initializing the player id, number of drawn cards and the
   * cards in the pile.
   * 
   * @param playerID
   *          the player id
   * @param cards
   *          the number of cards drawn
   * @param cardsInPile
   *          the number of cards in the pile
   */
  public NotYourCards(int playerID, int cards, int cardsInPile) {
    this.playerID = playerID;
    this.cardsInHand = cards;
    this.cardsInPile = cardsInPile;
  }

  /**
   * The method returns the number of the cards drawn.
   * 
   * @return number of the cards drawn
   */
  public int getCardsInHand() {
    return cardsInHand;
  }

  /**
   * The method returns the number of the cards in the pile.
   * 
   * @return number of cards in the pile
   */
  public int getCardsInPile() {
    return cardsInPile;
  }

  /**
   * The method returns the player id.
   * 
   * @return the player id
   */
  public int getPlayerID() {
    return playerID;
  }

}
