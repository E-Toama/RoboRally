package client.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {

    /*private MainViewModel mainViewModel = new MainViewModel();
    private ProgrammingViewModel programmingViewModel;
    private GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();
    private ChatViewModel chatViewModel = new ChatViewModel();
    private PlayerMatModel playerMatModel = new PlayerMatModel();*/

    /*private PlayerMatView playerMatView;
    private ProgrammingController programmingController;
    private InGameChatController inGameChatController;
    private EnemyMatView enemyMatView;*/
    private GridPane gameBoardPane;

    @FXML
    GridPane mainViewPane;
    private boolean isPlayerMatActive = true;

    public void initializeMainView() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/MainView.fxml"));
        mainViewPane = mainLoader.load();

        FXMLLoader playerMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/PlayerMat.fxml"));
        GridPane playerMatPane = playerMatLoader.load();
        mainViewPane.add(playerMatPane, 0, 1, 1, 2);

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameViewChat.fxml"));
        GridPane chatPane = chatLoader.load();
        mainViewPane.add(chatPane, 0, 0);

        /*FXMLLoader gameBoardLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameBoardView.fxml"));
        gameBoardPane = gameBoardLoader.load();*/

        VBox otherPlayers = new VBox();
        for (int i = 0; i < 3; i++) {
            FXMLLoader enemyMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/EnemyMat.fxml"));
            GridPane enemyMatPane = enemyMatLoader.load();
            otherPlayers.getChildren().add(enemyMatPane);
        }
        mainViewPane.add(otherPlayers, 2,0);


    }

   /* public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    public void setPlayerMatView(PlayerMatView playerMatView) {
        this.playerMatView = playerMatView;
    }

    public void setProgrammingController(ProgrammingController programmingController) {
        this.programmingController = programmingController;
    }

    public void setInGameChatController(InGameChatController inGameChatController) {
        this.inGameChatController = inGameChatController;
    }

    public void setEnemyMatView(EnemyMatView enemyMatView) {
        this.enemyMatView = enemyMatView;
    }*/

    public GridPane getMainViewPane() {
        return mainViewPane;
    }

    public void setGameBoardPane(GridPane gameBoardPane) {
        this.gameBoardPane = gameBoardPane;
        mainViewPane.add(gameBoardPane, 1, 0);

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
