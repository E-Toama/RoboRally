package utilities.messages;

import game.cards.ActiveCard;

/**
 * 
 * @author
 */
public class CurrentCards extends MessageBody {
  // TODO add list activeCards
  private final ActiveCard[] activeCards;

  public CurrentCards(ActiveCard[] activeCards) {
    this.activeCards = activeCards;
  }

  public ActiveCard[] getActiveCards() {
    return activeCards;
  }

}
