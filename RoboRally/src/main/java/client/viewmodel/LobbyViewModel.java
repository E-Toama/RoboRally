package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

/**
 * ViewModel class for the Lobby
 * 
 */
public class LobbyViewModel {

    private ClientThread clientThread;
    private StringProperty chatText = new SimpleStringProperty();
    private StringProperty userNameTextField = new SimpleStringProperty();

    /**
     * Constructor of the LobbyViewModel with client thread
     */
    public LobbyViewModel() {

        this.clientThread = ClientThread.getInstance();

    }


    public ClientThread getClientThread() {
        return clientThread;
    }

    /**
     * gets the chat messages
     * @return
     */
    public String getChatText() {
        return chatText.get();
    }


    public StringProperty chatTextProperty() {
        return chatText;
    }

    public String getUserNameTextField() {
        return userNameTextField.get();
    }

    public StringProperty userNameTextFieldProperty() {
        return userNameTextField;
    }

    /**
     * responsible for sending the chat messages and clearing the textfield afterwards
     */
    public void sendMessage() {

        String currentMessage = getChatText();
        clientThread.sendMessage(currentMessage, -1);

        chatText.set("");

    }

    /**
     * setter for smash bot
     */
    public void setSmashBot() {}

    /**
     * setter for hulk x90 bot
     */
    public void setHulk() {}

    /**
     * setter for spin Bot
     */
    public void setSpinBot() {}

    /**
     * setter for hammer bot
     */
    public void setHammerBot() {}

    /**
     * setter for twonky bot
     */
    public void setTwonky() {}

    /**
     * setter for zoom bot
     */
    public void setZoomBot() {}

    public void submitUserNameAndRobot() {}

    public void setReady() {}


}
