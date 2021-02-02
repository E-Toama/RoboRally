import client.view.PopupController;
import javafx.application.Application;
import javafx.stage.Stage;
import server.network.TestMessages;

public class MainViewTest extends Application {


    int otherPlayerMats = 3;
    boolean programmingPhase = false;  // false = show PlayerMat, true = show ProgrammingMat
    String track = "DizzyHighway"; // "ExtraCrispy" or "DizzyHighway"
    String[] availableMaps = {"DizzyHighway", "ExtraCrispy"};
    String[] testCardsForProgrammingView = TestMessages.testCardsForProgrammingView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        /*ClientThread clientThread = ClientThread.getInstance();
        clientThread.initializeEmptyMainView();
        MainViewModel mainViewModel = clientThread.getMainViewModel();*/

        PopupController popupController = new PopupController();
        popupController.showPickDamage(2, TestMessages.availableDamageCards);

       /*
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
        stage.show();*/

    }

}
