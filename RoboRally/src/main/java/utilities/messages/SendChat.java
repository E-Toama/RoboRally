package utilities.messages;

/**
 * The class SendChat is responsible for sending private chats from one user to
 * other or not private to all.
 * 
 */
public class SendChat extends MessageBody {

  private final String message;
  private final int to;

  /**
   * Constructor for initializing the message and the destination.
   * 
   * @param message
   *          the message
   * @param to
   *          the destination
   */
  public SendChat(String message, int to) {

    this.message = message;
    this.to = to;

  }

  /**
   * The method returns the message.
   * 
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * The method returns the destination.
   * 
   * @return the destination
   */
  public int getTo() {
    return to;
  }
}
