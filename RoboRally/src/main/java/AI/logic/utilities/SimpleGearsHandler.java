package AI.logic.utilities;

import AI.logic.AIGameState;
import game.gameboard.gameboardfieldobjects.GearFieldObject;

/**
 * Simulate gears.
 */
public class SimpleGearsHandler {

    private final AIGameState aiGameState;

    public SimpleGearsHandler(AIGameState aiGameState) {

        this.aiGameState = aiGameState;

    }

    public void simulateGears() {

        GearFieldObject gearFieldObject = aiGameState.getIntermediateBoardElement().getGear();

        switch (gearFieldObject.getOrientation()) {

            case "right" -> aiGameState.turningClockwiseIntermediate();
            case "left" -> aiGameState.turningCounterClockwiseIntermediate();

        }

    }

}
