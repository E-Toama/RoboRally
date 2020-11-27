package views;

import exceptions.DuplicateNameException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.network.Client;
import viewModel.ChatApp;
import viewModel.WelcomeViewViewModel;

/**
 * @author Ehbal, Elias
 */
public class WelcomeView {

    // Welcome - User registration
    @FXML
    private AnchorPane rootPaneWelcome;
    @FXML
    private TextField userNameField;
    @FXML
    private Button submitButton;
    @FXML
    private Label nameTakenLabel;

    private final WelcomeViewViewModel viewModel = new WelcomeViewViewModel();

    private final ChatApp chatApp = new ChatApp();

    @FXML
    void initialize() {

        // Wechselseitige Bindung von userNameField, submitButton und
        // WelcomeViewModel
        userNameField.textProperty()
                .bindBidirectional(viewModel.userNameProperty());
        submitButton.defaultButtonProperty()
                .bindBidirectional(viewModel.submitButtonProperty());

    }

    public void submitUserName() {

        try {
            //Passing the current stage to the ViewModel
            Stage stage = (Stage) submitButton.getScene().getWindow();
            viewModel.submitUserName(stage);

            //Client-Constructor throws DuplicateNameException if name already taken
        } catch (DuplicateNameException e) {
            nameTakenLabel.setText(e.getMessage());
            nameTakenLabel.setVisible(true);  // Display notification
        }
    }

    @FXML
    public void hideNameTakenLabel() {
        nameTakenLabel.setVisible(false);
    }

}
