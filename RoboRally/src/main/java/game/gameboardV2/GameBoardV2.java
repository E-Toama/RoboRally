package game.gameboardV2;

public class GameBoardV2 {

    private BoardElement[][] gameBoard;

    public GameBoardV2(BoardElement[][] gameBoard) {

        this.gameBoard = gameBoard;

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

}
