package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.awt.*;

public class LobbyViewModel {

    private ClientThread clientThread;
    private StringProperty chatText = new SimpleStringProperty();
    private StringProperty userNameTextField = new SimpleStringProperty();


    public LobbyViewModel() {

        this.clientThread = ClientThread.getClientThread();

    }

    public ClientThread getClientThread() {
        return clientThread;
    }

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

    public void sendMessage() {

        String currentMessage = getChatText();
        //ToDo: send message to single (chosen) player


        clientThread.sendMessage(currentMessage, -1);

        chatText.set("");

    }

    public void setSmashBot() {}

    public void setHulk() {}

    public void setSpinBot() {}

    public void setHammerBot() {}

    public void setTwonky() {}

    public void setZoomBot() {}

    public void submitUserNameAndRobot() {}

    public void setReady() {}

}
