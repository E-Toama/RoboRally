package utilities.messages;

import game.cards.Card;

/**
 * This class is responsible for saving the damage cards that the player draws.
 * 
 * @author
 */
public class DrawDamage extends MessageBody {
  private final int playerID;
  // TODO Damage Cards only.
  private final String[] cards;

  /**
   * Constructor for initializing the player id and the list of the damage cards
   * that he drew.
   * 
   * @param playerID
   *          the player id
   * @param cards
   *          array of damage cards
   */
  public DrawDamage(int playerID, String[] cards) {
    this.playerID = playerID;
    this.cards = cards;
  }

  /**
   * The method returns the array of damage cards.
   * 
   * @return array of damage cards
   */
  public String[] getCards() {
    return cards;
  }

  /**
   * The method returns the player id.
   * 
   * @return the player id
   */
  public int getPlayerID() {
    return playerID;
  }

}
