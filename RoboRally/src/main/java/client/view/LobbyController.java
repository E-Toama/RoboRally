package client.view;

import client.viewmodel.LobbyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class LobbyController {

    private LobbyViewModel lobbyViewModel = new LobbyViewModel();

    @FXML
    private ListView<String> chatBox;

    @FXML
    private TextField chatTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    void initialize() {

        chatBox.setItems(lobbyViewModel.getClientThread().chatMessages);
        chatTextField.textProperty().bindBidirectional(lobbyViewModel.chatTextProperty());
        userNameTextField.textProperty().bindBidirectional(lobbyViewModel.userNameTextFieldProperty());

    }

    public void sendMessage() {

        lobbyViewModel.sendMessage();

    }

    public void submitUserNameAndRobot() {

        lobbyViewModel.submitUserNameAndRobot();

    }

    public void setSmashBot() {

        lobbyViewModel.setSmashBot();

    }

    public void setHulk() {

        lobbyViewModel.setHulk();

    }

    public void setSpinBot() {

        lobbyViewModel.setSpinBot();

    }

    public void setHammerBot() {

        lobbyViewModel.setHammerBot();

    }

    public void setTwonky() {

        lobbyViewModel.setTwonky();

    }

    public void setZoomBot() {

        lobbyViewModel.setZoomBot();

    }

    public void setReady() {

        lobbyViewModel.setReady();

    }

}
