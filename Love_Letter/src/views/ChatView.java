package views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.network.Client;
import viewModel.ChatViewViewModel;

/**
 * This class is the controller class for the fxml file and it represents the chat window.
 * 
 * @author Elias, Ehbal
 */
public class ChatView {

  // Chat
  @FXML
  private AnchorPane rootPaneChat;
  @FXML
  private AnchorPane chatWrite;
  @FXML
  private AnchorPane chatPane;
  @FXML
  private TextField writeField;
  @FXML
  private Button sendButton;
  @FXML
  private ListView<String> chatBox;

  private final ChatViewViewModel viewModel = new ChatViewViewModel();

  /**
   * The method initializes the textfield and the button in the chat window using data binding.
   */
  @FXML
  void initialize() {

    writeField.textProperty().bindBidirectional(viewModel.messageProperty());
    sendButton.defaultButtonProperty().bindBidirectional(viewModel.sendButtonProperty());

  }

  /**
   * This method is responsible for sending a message to the server.
   */
  public void sendMessage() {

    viewModel.sendMessage();

  }

  /**
   * This method sets the messages sent from the user in the Chat window.
   * @param client The user which sends the message.
   */
  public void setClient(Client client) {

    viewModel.setClient(client);
    chatBox.setItems(viewModel.getClient().chatMessages);

  }

}
