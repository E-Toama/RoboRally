package game;

import game.gameboard.GameBoard;

public class Game {

    private GameBoard gameBoard = new GameBoard("ExtraCrispy");

    public Game() {


    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
