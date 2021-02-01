package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class EmptyFieldObject extends GameBoardFieldObject {

    public EmptyFieldObject() {
        super("Empty");
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}

