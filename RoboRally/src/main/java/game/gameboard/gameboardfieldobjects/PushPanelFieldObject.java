package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;

/**
 * This class represents the push panels board element and its effect once stood in its way.
 * 
 * @author 
 */
public class PushPanelFieldObject extends GameBoardFieldObject{

    private final String orientation;
    private final int[] registers;

    /**
     * Constructor for initializing the direction in which the push panels push and the registers 
     * that makes the push panels activate.
     *  
     * @param orientation 
     *          the direction of the push panels
     * @param registers
     *          the registers in which the push panels work.
     */
    public PushPanelFieldObject(String orientation, int[] registers) {
        super("PushPanel");
        this.orientation = orientation;
        this.registers = registers;
    }

    /**
     * The method returns the direction of the push panel.
     * 
     * @return the orientation of the push panel.
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * The method returns the registers to which the push panels work.
     * @return list of registers
     */
    public int[] getRegisters() {
        return registers;
    }

    /**
     * This method handles the activation of the push panels fields, once stepped 
     * one and the registers fit then they activate and push the robot away.
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

        MoveHandler moveHandler = new MoveHandler();

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();
        Position newPosition = moveHandler.getTargetPosition(position, orientation);

        moveHandler.move(game, gameState, playerID, position, newPosition, orientation, false, true);

    }
}
