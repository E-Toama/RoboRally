package game.cards;

/**
 * 
 * @author
 */
public class ActiveCard {

  private final int playerID;
  private final Card card;

  public ActiveCard(int playerID, Card card) {
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
