package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;

public class LeftTurnS extends CardS {

    public LeftTurnS() {

        this.name = "LeftTurn";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        aiGameState.turningCounterClockwiseIntermediate();

    }

}
