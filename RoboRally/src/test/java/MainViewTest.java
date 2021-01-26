import client.network.ClientThread;
import client.utilities.BoardTile;
import client.view.EnemyMatView;
import client.view.GameBoardController;
import client.view.MainViewController;
import client.view.PlayerMatView;
import client.view.ProgrammingController;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.InGameChatModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.PlayerMatModel;
import client.viewmodel.ProgrammingViewModel;
import game.gameboard.BoardElement;
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
    PlayerMatModel playerMatModel;
    GameBoardViewModel gameBoardViewModel;
    InGameChatModel inGameChatModel;
    EnemyMatModel enemyMatModel;
    ProgrammingViewModel programmingViewModel;


    GridPane gridPane;
    GridPane playerMat;
    GridPane programmingPane;
    boolean isPlayerMatActive = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Dummy Input for ProgrammingView
        String[] cardArray = new String[]{"MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};

        //Initialize the subviews
        ProgrammingViewModel programmingViewModel = new ProgrammingViewModel(cardArray);
        ProgrammingController programmingController = new ProgrammingController(programmingViewModel);

        MainViewModel mainViewModel = new MainViewModel(programmingViewModel);
        ClientThread clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(mainViewModel);

        MainViewController mainViewController = new MainViewController();
        GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();
        GameBoardController gameBoardController = new GameBoardController();
        gameBoardViewModel.setGameBoardController(gameBoardController);
        gameBoardViewModel.setGameBoard(new GameBoard("DizzyHighway").getGameBoard());
        gameBoardController.setGameBoardViewModel(gameBoardViewModel);
        gameBoardController.initBoard();
        mainViewController.initializeMainView();
        mainViewController.setGameBoardPane(gameBoardController.getGameGrid());
        //Single operations
        gameBoardController.initStartingPoints();
        gameBoardViewModel.setStartingPosition(4, 53);


/*        Button sceneSwitcher = new Button("SWITCH");
        sceneSwitcher.setOnAction(e -> switchScenes());

        otherPlayers.getChildren().add(sceneSwitcher);

        gridPane.add(otherPlayers, 2,0);

        programmingPane = programmingController.getGridPane();

       */

        Scene scene = new Scene(mainViewController.getMainViewPane());
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
