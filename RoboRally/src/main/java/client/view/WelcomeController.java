package client.view;

import client.viewmodel.WelcomeViewModel;
import game.Robots.*;
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
        smashBotButton.disableProperty().bindBidirectional(welcomeViewModel.smashBotTakenProperty());
        hulkButton.disableProperty().bindBidirectional(welcomeViewModel.hulkTakenProperty());
        spinBotButton.disableProperty().bindBidirectional(welcomeViewModel.spinBotTakenProperty());
        hammerBotButton.disableProperty().bindBidirectional(welcomeViewModel.hammerBotTakenProperty());
        townkyButton.disableProperty().bindBidirectional(welcomeViewModel.townkyTakenProperty());
        zoomBotButton.disableProperty().bindBidirectional(welcomeViewModel.zoomBotTakenProperty());
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

        switch (figure) {

            case 0:
                hammerBotButton.setDisable(true);
                break;

            case 1:
                hulkButton.setDisable(true);
                break;

            case 2:
                smashBotButton.setDisable(true);
                break;

            case 3:
                spinBotButton.setDisable(true);
                break;

            case 4:
                townkyButton.setDisable(true);
                break;

            case 5:
                zoomBotButton.setDisable(true);
                break;

            default:
               break;

        }

    }

}
