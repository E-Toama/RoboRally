package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class StartPointFieldObject extends GameBoardFieldObject {

    public StartPointFieldObject() {
        super("StartPoint");
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}
