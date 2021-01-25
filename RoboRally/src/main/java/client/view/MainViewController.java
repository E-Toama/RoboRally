package client.view;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class MainViewController {

    /*private MainViewModel mainViewModel = new MainViewModel();
    private ProgrammingViewModel programmingViewModel;
    private GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();
    private ChatViewModel chatViewModel = new ChatViewModel();
    private PlayerMatModel playerMatModel = new PlayerMatModel();*/

    private GameBoardController gameBoardController;
    private PlayerMatView playerMatView;

    @FXML
    GridPane mainViewPane = new GridPane();


    public GridPane getMainViewPane() {
        return mainViewPane;
    }
}
