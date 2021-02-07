package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;

public class MoveTwoS extends MoveS {

    public MoveTwoS() {

        this.name = "MoveTwo";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        moveForward(aiGameState);

        if (!aiGameState.getWasRebooted()) {

            moveForward(aiGameState);

        }

    }

}
