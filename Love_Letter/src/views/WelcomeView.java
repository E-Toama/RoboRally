package views;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Client;
import viewModel.ChatApp;
import viewModel.WelcomeViewViewModel;

import java.io.IOException;

public class WelcomeView {

    //Welcome - User registration
    @FXML
    private AnchorPane rootPaneWelcome;
    @FXML
    private TextField userNameField;
    @FXML
    private Button submitButton;

    private final WelcomeViewViewModel viewModel = new WelcomeViewViewModel();

    private final ChatApp chatApp = new ChatApp();

    @FXML
    void initialize() {

        //Wechselseitige Bindung von userNameField, submitButton und WelcomeViewModel
        userNameField.textProperty().bindBidirectional(viewModel.userNameProperty());
        submitButton.defaultButtonProperty().bindBidirectional(viewModel.submitButtonProperty());

    }
    //Client mit übergebenen Usernamen wird erstellt, Chatfenster wird geöffnet
    public void submitUserName() {

        //hier einfügen überprüfen ob nutzername vergeben (mit if else ?)

        try {

            Client client = new Client(viewModel.getUserName());

            Stage stage = (Stage) submitButton.getScene().getWindow();
            stage.setTitle(viewModel.getUserName());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ChatView.fxml"));

            stage.setScene(new Scene(loader.load()));

            ChatView chatView = loader.getController();
            chatView.setClient(client);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    client.writeToServer("bye");
                    stage.close();
                }
            });

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
