import client.network.ClientThread;
import client.view.EnemyMatView;
import client.view.MainViewController;
import client.view.PlayerMatView;
import client.view.ProgrammingController;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.ProgrammingViewModel;
import game.gameboard.GameBoard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MainViewTest extends Application {

    MainViewModel mainViewModel;
    GridPane gridPane;
    GridPane playerMat;
    GridPane programmingPane;
    boolean isPlayerMatActive = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String[] cardArray = new String[]{"MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};
        ProgrammingViewModel programmingViewModel = new ProgrammingViewModel(cardArray);
        ProgrammingController programmingController = new ProgrammingController(programmingViewModel);
        MainViewModel mainViewModel = new MainViewModel(programmingViewModel);
        ClientThread clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(mainViewModel);
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/MainView.fxml"));
        gridPane = mainLoader.load();
        FXMLLoader playerMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/PlayerMat.fxml"));
        playerMat = playerMatLoader.load();
        GridPane boardView = new GameBoardViewModel().createGameBoardView(new GameBoard("DizzyHighway").getGameBoard());

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameViewChat.fxml"));
        GridPane chatPane = chatLoader.load();
        gridPane.add(boardView, 1, 0);
        gridPane.add(playerMat, 0, 1, 1, 2);
        gridPane.add(chatPane, 0, 0);
        VBox otherPlayers = new VBox();
        Logger logger = Logger.getLogger("MyLogger");
        System.out.println(logger.getName());
        logger.log(Level.FINE, "otherPlayer built");


        for (int i = 0; i < 3; i++) {
            EnemyMatView enemyMatView = new EnemyMatView();
            //Zwischensschritt: java -> javafx
            otherPlayers.getChildren().add(enemyMatView.getEnemyMat());
        }

        Button sceneSwitcher = new Button("SWITCH");
        sceneSwitcher.setOnAction(e -> switchScenes());

        otherPlayers.getChildren().add(sceneSwitcher);

        gridPane.add(otherPlayers, 2,0);

        programmingPane = programmingController.getGridPane();


        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    public void switchScenes(){
        if (isPlayerMatActive) {
            isPlayerMatActive = false;
            gridPane.getChildren().remove(playerMat);
            gridPane.add(programmingPane, 0, 1, 1, 2);
        } else {
            isPlayerMatActive = true;
            gridPane.getChildren().remove(programmingPane);
            gridPane.add(playerMat, 0, 1, 1, 2);
        }

    }
}
