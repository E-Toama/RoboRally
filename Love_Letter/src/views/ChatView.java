package views;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Client;
import viewModel.ChatViewViewModel;

public class ChatView {

    //Chat
    @FXML
    private AnchorPane rootPaneChat;
    @FXML
    private  AnchorPane chatWrite;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private TextField writeField;
    @FXML
    private Button sendButton;
    @FXML
    private ListView chatBox;

    private final ChatViewViewModel viewModel = new ChatViewViewModel();

    @FXML
    void initialize() {

        writeField.textProperty().bindBidirectional(viewModel.messageProperty());
        sendButton.defaultButtonProperty().bindBidirectional(viewModel.sendButtonProperty());

    }

    public void sendMessage() {

        viewModel.sendMessage();

    }

    public void setClient(Client client) {

        viewModel.setClient(client);
        chatBox.setItems(viewModel.getClient().chatMessages);

    }

}
