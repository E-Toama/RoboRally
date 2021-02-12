package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the Reboot token board element.
 * 
 */
public class RestartPointFieldObject extends GameBoardFieldObject {

    private String orientation;

    /**
     * Constructor for initializing the name and orientation of the reboot field.
     * 
     * @param orientation
     *          the direction in which the robot should be facing
     */
    public RestartPointFieldObject(String orientation) {
        super("RestartPoint");
        this.orientation = orientation;
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

    /**
     * This method returns the direction in which the robot will be facing if rebooted.
     * 
     * @return the orientation of the reboot field
     */
    public String getOrientation() {
        return orientation;
    }
}
