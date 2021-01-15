package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;



public class ChatViewModel {

    private final ClientThread clientThread;
    private final StringProperty chatText = new SimpleStringProperty();
    public final ObservableList<MenuItem> dropDownItems = FXCollections.observableArrayList();

    public ChatViewModel() {

        this.clientThread = ClientThread.getClientThread();
        this.clientThread.setChatViewModel(this);

    }

    public String getChatText() {
        return chatText.get();
    }

    public StringProperty chatTextProperty() {
        return chatText;
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public void sendChat() {

        clientThread.sendMessage(getChatText(), -1);
        chatText.set("");
    }

    public void setReady() {}

    public ObservableList<MenuItem> addMenuItems(ObservableList<String> list) {
        for (String temp : list) {
            dropDownItems.add(new MenuItem(temp));
        }
        return dropDownItems;

    }

}
