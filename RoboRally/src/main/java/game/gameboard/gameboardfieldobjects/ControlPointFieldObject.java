package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.messages.CheckpointReached;
import utilities.messages.GameWon;

public class ControlPointFieldObject extends GameBoardFieldObject {

    private final int count;

    public ControlPointFieldObject(int count) {
        super("ControlPoint");
        this.count = count;
    }

    public int getCount() {
        return count;
    }

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
