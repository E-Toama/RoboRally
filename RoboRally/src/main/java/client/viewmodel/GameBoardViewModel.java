package client.viewmodel;

import client.network.ClientThread;
import client.utilities.ClientLaserHandler;
import client.view.GameBoardController;
import game.gameboard.BoardElement;
import game.gameboard.GameBoard;

import java.util.HashSet;

/**
 * ViewModel class for the GameBoard
 * @author
 */
public class GameBoardViewModel {

    ClientThread clientThread;
    GameBoardController gameBoardController;

    BoardElement[][] gameBoard;
    HashSet<Integer> startingPositions;

    /**
     * Constructor for a GameBoardViewModel with client thread and gameBoardController
     */
    public GameBoardViewModel() {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setGameBoardViewModel(this);

        //Model <-> Controller
        gameBoardController = new GameBoardController();
        gameBoardController.setGameBoardViewModel(this);

        initStartingPositions();
    }

    /**
     * initializes starting point positions
     */
    private void initStartingPositions() {
        startingPositions = new HashSet<>();
        startingPositions.add(14);
        startingPositions.add(39);
        startingPositions.add(53);
        startingPositions.add(66);
        startingPositions.add(78);
        startingPositions.add(105);
    }

    /**
     * sets GameBoard with the right laser values
     * @param gameBoard
     */
    public void setGameBoard(GameBoard gameBoard) {

        ClientLaserHandler clientLaserHandler = new ClientLaserHandler();

        for (BoardElement laser : gameBoard.getLasers().values()) {
            clientLaserHandler.handleBoardLasers(gameBoard.getGameBoard(), laser);
        }

        this.gameBoard = clientLaserHandler.getUpdatedGameBoard();

        gameBoardController.initBoard();
    }

    /**
     * gets a gameBoard
     * @return gameBoard
     */
    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }


    public GameBoardController getGameBoardController() {
        return gameBoardController;
    }

    public HashSet<Integer> getStartingPositions() {
        return startingPositions;
    }

    /**
     * passes the chosen starting position
     * @param position
     */
    public void transmitStartingPosition(int position) {
        clientThread.sendStartingPosition(position);
    }

    /**
     * sets the robot figure on the chosen starting position
     * @param robotFigure ist he robot of the player
     * @param position the chosen starting position of the player
     */
    public void setStartingPosition(int robotFigure, int position) {
        startingPositions.remove(position);
        gameBoardController.setStartingPosition(robotFigure, position);
    }

    /**
     * sets the robot figure of the other players on the chosen starting position
     * @param robotFigure is a robot of the other players
     * @param position is the chosen starting position of the oter players
     */
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
