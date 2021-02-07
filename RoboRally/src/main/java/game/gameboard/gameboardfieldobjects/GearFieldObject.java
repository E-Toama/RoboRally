package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.messages.PlayerTurning;

/**
 * This class represents the Gear board element and its effect once stepped on.
 * 
 * @author 
 */
public class GearFieldObject extends GameBoardFieldObject {

    private final String orientation;

    /**
     * Constructor for initialization of the name and the orientation of the gear board element.
     * 
     * @param orientation 
     *              the direction the gear board element rotates
     */
    public GearFieldObject(String orientation) {
        super("Gear");
        this.orientation = orientation;
    }

    /**
     * This method handles the effect of stepping on a gear field.
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

        if (orientation.equals("right")) {

            turnClockwise(gameState, playerID);

        } else {

            turnCounterClockwise(gameState, playerID);

        }

    }

    /**
     * This method turns the robot clockwise.
     * 
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
    private void turnClockwise(GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "clockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

    }

    /**
     * This method turns the robot counterclockwise.
     * 
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
    private void turnCounterClockwise(GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).getRobot().turnLeft();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "counterClockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

    }

    /**
     * This method returns the rotating direction of the gear board element.
     * 
     * @return the rotating direction of the gear field
     */
    public String getOrientation() {
        return orientation;
    }

}
