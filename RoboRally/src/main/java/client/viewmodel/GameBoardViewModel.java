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

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameBoardViewModel {

    ClientThread clientThread;
    GameBoardController gameBoardController;
    BoardElement[][] gameBoard;
    HashSet<Integer> startingPositions;

    public GameBoardViewModel() {
        this.clientThread = ClientThread.getInstance();
        clientThread.setGameBoardViewModel(this);
        initStartingPositions();
    }

    private void initStartingPositions() {
        startingPositions = new HashSet<>();
        startingPositions.add(14);
        startingPositions.add(39);
        startingPositions.add(53);
        startingPositions.add(66);
        startingPositions.add(78);
        startingPositions.add(105);
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

    public HashSet<Integer> getStartingPositions() {
        return startingPositions;
    }

    public void transmitStartingPosition(int position) {
        clientThread.sendStartingPosition(position);
    }

    public void setStartingPosition(int robotFigure, int position) {
        startingPositions.remove(position);
        gameBoardController.setStartingPosition(robotFigure, position);
        for (Integer i : startingPositions) {
            System.out.println("Positions in Model: " + i);
        }

    }

    public void showStartingPoints() {
        gameBoardController.initStartingPoints();
    }

    public void updateRobotPosition(int robotFigure, int oldRow, int oldColumn, int newRow, int newColumn) {
       gameBoardController.updateRobotPosition(robotFigure, oldRow, oldColumn, newRow, newColumn);
    }
}
