package server.messages;

/**
 * 
 * @author
 */
public class PickDamage extends MessageBody {
  private final int count;

  public PickDamage(int count) {
    this.count = count;
  }

  public int getCount() {
    return count;
  }
}
