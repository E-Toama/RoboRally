package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the different types of belts board elements with its speed and orientation.
 * 
 * @author 
 */
public class BeltFieldObject extends GameBoardFieldObject {

    private final String orientation;
    private final int speed;

    /**
     * Constructor for the initialization of the belt speed and orientation.
     * 
     * @param orientation
     *          the direction that the belt is facing
     * @param speed
     *          the number of fields that the robot will be pushed
     */
    public BeltFieldObject(String orientation, int speed) {
        super("Belt");
        this.orientation = orientation;
        this.speed = speed;
    }

    /**
     * This method returns the orientation of the belt.
     * 
     * @return the direction that the belt is facing
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * This method returns the speed of the belt, which represents the number of fields the robot will be pushed once landed on it.
     * 
     * @return the speed of the belt 
     */
    public int getSpeed() {
        return speed;
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
