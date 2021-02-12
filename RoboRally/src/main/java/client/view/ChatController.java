package client.view;

import client.viewmodel.ChatViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 *  Controller Class for the Lobby Chat
 *  is binded to ChatViewModel
 *
 */
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


    /**
     *
     * binds FXML attributes to the chatViewModel properties
     * initializes dropdown menu with players for private messaging
     */
    @FXML
    void initialize() {

        chatTextField.textProperty().bindBidirectional(chatViewModel.chatTextProperty());
        statusWindow.setItems(chatViewModel.getClientThread().observablePlayerList);
        chatBox.setItems(chatViewModel.getClientThread().chatMessages);
        dropDown2.setItems(chatViewModel.getClientThread().observablePlayerListWithDefault);
        dropDown2.getSelectionModel().selectFirst();

    }

    /**
     * calls sendChat() in the chatViewModel
     */
    public void sendChat() {

        chatViewModel.sendChat();

    }

    /**
     * calls setReady() in the chatViewModel
     */
    public void setReady() {

        chatViewModel.setReady();

    }

    /**
     * calls changeDestination in the chatViewModel with the chosen player in from the dropdown menu
     */
    public void changeDestination() {

        chatViewModel.changeDestination(chatViewModel.getClientThread().messageMatchMap.get(dropDown2.getValue()));

    }

}
