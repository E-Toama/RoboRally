package utilities.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class CardsYouGotNow extends MessageBody {
  // TODO add cards list (register cards only)
  private final String[] cards;

  public CardsYouGotNow(String[] cards) {
    this.cards = cards;
  }

  public String[] getCards() {
    return cards;
  }
}
