package utilities.messages;

/**
 * The class CardPlayed represents the card that the player played which will be
 * sent to all users by the server.
 * 
 * @author
 */
public class CardPlayed extends MessageBody {

  private final int playerID;
  private final String card;

  /**
   * Constuctor for initializing the player id and his played card.
   * 
   * @param playerID
   *          the player id
   * @param card
   *          the card that he played
   */
  public CardPlayed(int playerID, String card) {
    this.playerID = playerID;
    this.card = card;
  }

  /**
   * The method returns the player id.
   * 
   * @return the player id
   */
  public int getPlayerID() {
    return playerID;
  }

  /**
   * The method returns the card that the player played.
   * 
   * @return the card that the player played
   */
  public String getCard() {
    return card;
  }

}
