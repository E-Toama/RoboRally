package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.messages.CheckpointReached;
import utilities.messages.GameWon;

/**
 * This class represents the Checkpoint board element with its effect once stepped on.
 * 
 * @author 
 */
public class ControlPointFieldObject extends GameBoardFieldObject {

    private final int count;

    /**
     * Constructor for the initialization of the name with how many Checkpoints are there on the board.
     * 
     * @param count 
     *          number of checkpoints on the board
     */
    public ControlPointFieldObject(int count) {
        super("ControlPoint");
        this.count = count;
    }

    /**
     * This method returns the number of checkpoints on the board.
     * 
     * @return the number of checkpoints
     */
    public int getCount() {
        return count;
    }

    /**
     * This method handles the number of checkpoints the player has once stepped on one and then checks 
     * if the game is over and ends it.
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

        int checkpointsReached = gameState.playerMatHashMap.get(playerID).getCheckpointsReached();

        if (checkpointsReached == count - 1) {

            gameState.playerMatHashMap.get(playerID).setCheckpointsReached();

            String checkpointReached = messageHandler.buildMessage("CheckPointReached", new CheckpointReached(playerID, count));
            gameState.server.sendMessageToAllUsers(checkpointReached);

            if (count == gameState.checkPointsNeededToWin) {

                String gameWon = messageHandler.buildMessage("GameWon", new GameWon(playerID));
                gameState.server.sendMessageToAllUsers(gameWon);
                gameState.server.endGame();

            }

        }

    }
}
