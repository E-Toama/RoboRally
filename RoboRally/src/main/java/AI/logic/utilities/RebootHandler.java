package AI.logic.utilities;

import AI.logic.AIGameState;
import game.gameboard.BoardElement;
import game.utilities.Position;

/**
 * Doc: Yashar
 */
public class RebootHandler {

    public void handleReboot(AIGameState aiGameState) {

        aiGameState.setWasRebooted(true);

        if (aiGameState.getIntermediatePosition().getX() < 3) {

            aiGameState.setIntermediatePosition(aiGameState.getStartPosition());
            aiGameState.setIntermediateBoardElement(aiGameState.getStartBoardElement());

        } else {

            Position restartPosition = aiGameState.getGameBoard().getRestartPoint().getXY();
            BoardElement restartBoardElement = aiGameState.getGameBoard().getRestartPoint();

            aiGameState.setIntermediatePosition(restartPosition);
            aiGameState.setIntermediateBoardElement(restartBoardElement);

        }

        aiGameState.setIntermediateOrientation("up");

    }

}
