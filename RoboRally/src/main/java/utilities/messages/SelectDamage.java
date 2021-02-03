package utilities.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class SelectDamage extends MessageBody {
  // TODO damage Cards only.
  private final String[] cards;
  
  public SelectDamage(String[] cards) {
    this.cards = cards;
  }

  public String[] getCards() {
    return cards;
  }

}
