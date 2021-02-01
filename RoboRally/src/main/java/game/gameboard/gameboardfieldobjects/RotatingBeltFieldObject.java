package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class RotatingBeltFieldObject extends GameBoardFieldObject {

    private final int speed;
    private final boolean isCrossing;
    private final String[] orientations;

    public RotatingBeltFieldObject(int speed, boolean isCrossing, String[] orientations) {
        super("RotatingBelt");
        this.speed = speed;
        this.isCrossing = isCrossing;
        this.orientations = orientations;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isCrossing() {
        return isCrossing;
    }

    public String[] getOrientations() {
        return orientations;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}
