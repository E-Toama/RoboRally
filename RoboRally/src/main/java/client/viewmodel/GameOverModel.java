package client.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author
 */
public class GameOverModel {

    private final StringProperty winnerName = new SimpleStringProperty();


    public StringProperty winnerNameProperty() {

        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName.set(winnerName);

    }

}
