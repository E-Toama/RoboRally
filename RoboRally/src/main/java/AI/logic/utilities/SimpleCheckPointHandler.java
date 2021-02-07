package AI.logic.utilities;

import AI.logic.AIGameState;

public class SimpleCheckPointHandler {

    private AIGameState aiGameState;

    public SimpleCheckPointHandler(AIGameState aiGameState) {

        this.aiGameState = aiGameState;

    }

    public void simulateCheckPointReached() {

        if (aiGameState.getGameBoard().equals("ExtraCrispy")) {

            int targetCheckpoint = aiGameState.getTargetCheckpoint() + 1;
            aiGameState.setIntermediateRatingMaps(aiGameState.getGameBoardName(), targetCheckpoint);

        }

    }

}
