package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the rotating belt board element.
 * 
 */
public class RotatingBeltFieldObject extends GameBoardFieldObject {

    private final int speed;
    private final boolean isCrossing;
    private final String[] orientations;

    /**
     * Constructor for initializing the speed, if its a crossing and the direction 
     * in which the curve of the belt is going.
     * 
     * @param speed
     *          the number of fields the robot must move once landed on a belt
     * @param isCrossing
     *          true if its a crossing between more than one belt
     * @param orientations
     *          the direction of the curve
     */
    public RotatingBeltFieldObject(int speed, boolean isCrossing, String[] orientations) {
        super("RotatingBelt");
        this.speed = speed;
        this.isCrossing = isCrossing;
        this.orientations = orientations;
    }

    /**
     * The method returns the number of fields the robot should be pushed.
     * @return the speed of the belt
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * The method returns true if its a crossing between more than one belt.
     * @return true if its a crossing
     */
    public boolean isCrossing() {
        return isCrossing;
    }

    /**
     * The method returns the direction in which the curve is going.
     * @return direction of the curve
     */
    public String[] getOrientations() {
        return orientations;
    }

    /**
     * 
     * @param game
     *          an object of the Game class
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }
}
