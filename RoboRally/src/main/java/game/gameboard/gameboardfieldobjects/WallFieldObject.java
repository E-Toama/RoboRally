package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the wall board element.
 * 
 */
public class WallFieldObject extends GameBoardFieldObject {

    private final String[] orientations;

    /**
     * Constructor for initializing the name and the orientation of the wall.
     * 
     * @param orientations 
     *          the directions in which the wall is facing
     */
    public WallFieldObject(String[] orientations) {
        super("Wall");
        this.orientations = orientations;
    }

    /**
     * This method returns the directions in which the wall is facing.
     * 
     * @return the orientation of the wall
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
