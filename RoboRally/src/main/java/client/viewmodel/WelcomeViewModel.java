package client.viewmodel;

import client.network.ClientThread;
import client.view.ViewController;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class WelcomeViewModel {

    private final ClientThread clientThread;
    private final StringProperty userNameTextField = new SimpleStringProperty();
    private int selectedRobot;

    public WelcomeViewModel() {

        this.clientThread = ClientThread.getClientThread();
        this.clientThread.setWelcomeViewModel(this);

    }

    public String getUserNameTextField() {
        return userNameTextField.get();
    }

    public StringProperty userNameTextFieldProperty() {
        return userNameTextField;
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public void setSmashBot() {
        this.selectedRobot = 2;
    }

    public void setHulk() {
        this.selectedRobot = 1;
    }

    public void setSpinBot() {
        this.selectedRobot = 3;
    }

    public void setHammerBot() {
        this.selectedRobot = 0;
    }

    public void setTwonky() {
        this.selectedRobot = 4;
    }

    public void setZoomBot() {
        this.selectedRobot = 5;
    }

    public void submitPlayer() {
        clientThread.submitPlayer(getUserNameTextField(), selectedRobot);
    }

    public void playerSuccesfullyAdded() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                try {

                    Parent lobby = FXMLLoader.load(getClass().getResource("/FXMLFiles/ChatWindow.fxml"));

                    Scene chatScene = new Scene(lobby);
                    ViewController.getViewController().setTitle(clientThread.getPlayer().getName());
                    ViewController.getViewController().setScene(chatScene);

                } catch (IOException e) {

                    System.out.println("Fehler hier");

                    e.printStackTrace();

                }

            }
        });

    }

    public void playerNotAdded() {}

}
