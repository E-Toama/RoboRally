package client.view;
import client.viewmodel.GameOverModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


/**
 *  Controller class of the GameOverScreen
 * 
 */
public class GameOverController {

    private final GameOverModel gameOverModel = new GameOverModel();

    @FXML private GridPane gameOverPane;

    @FXML private Text gameOverText;

    @FXML private Label winnerLabel;

    @FXML private ImageView winnerRobotImage;


    @FXML
    public void initialize() {
        winnerLabel.textProperty().bindBidirectional(gameOverModel.winnerNameProperty());
        winnerRobotImage.imageProperty().bindBidirectional(gameOverModel.winnerRobotProperty());
    }

    public GameOverModel getGameOverModel() {
        return gameOverModel;
    }
}


