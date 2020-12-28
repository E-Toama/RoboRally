package game.cards;

/**
 * 
 * @author
 */
public class ActiveCards {
  private final int playerID;
  private final Card card;

  public ActiveCards(int playerID, Card card) {
    this.playerID = playerID;
    this.card = card;
  }

  public int getPlayerID() {
    return playerID;
  }

  public Card getCard() {
    return card;
  }

}
