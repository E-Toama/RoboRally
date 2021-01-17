package utilities.messages;

/**
 * 
 * @author
 */
public class NotYourCards extends MessageBody {
  private final int playerID;
  private final int cards;

  public NotYourCards(int playerID, int cards) {
    this.playerID = playerID;
    this.cards = cards;
  }

  public int getPlayerID() {
    return playerID;
  }

  public int getCards() {
    return cards;
  }

}
