package client.viewmodel;

import client.network.ClientThread;
import client.view.ViewController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class WelcomeViewModel {

    private final ClientThread clientThread;
    private final StringProperty userNameTextField = new SimpleStringProperty();
    private final BooleanProperty hammerBotTaken = new SimpleBooleanProperty();
    private final BooleanProperty hulkTaken = new SimpleBooleanProperty();
    private final BooleanProperty smashBotTaken = new SimpleBooleanProperty();
    private final BooleanProperty spinBotTaken = new SimpleBooleanProperty();
    private final BooleanProperty townkyTaken = new SimpleBooleanProperty();
    private final BooleanProperty zoomBotTaken = new SimpleBooleanProperty();
    private int selectedRobot;

    public WelcomeViewModel() {

        this.clientThread = ClientThread.getInstance();
        this.clientThread.setWelcomeViewModel(this);

    }

    public boolean isHammerBotTaken() {
        return hammerBotTaken.get();
    }

    public BooleanProperty hammerBotTakenProperty() {
        return hammerBotTaken;
    }

    public boolean isHulkTaken() {
        return hulkTaken.get();
    }

    public BooleanProperty hulkTakenProperty() {
        return hulkTaken;
    }

    public boolean isSmashBotTaken() {
        return smashBotTaken.get();
    }

    public BooleanProperty smashBotTakenProperty() {
        return smashBotTaken;
    }

    public boolean isSpinBotTaken() {
        return spinBotTaken.get();
    }

    public BooleanProperty spinBotTakenProperty() {
        return spinBotTaken;
    }

    public boolean isTownkyTaken() {
        return townkyTaken.get();
    }

    public BooleanProperty townkyTakenProperty() {
        return townkyTaken;
    }

    public boolean isZoomBotTaken() {
        return zoomBotTaken.get();
    }

    public BooleanProperty zoomBotTakenProperty() {
        return zoomBotTaken;
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

                    e.printStackTrace();

                }

            }
        });

    }

    public void playerNotAdded() {}

    public void disableRobotButton(int figure) {

        switch (figure) {

            case 0:
                hammerBotTaken.setValue(true);
                break;

            case 1:
                hulkTaken.setValue(true);
                break;

            case 2:
                smashBotTaken.setValue(true);
                break;

            case 3:
                spinBotTaken.setValue(true);
                break;

            case 4:
                townkyTaken.setValue(true);
                break;

            case 5:
                zoomBotTaken.setValue(true);
                break;

            default:
                break;

        }

    }

}
