package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
/**
 * This class represents the empty board element.
 *  
 */
public class EmptyFieldObject extends GameBoardFieldObject {

  /**
   * Constructor for board element name initialization.
   */
    public EmptyFieldObject() {
        super("Empty");
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

