package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class LaserFieldObject extends GameBoardFieldObject {

    private final String orientation;
    private final int count;

    public LaserFieldObject(String orientation, int count) {
        super("Laser");
        this.orientation = orientation;
        this.count = count;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}
