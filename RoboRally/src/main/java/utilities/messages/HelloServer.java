package utilities.messages;

/**
 * The HelloServer class represents the protocol, group and if its an AI as an
 * answer from the client after the connection with the server.
 * 
 */
public class HelloServer extends MessageBody {

  private final double protocol;
  private final String group;
  private final Boolean isAI;

  /**
   * A constructor to initialize the protocol, group and if its an AI.
   * 
   * @param protocol
   *          is the version of the protocol
   * @param group
   *          is the group that did the project
   * @param isAI
   *          is to check whether its a player or a bot
   */
  public HelloServer(double protocol, String group, Boolean isAI) {

    this.protocol = protocol;
    this.group = group;
    this.isAI = isAI;

  }

  /**
   * A method for returning the protocol version.
   * 
   * @return the version of the protocol
   */
  public double getProtocol() {
    return protocol;
  }

  /**
   * A method for returning the name of the group.
   * 
   * @return the group
   */
  public String getGroup() {
    return group;
  }

  /**
   * A method for returning if its an AI or not.
   * 
   * @return
   */
  public Boolean getAI() {
    return isAI;
  }
}
