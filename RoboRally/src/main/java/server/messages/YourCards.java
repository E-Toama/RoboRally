package server.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class YourCards extends MessageBody {
  // TODO programming cards only
  private final Card[] cards;

  public YourCards(Card[] cards) {
    this.cards = cards;
  }

  public Card[] getCards() {
    return cards;
  }
}
