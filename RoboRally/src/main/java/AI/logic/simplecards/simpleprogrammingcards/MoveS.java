package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;
import AI.logic.utilities.SimpleMoveHandler;

public abstract class MoveS extends CardS {

    private SimpleMoveHandler simpleMoveHandler = new SimpleMoveHandler();

    public void moveForward(AIGameState aiGameState) {

        simpleMoveHandler.move(aiGameState, aiGameState.getIntermediateOrientation());

    }

}
