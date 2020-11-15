package viewModel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;

public class ChatViewViewModel {

    //Properties
    private StringProperty message = new SimpleStringProperty();
    private BooleanProperty sendButton = new SimpleBooleanProperty();
    private Thread clientThread;
    private Client client;


    public void setClient(Client client) {

        this.client = client;
        this.clientThread = new Thread(client);
        clientThread.start();

    }

    public Client getClient() {

        return client;

    }

    //Property Instanzen mit getter und setter
    public StringProperty messageProperty() {
        return  message;
    }

    public final String getMessage() {
        return message.get();
    }

    public final void setMessage(String newMessage) {
        message.set(newMessage);
    }

    public BooleanProperty sendButtonProperty() {
        return sendButton;
    }

    public final Boolean getSendButton() {
        return sendButton.get();
    }

    public final void setSendButton(Boolean value) {
        sendButton.set(value);
    }

    //Message wird an den Server weitergeleitet, Textfeld (writeField) wird geleert
    public final void sendMessage() {
        String currentMessage = getMessage();
        client.writeToServer(currentMessage);
        if (currentMessage.startsWith("bye")) {
            Platform.exit();
        } else {

            this.setMessage("");
        }

    }

}
