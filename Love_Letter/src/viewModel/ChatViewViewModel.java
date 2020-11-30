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

  public void setClient(Client client) {
    this.client = client;
    this.clientThread = new Thread(client);
    clientThread.start();
  }

  public Client getClient() {
    return client;
  }

  // Getters and Setters for Properties
  public StringProperty messageProperty() {
    return message;
  }

  public final String getMessage() {
    return message.get();
  }

  public final void setMessage(String newMessage) {
    message.set(newMessage);
  }

  public BooleanProperty sendButtonProperty() {
    return sendButton;
  }

  public final Boolean getSendButton() {
    return sendButton.get();
  }

  public final void setSendButton(Boolean value) {
    sendButton.set(value);
  }

  /**
   * Fetch the current message from ChatView, pass it on to the server
   * and empty the text field for the user.
   * Close the screen and shutdown client-side process if user enters "!BYE".
   */
  public final void sendMessage() {
    String currentMessage = getMessage();
    client.writeToServer(currentMessage);

    // Fenster schließen, falls "bye" eingegeben wird
    if (currentMessage.startsWith("!BYE")) {
      Platform.exit();
      System.exit(0);

    } else {

      this.setMessage("");
    }
  }
}
