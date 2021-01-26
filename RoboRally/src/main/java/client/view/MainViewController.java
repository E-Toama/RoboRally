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
    private boolean isPlayerMatActive = true;




    public GridPane getMainViewPane() {
        return mainViewPane;
    }

   /* public void switchScenes(){
        if (isPlayerMatActive) {
            isPlayerMatActive = false;
            mainViewPane.getChildren().remove(playerMat);
            mainViewPane.add(programmingPane, 0, 1, 1, 2);
        } else {
            isPlayerMatActive = true;
            mainViewPane.getChildren().remove(programmingPane);
            mainViewPane.add(playerMat, 0, 1, 1, 2);
        }

    }*/
}
