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
 * View Model for the initial Welcome Screen. The class receives the username from WelcomeView
 * and passes it on to the Client Constructor. If the name is available, a new Client is created
 * and the ChatView is initialized.
 * @author Josef, Elias
 */
public class WelcomeViewViewModel {

    // Properties
    private StringProperty userName = new SimpleStringProperty();
    private BooleanProperty submitButton = new SimpleBooleanProperty();

    /**
     * Access the Property of the userName-field
     * @return The userName-Property
     */
    public StringProperty userNameProperty() {
        return userName;
    }

    /**
     * Access the Property of the submitButton
     * @return the submitButton-Property
     */
    public BooleanProperty submitButtonProperty() {
        return submitButton;
    }

    /**
     * Access the actual username
     * @return the given username as String
     */
    public final String getUserName() {
        return userName.get();
    }

    /**
     * Set a new username
     * @param newUserName The new username.
     */
    public final void setUserName(String newUserName) {
        userName.set(newUserName);
    }


    /**
     * Construct Client and initialize ChatView
     *
     * @param stage passed from WelcomeView to set up ChatView
     * @throws DuplicateNameException if name already taken (caught in WelcomeView)
     */
    public void submitUserName(Stage stage) throws DuplicateNameException {

        try {
            Client client = new Client(getUserName());  //throws Exception

            stage.setTitle(getUserName());
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/views/ChatView.fxml"));

            stage.setScene(new Scene(loader.load()));

            ChatView chatView = loader.getController();
            chatView.setClient(client);
            stage.show();

            //Close Window on "!BYE"
            stage.setOnCloseRequest(e -> {
                client.writeToServer("!BYE");
                stage.close();
                System.exit(0);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
