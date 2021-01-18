package game.gameboardV2;

import game.gameboardV2.boards.DizzyHighway;
import game.gameboardV2.boards.StartBoard;

public class GameBoardV2 {

    private BoardElement[][] gameBoard;

    public GameBoardV2() {

        this.gameBoard = createDizzyHighway();

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
    public GameBoardV2(GameBoardMapObject[] map) {
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
}
