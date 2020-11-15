package views;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.Client;
import viewModel.ChatViewViewModel;

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

  @FXML
  void initialize() {

    // Wechselseitige Bindung von writeField,sendButton und ChatViewViewModel
    writeField.textProperty().bindBidirectional(viewModel.messageProperty());
    sendButton.defaultButtonProperty()
        .bindBidirectional(viewModel.sendButtonProperty());

  }

  // Führt sendMessage() in ChatViewViewModel aus
  public void sendMessage() {

    viewModel.sendMessage();

  }

  // chatMessages werden in die ListView chatBox übertragen
  public void setClient(Client client) {

    viewModel.setClient(client);
    chatBox.setItems(viewModel.getClient().chatMessages);

  }

  public void closeApplication() {
    Platform.exit();
  }

}
