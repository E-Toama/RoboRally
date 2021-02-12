package utilities.messages;

/**
 * This class represents the currently active phase of the game.
 * 
 */
public class ActivePhase extends MessageBody {
  private final int phase;

  /**
   * Constructor for initializing the current phase.
   * 
   * @param phase
   *          the current phase of the game
   */
  public ActivePhase(int phase) {
    this.phase = phase;
  }

  /**
   * The method returns the current phase of the game.
   * 
   * @return the current phase
   */
  public int getPhase() {
    return phase;
  }

}
