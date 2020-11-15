package viewModel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashSet;
import java.util.Set;

public class WelcomeViewViewModel {

    //Property Instanzen mit getter und setter
    private StringProperty userName = new SimpleStringProperty();

    private BooleanProperty submitButton = new SimpleBooleanProperty();

    public StringProperty userNameProperty() {
        return  userName;
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

}
