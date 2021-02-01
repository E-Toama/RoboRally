package client;

import client.view.ViewController;
import game.utilities.PositionLookUp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RoboRallyClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try{

            PositionLookUp.createMaps();

            ViewController.getViewController().setPrimaryStage(primaryStage);

            Parent lobby = FXMLLoader.load(getClass().getResource("/FXMLFiles/WelcomeWindow.fxml"));

            Scene lobbyScene = new Scene(lobby);
            primaryStage.setScene(lobbyScene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(e -> Platform.exit());

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
