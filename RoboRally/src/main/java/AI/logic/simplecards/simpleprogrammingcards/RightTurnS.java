package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;

public class RightTurnS extends CardS {

    public RightTurnS() {

        this.name = "RightTurn";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        aiGameState.turningClockwiseIntermediate();

    }

}
