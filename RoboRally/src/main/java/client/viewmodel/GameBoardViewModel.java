package client.viewmodel;

import client.network.ClientThread;
import client.utilities.BoardTile;
import client.utilities.ImageBuilder;
import client.utilities.RobotImageBuilder;
import client.view.BoardTileView;
import client.view.GameBoardController;
import game.gameboard.BoardElement;
import game.gameboard.GameBoard;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameBoardViewModel {

    ClientThread clientThread;
    GameBoardController gameBoardController;
    BoardElement[][] gameBoard;

    public GameBoardViewModel() {
        this.clientThread = ClientThread.getInstance();
        clientThread.setGameBoardViewModel(this);
    }

    public void setGameBoard(BoardElement[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    public void setStartingPosition(int robotFigure, int row, int column) {
        gameBoardController.setStartingPosition(robotFigure, row, column);

    }

    public void updateRobotPosition(int robotFigure, int oldRow, int oldColumn, int newRow, int newColumn) {
       gameBoardController.updateRobotPosition(robotFigure, oldRow, oldColumn, newRow, newColumn);
    }
}
