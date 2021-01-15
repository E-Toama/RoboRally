package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

public class LobbyViewModel {

    private ClientThread clientThread;
    private StringProperty chatText = new SimpleStringProperty();
    private StringProperty userNameTextField = new SimpleStringProperty();
    private Property dropDown= new SimpleObjectProperty<>();

    public final ObservableList<MenuItem> dropDownItems = FXCollections.observableArrayList();


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

    public Property<Node> dropDownProperty() {

        return dropDown;
    }

    public ObservableList<MenuItem> addMenuItems(ObservableList<String> list) {
        for (String temp : list) {
            dropDownItems.add(new MenuItem(temp));
        }
        return dropDownItems;

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
