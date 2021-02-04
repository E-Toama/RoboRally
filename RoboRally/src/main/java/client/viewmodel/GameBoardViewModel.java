package client.viewmodel;

import client.network.ClientThread;
import client.view.GameBoardController;
import game.gameboard.BoardElement;

import java.util.HashSet;

public class GameBoardViewModel {

    ClientThread clientThread;
    GameBoardController gameBoardController;

    BoardElement[][] gameBoard;
    HashSet<Integer> startingPositions;

    public GameBoardViewModel() {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setGameBoardViewModel(this);

        //Model <-> Controller
        gameBoardController = new GameBoardController();
        gameBoardController.setGameBoardViewModel(this);

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
        gameBoardController.initBoard();
    }

    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }


    public GameBoardController getGameBoardController() {
        return gameBoardController;
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
    }

    public void setOtherRobotStartingPostion(int robotFigure, int position) {
        startingPositions.remove(position);
        gameBoardController.setOtherRobotStartingPosition(robotFigure, position);
    }

    public void showStartingPoints() {
        gameBoardController.initStartingPoints();
    }

    public void updateBoard() {
        gameBoardController.updateBoard();
    }

}
