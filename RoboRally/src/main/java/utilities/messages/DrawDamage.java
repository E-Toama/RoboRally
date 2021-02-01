package utilities.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class DrawDamage extends MessageBody {
  private final int playerID;
  // TODO Damage Cards only.
  private final String[] cards;

  public DrawDamage(int playerID, String[] cards) {
    this.playerID = playerID;
    this.cards = cards;
  }

  public String[] getCards() {
    return cards;
  }

  public int getPlayerID() {
    return playerID;
  }

}
