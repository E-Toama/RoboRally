package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the pit board element.
 * 
 * @author 
 */
public class PitFieldObject extends GameBoardFieldObject {
    
    /**
     * Constructor for initializing the name of the pit.
     */
    public PitFieldObject() {
        super("Pit");
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
