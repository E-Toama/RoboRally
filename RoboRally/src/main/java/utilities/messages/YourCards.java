package utilities.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class YourCards extends MessageBody {
  // TODO programming cards only
  private final Card[] cards;
  private final int cardsInPile;

  public YourCards(Card[] cards, int cardsInPile) {
    this.cards = cards;
    this.cardsInPile = cardsInPile;
  }

  public Card[] getCards() {
    return cards;
  }

  public int getCardsInPile() {
    return cardsInPile;
  }
  
}
