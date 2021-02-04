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
    private final List<HashMap<Position, BoardElement>> laserAffected = new ArrayList<>();

    public GameBoard(String board) {

        switch (board) {
            case "DizzyHighway" -> this.gameBoard = createDizzyHighway();
            case "ExtraCrispy" -> this.gameBoard = createExtraCrispy();
        }

        initializeMaps();

    }

    public Position getAntennaPosition() {
        return antennaPosition;
    }

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

    public List<HashMap<Position, BoardElement>> getLaserAffected() {
        return laserAffected;
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
     *      * WARNING: Hard-coded size of GameBoard-array (10 * 13)
     *      * (because it is impossible to guess the board-format from a one-dimensional list)
     *      * @param map
     * @param map Array of GameBoardMapObjects from JSON-Message
     * @param selectedMap only needed for startBoard-Difference
     */
    public GameBoard(GameBoardMapObject[] map, String selectedMap) {
        gameBoard = new BoardElement[10][13];
        StartBoard startBoard = new StartBoard();
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

    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }

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

            }

        }

        for (BoardElement laser : lasers.values()) {

            String orientation = laser.getLaserOrientation();

            //laserAffected.add(createLaserAffectedHashMap(laser, orientation));

        }

    }

    public HashMap<Position, BoardElement> createLaserAffectedHashMap(BoardElement laser, String orientation) {

        HashMap<Position, BoardElement> returnValue = new HashMap<>();
        returnValue.put(laser.getXY(), laser);

        int x = laser.getXY().getX();
        int y = laser.getXY().getY();

        switch (orientation) {

            case "up":
                while (true) {

                    BoardElement nextBoardElementUp = gameBoard[y - 1][x];

                    if (nextBoardElementUp.isWall()) {

                        List<String> wallOrientations = Arrays.asList(nextBoardElementUp.getWallOrientations());

                        if (wallOrientations.contains("down")) {

                            break;

                        } else if (wallOrientations.contains("up")) {

                            returnValue.put(nextBoardElementUp.getXY(), nextBoardElementUp);
                            break;

                        } else {

                            returnValue.put(nextBoardElementUp.getXY(), nextBoardElementUp);
                            y = y - 1;

                        }

                    } else {

                        returnValue.put(nextBoardElementUp.getXY(), nextBoardElementUp);
                        y = y - 1;

                    }

                }
                break;

            case "down":
                while (true) {

                    BoardElement nextBoardElementDown = gameBoard[y + 1][x];

                    if (nextBoardElementDown.isWall()) {

                        List<String> wallOrientations = Arrays.asList(nextBoardElementDown.getWallOrientations());

                        if (wallOrientations.contains("up")) {

                            break;

                        } else if (wallOrientations.contains("down")) {

                            returnValue.put(nextBoardElementDown.getXY(), nextBoardElementDown);
                            break;

                        } else {

                            returnValue.put(nextBoardElementDown.getXY(), nextBoardElementDown);
                            y = y + 1;

                        }

                    } else {

                        returnValue.put(nextBoardElementDown.getXY(), nextBoardElementDown);
                        y = y + 1;

                    }

                }
                break;

            case "left":
                while (true) {

                    BoardElement nextBoardElementLeft = gameBoard[y][x - 1];

                    if (nextBoardElementLeft.isWall()) {

                        List<String> wallOrientations = Arrays.asList(nextBoardElementLeft.getWallOrientations());

                        if (wallOrientations.contains("right")) {

                            break;

                        } else if (wallOrientations.contains("left")) {

                            returnValue.put(nextBoardElementLeft.getXY(), nextBoardElementLeft);
                            break;

                        } else {

                            returnValue.put(nextBoardElementLeft.getXY(), nextBoardElementLeft);
                            x = x - 1;

                        }

                    } else {

                        returnValue.put(nextBoardElementLeft.getXY(), nextBoardElementLeft);
                        x = x - 1;

                    }

                }
                break;

            case "right":
                while (true) {

                    BoardElement nextBoardElementRight = gameBoard[y][x + 1];

                    if (nextBoardElementRight.isWall()) {

                        List<String> wallOrientations = Arrays.asList(nextBoardElementRight.getWallOrientations());

                        if (wallOrientations.contains("left")) {

                            break;

                        } else if (wallOrientations.contains("right")) {

                            returnValue.put(nextBoardElementRight.getXY(), nextBoardElementRight);
                            break;

                        } else {

                            returnValue.put(nextBoardElementRight.getXY(), nextBoardElementRight);
                            x = x + 1;

                        }

                    } else {

                        returnValue.put(nextBoardElementRight.getXY(), nextBoardElementRight);
                        x = x + 1;

                    }

                }
                break;

            default:
                return returnValue;

        }

        return returnValue;

    }

}
