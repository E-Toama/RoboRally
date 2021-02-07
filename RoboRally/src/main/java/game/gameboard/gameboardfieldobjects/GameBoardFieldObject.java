package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.MessageHandler;

/**
 * The abstract class GameBoardFieldObject is a parent class for the classes that represents the different 
 * types of board elements.
 * 
 * @author 
 */
public abstract class GameBoardFieldObject {

    private final String type;
    protected final MessageHandler messageHandler = new MessageHandler();

    /**
     * Constructor for the board element initialization.
     * 
     * @param type 
     *          the name of the board element
     */
    protected GameBoardFieldObject(String type) {
        this.type = type;
    }

    /**
     * This method returns the type of the board element.
     * 
     * @return the name of the board element
     */
    public String getType() {
        return type;
    }

    /**
     * The abstract method activate is for the activation of the different types of board elements.
     * 
     * @param game
     *          an object of the Game class
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
    public abstract void activate(Game game, GameState gameState, int playerID);

}
