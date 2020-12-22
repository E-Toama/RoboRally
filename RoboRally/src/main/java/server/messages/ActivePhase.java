package server.messages;

/**
 * 
 * @author
 */
public class ActivePhase extends MessageBody {
  private final int phase;

  public ActivePhase(int phase) {
    this.phase = phase;
  }

  public int getPhase() {
    return phase;
  }

}
