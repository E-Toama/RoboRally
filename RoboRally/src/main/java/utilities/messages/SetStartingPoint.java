package utilities.messages;

/**
 * 
 * @author
 */
public class SetStartingPoint extends MessageBody {
  private final int position;

  public SetStartingPoint(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

}
