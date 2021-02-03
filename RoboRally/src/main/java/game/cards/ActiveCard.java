package game.cards;

/**
 * 
 * @author
 */
public class ActiveCard {

  private final int playerID;
  private final String card;

  public ActiveCard(int playerID, String card) {
    this.playerID = playerID;
    this.card = card;
  }

  public int getPlayerID() {
    return playerID;
  }

  public String getCard() {
    return card;
  }

}
