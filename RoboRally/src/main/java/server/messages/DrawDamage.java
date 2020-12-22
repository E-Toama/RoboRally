package server.messages;

import game.cards.Card;

/**
 * 
 * @author
 */
public class DrawDamage extends MessageBody {
  private final int playerID;
  // TODO Damage Cards only.
  private final Card[] cards;

  public DrawDamage(int playerID, Card[] cards) {
    this.playerID = playerID;
    this.cards = cards;
  }

  public Card[] getCards() {
    return cards;
  }

  public int getPlayerID() {
    return playerID;
  }

}
