package utilities.messages;

/**
 * The HelloClient class represents the protocol version that the server sends
 * to the client after the tcp connection.
 * 
 * @author
 */
public class HelloClient extends MessageBody {

  private final double protocol;
  
  /**
   * Constructor to initialize the protocol with its version.
   * @param protocol is the version of the protocol
   */
  public HelloClient(double protocol) {

    this.protocol = protocol;

  }
  
  /**
   * A method for returning the protocol version.
   * @return the version of the protocol
   */
  public double getProtocol() {
    return protocol;
  }
}
