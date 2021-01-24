package utilities.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class YourCards extends MessageBody {
  // TODO programming cards only
  private final String[] cards;
  private final int cardsInPile;

  public YourCards(String[] cards, int cardsInPile) {
    this.cards = cards;
    this.cardsInPile = cardsInPile;
  }

  public String[] getCards() {
    return cards;
  }

  public int getCardsInPile() {
    return cardsInPile;
  }
  
}
