package game.cards;

/**
 * This class is responsible for saving a player id with the card that he chose
 * on a specific register.
 * 
 */
public class ActiveCard {

  private final int playerID;
  private final String card;

  /**
   * Constructor for initializing the player id and the card that he chose.
   * 
   * @param playerID
   *          the player id
   * @param card
   *          the card that the player picked
   */
  public ActiveCard(int playerID, String card) {
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
   * The method returns the card that the player picked.
   * 
   * @return the chosen card of the player
   */
  public String getCard() {
    return card;
  }

}
