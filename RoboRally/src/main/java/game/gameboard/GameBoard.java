package game.gameboard;

import game.gameboard.boards.DizzyHighway;
import game.gameboard.boards.ExtraCrispy;
import game.gameboard.boards.StartBoard;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import game.gameboard.gameboardfieldobjects.RestartPointFieldObject;
import game.utilities.Position;
import game.utilities.PositionLookUp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GameBoard {

    private String boardName;

    private BoardElement[][] gameBoard;

    private Position antennaPosition;
    private BoardElement restartPoint;

    private final HashMap<Position, BoardElement> blueConveyorBelts = new HashMap<>();
    private final HashMap<Position, BoardElement> blueRotatingConveyorBelts = new HashMap<>();
    private final HashMap<Position, BoardElement> greenConveyorBelts = new HashMap<>();
    private final HashMap<Position, BoardElement> greenRotatingConveyorBelts = new HashMap<>();
    private final HashMap<Position, BoardElement> pushPanels = new HashMap<>();
    private final HashMap<Position, BoardElement> gears = new HashMap<>();
    private final HashMap<Position, BoardElement> lasers = new HashMap<>();
    private final HashMap<Position, BoardElement> checkPoints = new HashMap<>();
    private final HashMap<Position, BoardElement> energySpaces = new HashMap<>();
    private final HashMap<Position, BoardElement> startingPoints = new HashMap<>();

    public GameBoard(String board) {

        this.boardName = board;

        switch (board) {
            case "DizzyHighway" -> this.gameBoard = createDizzyHighway();
            case "ExtraCrispy" -> this.gameBoard = createExtraCrispy();
        }

        initializeMaps();

    }

    /**
     * This method returns the gameboardElements:
     */
    public String getBoardName() {
        return boardName;
    }
    /**
     * This methods returns the AntennaPosition:
     */
    public Position getAntennaPosition() {
        return antennaPosition;
    }

    /**
     * These Getters return boardElements:
     */
    public BoardElement getRestartPoint() {
        return restartPoint;
    }

    public HashMap<Position, BoardElement> getBlueConveyorBelts() {
        return blueConveyorBelts;
    }

    public HashMap<Position, BoardElement> getBlueRotatingConveyorBelts() {
        return blueRotatingConveyorBelts;
    }

    public HashMap<Position, BoardElement> getGreenConveyorBelts() {
        return greenConveyorBelts;
    }

    public HashMap<Position, BoardElement> getGreenRotatingConveyorBelts() {
        return greenRotatingConveyorBelts;
    }

    public HashMap<Position, BoardElement> getPushPanels() {
        return pushPanels;
    }

    public HashMap<Position, BoardElement> getGears() {
        return gears;
    }

    public HashMap<Position, BoardElement> getLasers() {
        return lasers;
    }

    public HashMap<Position, BoardElement> getCheckPoints() {
        return checkPoints;
    }

    public HashMap<Position, BoardElement> getEnergySpaces() {
        return energySpaces;
    }

    public HashMap<Position, BoardElement> getStartingPoints() {
        return startingPoints;
    }

    public GameBoardMapObject[] toMap() {

        int length = gameBoard.length * gameBoard[0].length;
        GameBoardMapObject[] returnValue = new GameBoardMapObject[length];

        int x = 0;

        for (int i = 0; i < gameBoard.length; i++) {

            for (int j = 0; j < gameBoard[i].length; j++) {

                returnValue[x] = gameBoard[i][j].returnGameBoardMapObject();
                x++;

            }

        }

        return returnValue;

    }

    /**
     * Second Constructor for recreating a GameBoard from JSON
     * Only used as fallback solution if MapSelected-Message fails
     *      * WARNING: Hard-coded size of GameBoard-array (10 * 13)
     *      * (because it is impossible to guess the board-format from a one-dimensional list)
     *      * @param map
     * @param map Array of GameBoardMapObjects from JSON-Message
     */
    public GameBoard(GameBoardMapObject[] map) {
        gameBoard = new BoardElement[10][13];
        StartBoard startBoard = new StartBoard();

        //Check if transmitted map is ExtraCrispy and Startboard needs to be modified
        //ExtraCrispy-Positon 3 is Empty and therefore different from DizzyHighway
        if ("Empty".equals(map[3].getField()[0].getType())) {
            startBoard.startBoard[0][0] = new BoardElement(4, new GameBoardFieldObject[]{new RestartPointFieldObject("right")});
        }

        int mapIndexOfMainBoard = 0;
        int positionIncludingStartBoard = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (j < 3) {
                    gameBoard[i][j] = startBoard.startBoard[i][j];
                } else {
                    gameBoard[i][j] = new BoardElement(positionIncludingStartBoard, map[mapIndexOfMainBoard].getField());
                    mapIndexOfMainBoard++;
                }
                positionIncludingStartBoard++;

            }
        }
    }

    /**
     * this method returns the gameboard
     * @return gameBoard
     */
    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * this method returns the DizzyHighway gameBoard
     * @return BoardElement[][] dizzyHighway;
     */
    private BoardElement[][] createDizzyHighway() {
        BoardElement[][] dizzyHighway = new BoardElement[10][13];
        StartBoard startBoard = new StartBoard();
        DizzyHighway dizzyHighway1 = new DizzyHighway();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (j < 3) {
                    dizzyHighway[i][j] = startBoard.startBoard[i][j];
                } else {
                    dizzyHighway[i][j] = dizzyHighway1._5B[i][j-3];
                }
            }
        }
        return dizzyHighway;
    }
    /**
     * this method returns the ExtraCrispy gameBoard
     * @return BoardElement[][] ExtraCrispy;
     */
    private BoardElement[][] createExtraCrispy() {
        BoardElement[][] extraCrispy = new BoardElement[10][13];
        BoardElement restartTile = new BoardElement(0, new GameBoardFieldObject[]{new RestartPointFieldObject("right")});
        StartBoard startBoard = new StartBoard();
        ExtraCrispy extraCrispy1 = new ExtraCrispy();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (j < 3) {
                    extraCrispy[i][j] = (i == 0 && j == 0) ? restartTile : startBoard.startBoard[i][j];
                } else {
                    extraCrispy[i][j] = extraCrispy1._5B[i][j-3];
                }
            }
        }
        return extraCrispy;
    }
    /**
     * this method sets the position of all boardElements and adds them to the respective Hashmap<position, boardElements>.
     */
    private void initializeMaps() {

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 13; j++) {

                BoardElement boardElement = gameBoard[i][j];

                Position position = boardElement.getXY();

                if (boardElement.isAntenna()) {

                    this.antennaPosition = position;

                }

                if (boardElement.isRestartPoint()) {

                    this.restartPoint = boardElement;

                }

                if (boardElement.isGear()) {

                    gears.put(position, boardElement);

                }

                if (boardElement.isBlueConveyorBelt()) {

                    blueConveyorBelts.put(position, boardElement);

                }

                if (boardElement.isBlueRotatingConveyorBelt()) {

                    blueRotatingConveyorBelts.put(position, boardElement);

                }

                if (boardElement.isGreenConveyorBelt()) {

                    greenConveyorBelts.put(position,boardElement);

                }

                if (boardElement.isGreenRotatingConveyorBelt()) {

                    greenRotatingConveyorBelts.put(position, boardElement);

                }

                if (boardElement.isPushPanel()) {

                    pushPanels.put(position, boardElement);

                }

                if (boardElement.isLaser()) {

                    lasers.put(position, boardElement);

                }

                if (boardElement.isControlPoint() > 0) {

                    checkPoints.put(position, boardElement);

                }

                if (boardElement.isEnergySpace()) {

                    energySpaces.put(position, boardElement);

                }

                if (boardElement.isStartingPoint()) {

                    startingPoints.put(position, boardElement);

                }

            }

        }


    }


}
