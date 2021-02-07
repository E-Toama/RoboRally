package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;

public class MoveThreeS extends MoveS {

    public MoveThreeS() {

        this.name = "MoveThree";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        moveForward(aiGameState);

        if (!aiGameState.getWasRebooted()) {

            moveForward(aiGameState);

        }

        if (!aiGameState.getWasRebooted()) {

            moveForward(aiGameState);

        }

    }

}
