package utilities.messages;

/**
 * 
 * @author
 */
public class SelectCard extends MessageBody {
  private final String card;
  private final int register;

  public SelectCard(String card, int register) {
    this.card = card;
    this.register = register;
  }

  public String getCard() {
    return card;
  }

  public int getRegister() {
    return register;
  }

}
