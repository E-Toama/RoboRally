package viewModel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.network.Client;

/**
 * View Model for the ChatView sets up and starts the Client successfully constructed in WelcomeView.
 * Fetches messages from View and calls writeToServer-method.
 * @author Josef, Dennis
 * */
public class ChatViewViewModel {

  // Properties corresponding to ChatView
  private StringProperty message = new SimpleStringProperty();
  private BooleanProperty sendButton = new SimpleBooleanProperty();

  // Setting up the Client
  private Thread clientThread;
  private Client client;

  /**
   * Sets and runs the current client, called from the View-Class
   * @param client The successfully constructed Client from Welcome-View
   */
  public void setClient(Client client) {
    this.client = client;
    this.clientThread = new Thread(client);
    clientThread.start();
  }

  /**
   * Get the current client. Accessed by the ChatView-Class to append incoming messages
   * @return The current client-object
   */
  public Client getClient() {
    return client;
  }

  // Getters and Setters for Properties

  /**
   * Access the message property for bidirectional binding with the View-Class
   * @return The message String Property bound to the Textfield
   */
  public StringProperty messageProperty() {
    return message;
  }

  /**
   * Retrieves the actual String-message from the message property
   * Performs a quick check if the message entered is null.
   * @return the actual message submitted by the user
   */
  public final String getMessage() {
    return message.get() != null ? message.get() : "";
  }

  /**
   * Sets the content of the user's text field.
   * Used in sendMessage()-method to set the text field back to empty after the user hits submit-button
   * @param newMessage The new content of the message-text field
   */
  public final void setMessage(String newMessage) {
    message.set(newMessage);
  }

  /**
   * Retrieves the current property of the sendButton
   * @return Boolean Property of the sendButton
   */
  public BooleanProperty sendButtonProperty() {
    return sendButton;
  }

  /**
   * Fetch the current message from ChatView, pass it on to the server and
   * empty the text field for the user.
   * Close the screen and shutdown client-side process if user enters "!BYE".
   */
  public final void sendMessage() {
    String currentMessage = getMessage();
    client.writeToServer(currentMessage);

    // Close window and stop process if user enters "!BYE"
    if (currentMessage.startsWith("!BYE")) {
      Platform.exit();
      System.exit(0);

    } else {

      this.setMessage("");
    }
  }
}
