package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;

public class UTurnS extends CardS {

    public UTurnS() {

        this.name = "U-Turn";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        aiGameState.turningClockwiseIntermediate();
        aiGameState.turningClockwiseIntermediate();

    }

}
