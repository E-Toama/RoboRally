package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatViewModel {

    private final ClientThread clientThread;
    private final StringProperty chatText = new SimpleStringProperty();

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

}
