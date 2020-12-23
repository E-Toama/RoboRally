package server.messages;

import game.cards.ActiveCards;

/**
 * 
 * @author
 */
public class CurrentCards extends MessageBody {
  // TODO add list activeCards
  private final ActiveCards[] activeCards;

  public CurrentCards(ActiveCards[] activeCards) {
    this.activeCards = activeCards;
  }

  public ActiveCards[] getActiveCards() {
    return activeCards;
  }

}
