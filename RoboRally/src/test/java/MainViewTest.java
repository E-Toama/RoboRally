import client.network.ClientThread;
import client.view.GameBoardController;
import client.view.MainViewController;
import client.view.ProgrammingController;
import client.view.ViewController;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.InGameChatModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.PlayerMatModel;
import client.viewmodel.ProgrammingViewModel;
import game.gameboard.GameBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;

public class MainViewTest extends Application {


    int otherPlayerMats = 3;
    boolean programmingPhase = true;  // false = show PlayerMat, true = show ProgrammingMat
    String track = "DizzyHighway"; // "ExtraCrispy" or "DizzyHighway"

    String[] testCardsForProgrammingView = new String[]{"MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        MainViewModel mainViewModel = new MainViewModel();
        mainViewModel.getMainViewController().initializeMainView(otherPlayerMats);

        GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();
        gameBoardViewModel.setGameBoard(new GameBoard(track).getGameBoard());
        gameBoardViewModel.getGameBoardController().initBoard();

        mainViewModel.getMainViewController().setGameBoardPane(gameBoardViewModel.getGameBoardController().getGameGrid());

        if (programmingPhase) {
            ProgrammingViewModel programmingViewModel = new ProgrammingViewModel();
            Collections.shuffle(Arrays.asList(testCardsForProgrammingView));
            programmingViewModel.setCards(testCardsForProgrammingView);
            programmingViewModel.getProgrammingController().createCards();
            GridPane pane = programmingViewModel.getProgrammingController().getGridPane();
            mainViewModel.getMainViewController().setProgrammingPane(pane);
            mainViewModel.getMainViewController().switchScenes();
        }


        Scene scene = new Scene(mainViewModel.getMainViewController().getMainViewPane());
        stage.setScene(scene);
        stage.show();

    }

}
