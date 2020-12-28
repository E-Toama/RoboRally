package server.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class CardsYouGotNow extends MessageBody {
  // TODO add cards list (register cards only)
  private final Card[] cards;

  public CardsYouGotNow(Card[] cards) {
    this.cards = cards;
  }

  public Card[] getCards() {
    return cards;
  }
}
