package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class WallFieldObject extends GameBoardFieldObject {

    private final String[] orientations;

    public WallFieldObject(String[] orientations) {
        super("Wall");
        this.orientations = orientations;
    }

    public String[] getOrientations() {
        return orientations;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}
