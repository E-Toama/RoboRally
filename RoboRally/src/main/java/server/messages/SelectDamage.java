package server.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class SelectDamage extends MessageBody {
  // TODO damage Cards only.
  private final Card[] cards;
  
  public SelectDamage(Card[] cards) {
    this.cards = cards;
  }

  public Card[] getCards() {
    return cards;
  }

}
