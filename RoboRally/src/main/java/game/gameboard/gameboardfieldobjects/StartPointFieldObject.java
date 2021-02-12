package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the Starting point board element.
 * 
 */
public class StartPointFieldObject extends GameBoardFieldObject {

    /**
     * Constructor for the name initialization.
     */
    public StartPointFieldObject() {
        super("StartPoint");
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
