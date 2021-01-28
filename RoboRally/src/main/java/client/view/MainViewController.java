package client.view;

import client.viewmodel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {


    @FXML
    GridPane mainViewPane;
    VBox otherPlayers;
    GridPane playerMatPane;
    GridPane programmingPane;
    private GridPane gameBoardPane;
    private boolean isPlayerMatActive = true;
    private MainViewModel mainViewModel;


    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;

    }

    public void initializeMainView(int playerCount) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/MainView.fxml"));
        mainViewPane = mainLoader.load();

        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameViewChat.fxml"));
        GridPane chatPane = chatLoader.load();
        mainViewPane.add(chatPane, 0, 0);

        otherPlayers = new VBox();
        for (int i = 0; i < playerCount; i++) {
            FXMLLoader enemyMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/EnemyMat.fxml"));
            GridPane enemyMatPane = enemyMatLoader.load();
            otherPlayers.getChildren().add(enemyMatPane);
        }
        mainViewPane.add(otherPlayers, 2, 0);
    }

    public void setProgrammingPane(GridPane programmingPane) {
        this.programmingPane = programmingPane;
    }

    public GridPane getMainViewPane() {
        return mainViewPane;
    }

    public void setGameBoardPane(GridPane gameBoardPane) {
        this.gameBoardPane = gameBoardPane;
        mainViewPane.add(gameBoardPane, 1, 0);
    }

    public void setPlayerMatPane(GridPane playerMatPane) {
        this.playerMatPane = playerMatPane;
        mainViewPane.add(this.playerMatPane, 0, 1, 1, 2);

    }

    public VBox getOtherPlayers() {
        return otherPlayers;
    }

    public void switchScenes() {
        if (isPlayerMatActive) {
            isPlayerMatActive = false;
            mainViewPane.getChildren().remove(playerMatPane);
            mainViewPane.add(programmingPane, 0, 1, 1, 2);
        } else {
            isPlayerMatActive = true;
            mainViewPane.getChildren().remove(programmingPane);
            mainViewPane.add(playerMatPane, 0, 1, 1, 2);
        }

    }

}
