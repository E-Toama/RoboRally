package client.view;

import client.viewmodel.WelcomeViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class WelcomeController {

    private final WelcomeViewModel welcomeViewModel = new WelcomeViewModel();

    @FXML
    private TextField userTextField;

    @FXML
    void initialize() {
        userTextField.textProperty().bindBidirectional(welcomeViewModel.userNameTextFieldProperty());
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

}
