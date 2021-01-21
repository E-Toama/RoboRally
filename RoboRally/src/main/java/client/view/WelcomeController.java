package client.view;

import client.viewmodel.WelcomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class WelcomeController {

    private final WelcomeViewModel welcomeViewModel = new WelcomeViewModel();

    @FXML
    private TextField userTextField;

    @FXML
    private Button smashBotButton;

    @FXML
    private Button hulkButton;

    @FXML
    private Button spinBotButton;

    @FXML
    private Button hammerBotButton;

    @FXML
    private Button townkyButton;

    @FXML
    private Button zoomBotButton;

    @FXML
    void initialize() {
        userTextField.textProperty().bindBidirectional(welcomeViewModel.userNameTextFieldProperty());
        smashBotButton.disableProperty().bind(welcomeViewModel.isSmashRobotTaken());
        hulkButton.disableProperty().bind(welcomeViewModel.isHulkTaken());
        spinBotButton.disableProperty().bind(welcomeViewModel.isSpinBotTaken());
        hammerBotButton.disableProperty().bind(welcomeViewModel.isHammerBotTaken());
        townkyButton.disableProperty().bind(welcomeViewModel.isTwonkyTaken());
        zoomBotButton.disableProperty().bind(welcomeViewModel.isZoomBotTaken());
    }

    public void submitPlayer() {

        welcomeViewModel.submitPlayer();

    }

    public void setSmashBot() {

        welcomeViewModel.setSmashBot();

    }

    public void setHulk() {

        welcomeViewModel.setHulk();

    }

    public void setSpinBot() {

        welcomeViewModel.setSpinBot();

    }

    public void setHammerBot() {

        welcomeViewModel.setHammerBot();

    }

    public void setTwonky() {

        welcomeViewModel.setTwonky();

    }

    public void setZoomBot() {

        welcomeViewModel.setZoomBot();

    }

    public void disableButton(int figure) {



    }

}
