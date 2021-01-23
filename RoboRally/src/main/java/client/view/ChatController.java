package client.view;

import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChatController {

    private final ChatViewModel chatViewModel = new ChatViewModel();

    @FXML
    private TextField chatTextField;

    @FXML
    private ListView<String> chatBox;

    @FXML
    private ListView<String> statusWindow;

    @FXML
    private Button sendButton;

    @FXML
    private ComboBox dropDown2;

    @FXML
    void initialize() {

        chatTextField.textProperty().bindBidirectional(chatViewModel.chatTextProperty());
        statusWindow.setItems(chatViewModel.getClientThread().observablePlayerList);
        chatBox.setItems(chatViewModel.getClientThread().chatMessages);
        dropDown2.setItems(chatViewModel.getClientThread().observablePlayerListWithDefault);
        dropDown2.getSelectionModel().selectFirst();

    }

    public void sendChat() {

        chatViewModel.sendChat();

    }

    public void setReady() {

        chatViewModel.setReady();

    }

    public void changeDestination() {

         System.out.println(chatViewModel.getClientThread().messageMatchMap.get(dropDown2.getValue()));
         System.out.println(dropDown2.getValue());

        chatViewModel.changeDestination(chatViewModel.getClientThread().messageMatchMap.get(dropDown2.getValue()));

    }

}
