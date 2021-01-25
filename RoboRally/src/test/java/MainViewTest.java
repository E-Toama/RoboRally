import client.view.MainViewController;
import client.view.PlayerMatView;
import client.view.ProgrammingController;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.ProgrammingViewModel;
import game.gameboard.GameBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainViewTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/MainView.fxml"));
        GridPane gridPane = mainLoader.load();
        FXMLLoader playerMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/PlayerMat.fxml"));
        GridPane playerMat = playerMatLoader.load();
        GridPane boardView = new GameBoardViewModel().createGameBoardView(new GameBoard("DizzyHighway").getGameBoard());

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameViewChat.fxml"));
        GridPane chatPane = chatLoader.load();
        gridPane.add(boardView, 1, 0);
        gridPane.add(playerMat, 0, 1, 1, 2);
        gridPane.add(chatPane, 0, 0);
        VBox otherPlayers = new VBox();
        for (int i = 0; i < 3; i++) {
            PlayerMatView playerMatView = new PlayerMatView();
            otherPlayers.getChildren().add(playerMatView.getPlayerMat());
        }

        gridPane.add(otherPlayers, 2,0);

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
