import client.network.ClientThread;
import client.view.GameBoardController;
import client.view.MainViewController;
import client.view.ProgrammingController;
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

public class MainViewTest extends Application {

    MainViewModel mainViewModel;
    PlayerMatModel playerMatModel;
    GameBoardViewModel gameBoardViewModel;
    InGameChatModel inGameChatModel;
    EnemyMatModel enemyMatModel;
    ProgrammingViewModel programmingViewModel;

    Stage stage;
    GridPane gridPane;
    GridPane playerMat;
    GridPane programmingPane;
    boolean isPlayerMatActive = true;
    MainViewController mainViewController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        //Dummy Input for ProgrammingView
        String[] cardArray = new String[]{"MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};

        ProgrammingController programmingController = new ProgrammingController();
        //programmingController.setProgrammingViewModel(new ProgrammingViewModel(cardArray));
        MainViewModel mainViewModel = new MainViewModel();
        ClientThread clientThread = ClientThread.getInstance();
        clientThread.setMainViewModel(mainViewModel);

        mainViewController = new MainViewController();
        GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();
        GameBoardController gameBoardController = new GameBoardController();
        gameBoardViewModel.setGameBoardController(gameBoardController);
        gameBoardViewModel.setGameBoard(new GameBoard("DizzyHighway").getGameBoard());
        gameBoardController.setGameBoardViewModel(gameBoardViewModel);
        gameBoardController.initBoard();
        mainViewController.initializeMainView(3);
        mainViewController.setGameBoardPane(gameBoardController.getGameGrid());
        //Single operations
        gameBoardController.initStartingPoints();
        gameBoardViewModel.setStartingPosition(4, 53);


        Button sceneSwitcher = new Button("SWITCH");
        sceneSwitcher.setOnAction(e -> switchScenes());

        programmingPane = programmingController.getGridPane();
        programmingPane.add(sceneSwitcher, 1,0);
        mainViewController.getOtherPlayers().getChildren().add(sceneSwitcher);

        Scene scene = new Scene(mainViewController.getMainViewPane());
        stage.setScene(scene);
        stage.show();


    }

    public void switchScenes(){
        if (isPlayerMatActive) {
            isPlayerMatActive = false;
            stage.setScene(new Scene(programmingPane));
        } else {
            isPlayerMatActive = true;
            Scene scene = new Scene(mainViewController.getMainViewPane());
            stage.setScene(scene);

        }
        stage.show();

    }
}
