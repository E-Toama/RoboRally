package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class BeltFieldObject extends GameBoardFieldObject {

    private final String orientation;
    private final int speed;

    public BeltFieldObject(String orientation, int speed) {
        super("Belt");
        this.orientation = orientation;
        this.speed = speed;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}
