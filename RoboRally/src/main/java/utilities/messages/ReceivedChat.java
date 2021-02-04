package utilities.messages;

import com.google.gson.annotations.SerializedName;

/**
 * The class recievedChat is responsible for accepting chats from other users.
 * 
 * @author
 */
public class ReceivedChat extends MessageBody {

  private final String message;
  private final String from;
  @SerializedName("private")
  private Boolean isPrivate;

  /**
   * Constructor for initializing the message, source of the message and if its
   * for one user or to all.
   * 
   * @param message
   *          the message
   * @param from
   *          the source of the message
   * @param isPrivate
   *          defines if the message private or not
   */
  public ReceivedChat(String message, String from, Boolean isPrivate) {

    this.message = message;
    this.from = from;
    this.isPrivate = isPrivate;

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
   * The method returns the source of the message.
   * 
   * @return
   */
  public String getFrom() {
    return from;
  }

  /**
   * The method sets if the message private or not.
   * 
   * @param isPrivate
   *          true for private, and false for not private
   */
  public void setPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;

  }

  /**
   * The method returns the value of isPrivate.
   * 
   * @return the value of isPrivate
   */
  public Boolean getPrivate() {
    return isPrivate;
  }
}
