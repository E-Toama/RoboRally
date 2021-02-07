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

/**
 * ViewModel for the WelcomeView
 * @author
 */
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

    /**
     * constructor for WelcomeViewModel with client thread
     */
    public WelcomeViewModel() {

        this.clientThread = ClientThread.getInstance();
        this.clientThread.setWelcomeViewModel(this);

    }


    public boolean isHammerBotTaken() {
        return hammerBotTaken.get();
    }

    public boolean isHulkTaken() {
        return hulkTaken.get();
    }

    public boolean isSmashBotTaken() {
        return smashBotTaken.get();
    }

    public boolean isZoomBotTaken() {
        return zoomBotTaken.get();
    }

    public boolean isSpinBotTaken() {
        return spinBotTaken.get();
    }

    public boolean isTownkyTaken() {
        return townkyTaken.get();
    }

    /**
     *
     * @return BooleanProperty hammerBotTaken
     */
    public BooleanProperty hammerBotTakenProperty() {
        return hammerBotTaken;
    }




    /**
     * @return BooleanProperty hulkTaken
     */
    public BooleanProperty hulkTakenProperty() {
        return hulkTaken;
    }



    /**
     * @return BoooleanProperty smashBotTaken
     */
    public BooleanProperty smashBotTakenProperty() {
        return smashBotTaken;
    }



    /**
     * @return BooleanProperty spinBotTaken
     */
    public BooleanProperty spinBotTakenProperty() {
        return spinBotTaken;
    }


    /**
     * @return BooleanProperty twonkyTaken
     */
    public BooleanProperty townkyTakenProperty() {
        return townkyTaken;
    }


    /**
     * @return BooleanProperty zoomBotTaken
     */
    public BooleanProperty zoomBotTakenProperty() {
        return zoomBotTaken;
    }

    public String getUserNameTextField() {
        return userNameTextField.get();
    }

    /**
     * @return StringProperty userNameTextField
     */
    public StringProperty userNameTextFieldProperty() {
        return userNameTextField;
    }

    /**
     * gets client thread
     */
    public ClientThread getClientThread() {
        return clientThread;
    }

    /**
     * sets Smash Bot with index 2
     */
    public void setSmashBot() {
        this.selectedRobot = 2;
    }

    /**
     * sets Hulk x90 with index 1
     */
    public void setHulk() {
        this.selectedRobot = 1;
    }

    /**
     * sets Spin Bot with index 3
     */
    public void setSpinBot() {
        this.selectedRobot = 3;
    }

    /**
     * sets Hammer Bot with index 0
     */
    public void setHammerBot() {
        this.selectedRobot = 0;
    }

    /**
     * sets Twonky with index 4
     */
    public void setTwonky() {
        this.selectedRobot = 4;
    }

    /**
     * sets Zoom Bot with index 5
     */
    public void setZoomBot() {
        this.selectedRobot = 5;
    }



    /**
     * submits new player with username and selected robot
     */
    public void submitPlayer() {
        clientThread.submitPlayer(getUserNameTextField(), selectedRobot);
    }

    /**
     * proceeds to Lobby after player is added
     */
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

    /**
     * disables robot button if it's already taken by another player
     * @param figure is the index of the robot figure
     */
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
