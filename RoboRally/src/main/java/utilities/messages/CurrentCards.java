package utilities.messages;

import game.cards.ActiveCard;

/**
 * This class is responsible for saving for each player the card that he chose
 * on a specific register.
 * 
 */
public class CurrentCards extends MessageBody {
  private final ActiveCard[] activeCards;

  /**
   * Constructor for initializing the list of the players id with the card that
   * he chose on a specific register.
   * 
   * @param activeCards
   *          the array of the active cards
   */
  public CurrentCards(ActiveCard[] activeCards) {
    this.activeCards = activeCards;
  }

  /**
   * The method returns the array of the active card for each player.
   * 
   * @return the array of active card for each player
   */
  public ActiveCard[] getActiveCards() {
    return activeCards;
  }

}
