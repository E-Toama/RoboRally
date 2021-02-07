package client.view;
import client.viewmodel.GameOverModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


/**
 *  Controller class of the GameOverScreen
 * @author
 */
public class GameOverController {

    private final GameOverModel gameOverModel = new GameOverModel();

    @FXML private GridPane gameOverPane;

    @FXML private Text gameOverText;

    @FXML private Label winnerLabel;


    @FXML
    public void initialize() {

        winnerLabel.textProperty().bindBidirectional(gameOverModel.winnerNameProperty());


    }


}


