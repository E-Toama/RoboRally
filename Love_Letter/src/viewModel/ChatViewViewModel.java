package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;

public class ChatViewViewModel {

    private StringProperty message = new SimpleStringProperty();
    private BooleanProperty sendButton = new SimpleBooleanProperty();
    private Client client;

    public void setClient(Client client) {

        this.client = client;

    }

    public Client getClient() {

        return client;

    }

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

    public final void sendMessage() {

        System.out.println(getMessage());
        client.writeToServer(getMessage());
        this.setMessage("");

    }

}
