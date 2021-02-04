package utilities.messages;

/**
 * The class ConnectionUpdate is responsible for the Connection loss, it saves
 * the player id that lost the connection.
 * 
 * @author
 *
 */
public class ConnectionUpdate extends MessageBody {
  private int playerID;
  private Boolean connected;
  private String action;

  /**
   * Constructor for initializing the values of the following parameters.
   * 
   * @param playerID
   *          the player id
   * @param connected
   *          if the player is connected
   * @param action
   *          the action that should be done after connection loss
   */
  public ConnectionUpdate(int playerID, Boolean connected, String action) {
    this.playerID = playerID;
    this.connected = connected;
    this.action = action;
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
   * The method returns the connection status.
   * 
   * @return the connection status
   */
  public Boolean getConnected() {
    return connected;
  }

  /**
   * The method returns the action that should be done after losing connection.
   * 
   * @return the action after losing connection
   */
  public String getAction() {
    return action;
  }
}
