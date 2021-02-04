package utilities.messages;

/**
 * This class is responsible for saving the message type and message body.
 * 
 * @author
 */
public class Message {

  private final String messageType;
  private final MessageBody messageBody;

  /**
   * Constructor for initializing the message type and the message body.
   * 
   * @param messageType
   *          the type of the message
   * @param messageBody
   *          the message body
   */
  public Message(String messageType, MessageBody messageBody) {

    this.messageType = messageType;
    this.messageBody = messageBody;

  }

  /**
   * The method returns the message type.
   * 
   * @return the message type
   */
  public String getMessageType() {
    return messageType;
  }

  /**
   * The method returns the message body.
   * 
   * @return the message body
   */
  public MessageBody getMessageBody() {
    return messageBody;
  }

}
