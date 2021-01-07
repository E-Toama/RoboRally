package client;

import client.network.ClientThread;
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

            Parent lobby = FXMLLoader.load(getClass().getResource("/FXMLFiles/Lobby.fxml"));

            Scene lobbyScene = new Scene(lobby);
            primaryStage.setScene(lobbyScene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(e -> Platform.exit());

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
