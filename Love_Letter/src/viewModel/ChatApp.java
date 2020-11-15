package viewModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ChatApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override

    //Welcome Fenster wird geöffnet
    public void start(Stage stage) {

        try {

            stage.setTitle("Welcome");

            Parent welcome = FXMLLoader.load(getClass().getResource("../views/WelcomeView.fxml"));

            Scene welcomeScene = new Scene(welcome);

            stage.setScene(welcomeScene);
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
