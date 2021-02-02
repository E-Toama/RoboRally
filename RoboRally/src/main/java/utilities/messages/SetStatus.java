package utilities.messages;

/**
 * The Class SetStatus represents the status of the player, if he is ready to
 * play or not.
 * 
 * @author
 */
public class SetStatus extends MessageBody {

  private final Boolean ready;

  /**
   * A Constructor for initializing the player status.
   * 
   * @param ready
   *          is the player status
   */
  public SetStatus(Boolean ready) {

    this.ready = ready;

  }

  /**
   * The method returns the player status.
   * 
   * @return the status of the player
   */
  public Boolean getReady() {
    return ready;
  }
}
