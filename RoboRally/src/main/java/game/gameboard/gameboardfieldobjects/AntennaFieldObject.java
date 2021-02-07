package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

/**
 * This class represents the Antenna board element in the game.
 * 
 * @author 
 */
public class AntennaFieldObject extends GameBoardFieldObject {

  /**
   * Constructor for the board element name initialization.
   */
    public AntennaFieldObject() {
        super("Antenna");
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
