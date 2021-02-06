package utilities.messages;

/**
 * This class represents the card that should be played.
 * 
 * @author
 */
public class PlayCard extends MessageBody {

  private final String card;

  /**
   * Constructor for initializing the card.
   * 
   * @param card
   *          the card that was picked
   */
  public PlayCard(String card) {
    this.card = card;
  }

  /**
   * The method returns the card.
   * 
   * @return the card
   */
  public String getCard() {
    return card;
  }

}
