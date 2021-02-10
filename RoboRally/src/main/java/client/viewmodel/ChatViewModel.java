package client.viewmodel;

import client.network.ClientThread;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;

/**
 * ViewModel Class for the Lobby and Ingame Chat
 * contains methods responsible for sending chat messages
 * @author
 */
public class ChatViewModel {

    private static ClientThread clientThread;
    private final StringProperty chatText = new SimpleStringProperty();
    private int destination = -1;

    /**
     * Constructor for the ChatViewModel with clientThread
     */
    public ChatViewModel() {

        this.clientThread = ClientThread.getInstance();
        this.clientThread.setChatViewModel(this);

    }

    /**
     * gets the sent chat messages
     * @return chat messages
     */
    public String getChatText() {
        return chatText.get();
    }


    public StringProperty chatTextProperty() {
        return chatText;
    }

    /**
     * gets current ClientThread
     * @return ClientThread clientThread
     */
    public static ClientThread getClientThread() {
        return clientThread;
    }


    /**
     * responsible for sending the chat messages and clearing the chatwindow afterwards
     *
     */
    public void sendChat() {

        clientThread.sendMessage(getChatText(), destination);
        chatText.set("");
    }

    /**
     *
     * sets the player true, if s/he is ready to play
     */
    public void setReady() {
        if(clientThread.getPlayer().getStatus()) {
            clientThread.sendPlayerStatus(false);
        } else {
            clientThread.sendPlayerStatus(true);
        }
    }

    /**
     * sets the destination accordingly to the chosen dropdown menu value
     * @param destination
     */
    public void changeDestination(int destination) {

        this.destination = destination;

    }



}
