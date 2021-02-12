package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the laser board element and its effect on the robots once activated.
 * 
 */
public class LaserFieldObject extends GameBoardFieldObject {

    private final String orientation;
    private final int count;

    /**
     * Constructor for initialization of the direction and the number of lasers.
     * 
     * @param orientation
     *          the direction that the laser is shooting towards
     * @param count
     *          the number of laser shooting
     */
    public LaserFieldObject(String orientation, int count) {
        super("Laser");
        this.orientation = orientation;
        this.count = count;
    }

    /**
     * This method returns the orientation of the laser.
     * 
     * @return the direction in which the laser is shooting
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * This method returns the number of lasers that are shooting from the same laser field.
     * 
     * @return the number of lasers shooting 
     */
    public int getCount() {
        return count;
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
