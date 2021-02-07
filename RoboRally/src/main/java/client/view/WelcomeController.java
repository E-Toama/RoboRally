package client.view;

import client.viewmodel.WelcomeViewModel;
import game.Robots.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller class for the WelcomeView
 * @author
 */
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

    /**
     * binds FXML attributes to welcomeViewModel properties
     */
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

    /**
     * calls submitPlayer() in welcomeViewModel
     */
    public void submitPlayer() {

        welcomeViewModel.submitPlayer();

    }


    /**
     * calls setSmashBot() in welcomeViewModel
     */
    public void setSmashBot() {

        welcomeViewModel.setSmashBot();

    }

    /**
     * calls setHulk() in welcomeViewModel
     */
    public void setHulk() {

        welcomeViewModel.setHulk();

    }

    /**
     * calls setSpinBot() in welcomeViewModel
     */
    public void setSpinBot() {

        welcomeViewModel.setSpinBot();

    }

    /**
     * calls hammerBot() in welcomeViewModel
     */
    public void setHammerBot() {

        welcomeViewModel.setHammerBot();

    }

    /**
     * calls setTwonky() in welcomeViewModel
     */
    public void setTwonky() {

        welcomeViewModel.setTwonky();

    }

    /**
     * calls setZoomBot() in welcomeViewModel
     */
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
