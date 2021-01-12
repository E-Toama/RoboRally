package game.gameboard;

import game.gameboard.boardelements.BoardElement;

public class GameBoard {

    private BoardElement[][] gameBoard;

    public GameBoard(String course) {
        switch (course) {
            case "DizzyHighway":
                gameBoard = createDizzyHighway();
                break;
            default:
                gameBoard = null;
        }
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
