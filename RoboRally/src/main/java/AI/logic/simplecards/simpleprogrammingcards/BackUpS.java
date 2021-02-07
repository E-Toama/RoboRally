package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;
import AI.logic.utilities.SimpleMoveHandler;

public class BackUpS extends CardS {

    public BackUpS() {

        this.name = "BackUp";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        SimpleMoveHandler simpleMoveHandler = new SimpleMoveHandler();

        String oppositeDirection = "";

        switch (aiGameState.getIntermediateOrientation()) {

            case "up" -> oppositeDirection = "down";
            case "left" -> oppositeDirection = "right";
            case "down" -> oppositeDirection = "up";
            case "right" -> oppositeDirection = "left";

        }

        simpleMoveHandler.move(aiGameState, oppositeDirection);

    }

}
