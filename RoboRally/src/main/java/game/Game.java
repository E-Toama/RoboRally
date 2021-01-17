package game;

import game.gameboardV2.GameBoardV2;
import game.gameboardV2.boards.StartBoard;

public class Game {

    private GameBoardV2 gameBoard = new GameBoardV2(StartBoard.startBoard);

    public Game() {


    }

    public GameBoardV2 getGameBoard() {
        return gameBoard;
    }
}
