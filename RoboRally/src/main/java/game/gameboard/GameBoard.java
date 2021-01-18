package game.gameboard;

import game.gameboard.boardelements.BoardElement;

/**
 * This class holds the actual GameBoard. It has two constructors:
 * 1. For creating the GameBoard on the server side by providing the name of the course
 * 2. For creating the GameBoard on the client side by providing the map from the protocol-message
 */
public class GameBoard {

    private BoardElement[][] gameBoard;

    /**
     * Constructor for creating a course by name
     * @param course Course-name as string
     */
    public GameBoard(String course) {
        switch (course) {
            case "DizzyHighway":
                gameBoard = createDizzyHighway();
                break;
            default:
                gameBoard = null;
        }
    }

    /**
     * Second Constructor for recreating a GameBoard from JSON
     * WARNING: Hard-coded size of GameBoard-array (10 * 13)!!!
     * (because it is impossible to guess the board-format from a simple list)
     * @param map Array of MapElements from JSON-Message
     */
    public GameBoard(MapElement[] map) {
        gameBoard = new BoardElement[10][13];
        int position = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                gameBoard[i][j] = new BoardElement(map[position].getField());
                position++;
            }
        }
    }

    public BoardElement[][] getGameBoard() {
        return gameBoard;
    }

    public MapElement[] createMapForJSONMessage() {
        System.out.println(gameBoard.length);
        System.out.println(gameBoard[0].length);
        int length = gameBoard.length * gameBoard[0].length;
        MapElement[] map = new MapElement[length];
        int position = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[0].length; j++) {
                map[position] = gameBoard[i][j].createMapElement(position+1);
                position++;
            }
        }
        return map;
    }

    private BoardElement[][] createDizzyHighway() {
        BoardElement[][] dizzyHighway = new BoardElement[10][13];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (j < 3) {
                    dizzyHighway[i][j] = BoardParts.StartBoard[i][j];
                } else {
                    dizzyHighway[i][j] = BoardParts._5B[i][j-3];
                }
            }
        }
        return dizzyHighway;
    }


}
