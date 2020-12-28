package server.messages;

/**
 * 
 * @author
 */
public class PlayCard extends MessageBody {

  private final String card;

  public PlayCard(String card) {
    this.card = card;
  }

  public String getCard() {
    return card;
  }

}
