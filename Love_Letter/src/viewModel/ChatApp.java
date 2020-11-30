package viewModel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launches the JavaFX Application
 *
 * @author Dennis
 */
public class ChatApp extends Application {

    /**
     * Launches the Application
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Loads the "WelcomeView" and set's the Scene
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        try {

            stage.setTitle("Welcome");

            Parent welcome = FXMLLoader
                    .load(getClass().getResource("/views/WelcomeView.fxml"));

            Scene welcomeScene = new Scene(welcome);

            stage.setScene(welcomeScene);
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
