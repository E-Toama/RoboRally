package client.view;

import client.viewmodel.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controller class of the complete mainView / game window
 * 
 */
public class MainViewController {

    private MainViewModel mainViewModel = new MainViewModel(this);

    @FXML
    GridPane mainViewPane;
    Parent playerMatPane;
    Parent programmingPane;
    private GridPane gameBoardPane;
    private boolean isPlayerMatActive = true;


    /**
     * gets the mainViewModel
     * @return MainViewModel mainViewModel
     */
    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;

    }

    /**
     * sets the pane for the ingame chat on the top left
     * @param chatPane
     */
    public void setChatPane(Parent chatPane) {
        mainViewPane.add(chatPane, 0, 0);
    }

    /**
     * sets the pane for the programming mat
     * @param programmingPane
     */
    public void setProgrammingPane(GridPane programmingPane) {
        this.programmingPane = programmingPane;
    }

    /**
     * sets the pane for the gameBoard in the middle
     * @param gameBoardPane
     */
    public void setGameBoardPane(GridPane gameBoardPane) {
        this.gameBoardPane = gameBoardPane;
        mainViewPane.add(gameBoardPane, 1, 0);
    }

    /**
     * sets the pane for the player mat
     * @param playerMatPane
     */
    public void setPlayerMatPane(Parent playerMatPane) {
        this.playerMatPane = playerMatPane;
        mainViewPane.add(this.playerMatPane, 0, 1, 1, 2);

    }

    /**
     * @return GridPane mainViewPane
     */
    public GridPane getMainViewPane() {
        return mainViewPane;
    }

    /**
     * switches from player mat to programming mat in the programming phase
     *
     */
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

    /**
     * sets the pane for the enemy player mats on the right side
     * @param enemyMatPane
     */
    public void setEnemyPane(GridPane enemyMatPane) {
        mainViewPane.add(enemyMatPane, 2, 0, 2, 1);
    }
}
