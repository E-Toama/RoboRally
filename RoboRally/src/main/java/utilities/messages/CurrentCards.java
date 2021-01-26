package utilities.messages;

import game.cards.ActiveCards;

/**
 * 
 * @author
 */
public class CurrentCards extends MessageBody {
  private final ActiveCards[] activeCards;

  public CurrentCards(ActiveCards[] activeCards) {
    this.activeCards = activeCards;
  }

  public ActiveCards[] getActiveCards() {
    return activeCards;
  }

}
