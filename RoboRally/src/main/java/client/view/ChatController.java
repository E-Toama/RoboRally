package client.view;

import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ChatController {

    private final ChatViewModel chatViewModel = new ChatViewModel();

    @FXML
    private TextField chatTextField;

    @FXML
    private ListView<String> chatBox;

    @FXML
    private ListView<String> statusWindow;

    @FXML
    void initialize() {

        chatTextField.textProperty().bindBidirectional(chatViewModel.chatTextProperty());
        statusWindow.setItems(chatViewModel.getClientThread().observablePlayerList);
        chatBox.setItems(chatViewModel.getClientThread().chatMessages);

    }

    public void sendChat() {

        chatViewModel.sendChat();

    }

    public void setReady() {

        chatViewModel.setReady();

    }

}
