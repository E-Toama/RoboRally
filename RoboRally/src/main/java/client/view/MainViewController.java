package client.view;

import client.viewmodel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainViewController {

    private MainViewModel mainViewModel = new MainViewModel(this);

    @FXML
    GridPane mainViewPane;
    Parent playerMatPane;
    Parent programmingPane;
    private GridPane gameBoardPane;
    private boolean isPlayerMatActive = true;


    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;

    }

    public void setChatPane(Parent chatPane) {
        mainViewPane.add(chatPane, 0, 0);
    }

    public void setProgrammingPane(GridPane programmingPane) {
        this.programmingPane = programmingPane;
    }

    public void setGameBoardPane(GridPane gameBoardPane) {
        this.gameBoardPane = gameBoardPane;
        mainViewPane.add(gameBoardPane, 1, 0);
    }

    public void setPlayerMatPane(Parent playerMatPane) {
        this.playerMatPane = playerMatPane;
        mainViewPane.add(this.playerMatPane, 0, 1, 1, 2);

    }

    public GridPane getMainViewPane() {
        return mainViewPane;
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

    public void setEnemyPane(GridPane enemyMatPane) {
        mainViewPane.add(enemyMatPane, 2, 0, 2, 1);
    }
}
