package client;

import client.network.ClientThread;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RoboRallyClient extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        try{

            ClientThread clientThread = new ClientThread(false);

            //ToDo: how to handle the situation if handle connection fails



        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
