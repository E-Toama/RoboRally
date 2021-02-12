package client.viewmodel;

import client.utilities.RobotImageBuilder;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * ViewModel class for the GameOver window
 * 
 */
public class GameOverModel {

    private final StringProperty winnerName = new SimpleStringProperty();

    private final ObjectProperty<Image> robotImage = new SimpleObjectProperty<>();


    public StringProperty winnerNameProperty() {

        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName.set(winnerName);

    }

    public void setWinnerRobot(int winnerRobot) {
        Image winnerImage = RobotImageBuilder.buildWinnerImage(winnerRobot);
        this.robotImage.set(winnerImage);
    }

    public ObjectProperty<Image> winnerRobotProperty() {
        return robotImage;
    }
}
