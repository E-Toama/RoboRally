package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;



public class ChatViewModel {

    private static ClientThread clientThread;
    private final StringProperty chatText = new SimpleStringProperty();
    private int destination = -1;

    public ChatViewModel() {

        this.clientThread = ClientThread.getInstance();
        this.clientThread.setChatViewModel(this);

    }

    public String getChatText() {
        return chatText.get();
    }

    public StringProperty chatTextProperty() {
        return chatText;
    }

    public static ClientThread getClientThread() {
        return clientThread;
    }

    public void sendChat() {

        clientThread.sendMessage(getChatText(), destination);
        chatText.set("");
    }

    public void setReady() {
        if(clientThread.getPlayer().getStatus()) {
            clientThread.sendPlayerStatus(false);
        } else {
            clientThread.sendPlayerStatus(true);
        }
    }

    public void changeDestination(int destination) {

        this.destination = destination;

    }



}
