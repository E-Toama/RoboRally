package utilities.messages;

/**
 * 
 * @author
 */
public class NotYourCards extends MessageBody {
  private final int playerID;
  private final int cardsInHand;
  private final int cardsInPile;

  public NotYourCards(int playerID, int cards, int cardsInPile) {
    this.playerID = playerID;
    this.cardsInHand = cards;
    this.cardsInPile = cardsInPile;
  }

  public int getCardsInHand() {
    return cardsInHand;
  }

  public int getCardsInPile() {
    return cardsInPile;
  }

  public int getPlayerID() {
    return playerID;
  }

}
