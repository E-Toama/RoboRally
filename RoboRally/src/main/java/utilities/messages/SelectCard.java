package utilities.messages;

/**
 * 
 * @author
 */
public class SelectCard extends MessageBody {
  private final String card;
  private final int register;

  public SelectCard(String cards, int register) {
    this.card = cards;
    this.register = register;
  }

  public String getCards() {
    return card;
  }

  public int getRegister() {
    return register;
  }

}
