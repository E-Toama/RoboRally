package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;

public class MoveOneS extends MoveS {

    public MoveOneS() {

        this.name = "MoveOne";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        moveForward(aiGameState);

    }
}
