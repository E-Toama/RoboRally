package client.view;

import client.viewmodel.LobbyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Controller class for the Lobby with username list and chat
 * 
 */
public class LobbyController {

    private LobbyViewModel lobbyViewModel = new LobbyViewModel();

    @FXML
    private ListView<String> chatBox;

    @FXML
    private TextField chatTextField;

    @FXML
    private TextField userNameTextField;


    /**
     * binds FXML attributes to the lobbyViewModel
     */
    @FXML
    void initialize() {

        chatBox.setItems(lobbyViewModel.getClientThread().chatMessages);
        chatTextField.textProperty().bindBidirectional(lobbyViewModel.chatTextProperty());
        userNameTextField.textProperty().bindBidirectional(lobbyViewModel.userNameTextFieldProperty());

    }

    /**
     * calls sendMessage() in the lobbyViewModel
     */
    public void sendMessage() {

        lobbyViewModel.sendMessage();

    }

    /**
     * calls submitUserNameAndRobot() in the lobbyViewModel
     */
    public void submitUserNameAndRobot() {

        lobbyViewModel.submitUserNameAndRobot();

    }

    /**
     * sets the Smash Bot in the lobbyViewModel
     */
    public void setSmashBot() {

        lobbyViewModel.setSmashBot();

    }

    /**
     * sets the Hulk x90 bot in the lobbyviewmodel
     */
    public void setHulk() {

        lobbyViewModel.setHulk();

    }

    /**
     * sets the Spin Bot in the lobbyViewModel
     */
    public void setSpinBot() {

        lobbyViewModel.setSpinBot();

    }

    /**
     * sets the Hammer Bot in the lobbyViewModel
     */
    public void setHammerBot() {

        lobbyViewModel.setHammerBot();

    }

    /**
     * sets the Twonky Bot in the lobbbyViewModel
     */
    public void setTwonky() {

        lobbyViewModel.setTwonky();

    }

    /**
     * sets the Zoom Bot in the lobbyViewModel
     */
    public void setZoomBot() {

        lobbyViewModel.setZoomBot();

    }

    /**
     * calls setReady() in the lobbyViewModel
     */
    public void setReady() {

        lobbyViewModel.setReady();

    }

}
