package game.gameboard;

import game.Robots.Twonky;
import game.gameboard.boards.DizzyHighway;
import game.gameboard.boards.ExtraCrispy;
import game.gameboard.boards.StartBoard;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import game.gameboard.gameboardfieldobjects.RestartPointFieldObject;

import javax.print.attribute.standard.PresentationDirection;

public class GameBoard {

    private BoardElement[][] gameBoard;

    public GameBoard(String board) {

        switch (board) {
            case "DizzyHighway":
                this.gameBoard = createDizzyHighway();
                break;
            case "ExtraCrispy":
                this.gameBoard = createExtraCrispy();
                break;
        }

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
     * WARNING: Hard-coded size of GameBoard-array (10 * 13)!!!
     * (because it is impossible to guess the board-format from a one-dimensional list)
     * @param map Array of GameBoardMapObjects from JSON-Message
     */
    public GameBoard(GameBoardMapObject[] map) {
        gameBoard = new BoardElement[10][13];
        int position = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                gameBoard[i][j] = new BoardElement(position, map[position].getField());
                position++;
            }
        }
    }

    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }

    private BoardElement[][] createDizzyHighway() {
        BoardElement[][] dizzyHighway = new BoardElement[10][13];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (j < 3) {
                    dizzyHighway[i][j] = StartBoard.startBoard[i][j];
                } else {
                    dizzyHighway[i][j] = DizzyHighway._5B[i][j-3];
                }
            }
        }
        return dizzyHighway;
    }

    private BoardElement[][] createExtraCrispy() {
        BoardElement[][] extraCrispy = new BoardElement[10][13];
        BoardElement restartTile = new BoardElement(0, new GameBoardFieldObject[]{new RestartPointFieldObject("right")});
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (j < 3) {
                    extraCrispy[i][j] = (i == 0 && j == 0) ? restartTile : StartBoard.startBoard[i][j];
                } else {
                    extraCrispy[i][j] = ExtraCrispy._5B[i][j-3];
                }
            }
        }
        return extraCrispy;
    }
}
