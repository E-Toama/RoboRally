package viewModel;

import exceptions.DuplicateNameException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.network.Client;
import views.ChatView;

import java.io.IOException;

/**
 * @author Josef, Elias
 */
public class WelcomeViewViewModel {

    // Property Instanzen mit getter und setter
    private StringProperty userName = new SimpleStringProperty();

    private BooleanProperty submitButton = new SimpleBooleanProperty();

    public StringProperty userNameProperty() {
        return userName;
    }

    public final String getUserName() {
        return userName.get();
    }

    public final void setUserName(String newUserName) {
        userName.set(newUserName);
    }

    public BooleanProperty submitButtonProperty() {
        return submitButton;
    }

    public final Boolean getSubmitButton() {
        return submitButton.get();
    }

    public final void setSubmitButton(Boolean value) {
        submitButton.set(value);
    }

    /**
     * Construct Client and initialize ChatView
     *
     * @param stage passed from WelcomeView
     * @throws DuplicateNameException if name already taken (caught in WelcomeView)
     */
    public void submitUserName(Stage stage) throws DuplicateNameException {

        try {
            Client client = new Client(getUserName());
            stage.setTitle(getUserName());

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/views/ChatView.fxml"));

            stage.setScene(new Scene(loader.load()));

            ChatView chatView = loader.getController();
            chatView.setClient(client);
            stage.show();

            //Close Window on "bye"
            stage.setOnCloseRequest(e -> {
                client.writeToServer("!BYE");
                stage.close();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
