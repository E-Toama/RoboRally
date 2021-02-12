package AI.logic.utilities;

import AI.logic.AIGameState;
import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.BeltFieldObject;
import game.gameboard.gameboardfieldobjects.RotatingBeltFieldObject;
import game.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class SimpleBeltHandlerV2 {

    private final AIGameState aiGameState;
    private final SimpleMoveHandler simpleMoveHandler;

    public SimpleBeltHandlerV2(AIGameState aiGameState) {

        this.aiGameState = aiGameState;
        this.simpleMoveHandler = new SimpleMoveHandler();

    }

    public void simulateGreenBelts() {

        Position currentPosition = aiGameState.getIntermediatePosition();
        BoardElement currentBoardElement = aiGameState.getIntermediateBoardElement();
        String movingDirection;
        String rotatingDirection;

        if (currentBoardElement.isGreenConveyorBelt()) {

            BeltFieldObject beltFieldObject = currentBoardElement.getConveyorBelt();

            movingDirection = beltFieldObject.getOrientation();

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = currentBoardElement.getRotatingConveyorBelt();

            movingDirection = rotatingBeltFieldObject.getOrientations()[1];

        }

        Position nextPosition = getNextPosition(currentPosition, movingDirection);
        BoardElement nextBoardElement = aiGameState.getGameBoard().getGameBoard()[nextPosition.getY()][nextPosition.getX()];

        if (nextBoardElement.isGreenConveyorBelt()) {

            aiGameState.setIntermediatePosition(nextBoardElement.getXY());
            aiGameState.setIntermediateBoardElement(nextBoardElement);

        } else if (nextBoardElement.isGreenRotatingConveyorBelt()) {

            aiGameState.setIntermediatePosition(nextBoardElement.getXY());
            aiGameState.setIntermediateBoardElement(nextBoardElement);

            RotatingBeltFieldObject rotatingBeltFieldObject = nextBoardElement.getRotatingConveyorBelt();

            rotatingDirection = getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]);

            switch (rotatingDirection) {

                case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

            }


        } else {

            simpleMoveHandler.move(aiGameState, movingDirection);

        }

    }

    public void simulateBlueBelts() {

        Position currentPosition = aiGameState.getIntermediatePosition();
        BoardElement currentBoardElement = aiGameState.getIntermediateBoardElement();
        String movingDirection;
        List<String> rotatingDirections = new ArrayList<>();

        if (currentBoardElement.isBlueConveyorBelt()) {

            BeltFieldObject beltFieldObject = currentBoardElement.getConveyorBelt();

            movingDirection = beltFieldObject.getOrientation();

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = currentBoardElement.getRotatingConveyorBelt();

            movingDirection = rotatingBeltFieldObject.getOrientations()[1];

        }

        Position nextPosition = getNextPosition(currentPosition, movingDirection);
        BoardElement nextBoardElement = aiGameState.getGameBoard().getGameBoard()[nextPosition.getY()][nextPosition.getX()];

        if (nextBoardElement.isBlueConveyorBelt()) {

            BeltFieldObject beltFieldObject = nextBoardElement.getConveyorBelt();

            movingDirection = beltFieldObject.getOrientation();

        } else if (nextBoardElement.isBlueRotatingConveyorBelt()) {

            RotatingBeltFieldObject rotatingBeltFieldObject = nextBoardElement.getRotatingConveyorBelt();

            String rotatingDirection = getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]);

            rotatingDirections.add(rotatingDirection);

            movingDirection = rotatingBeltFieldObject.getOrientations()[1];

        } else {

            simpleMoveHandler.move(aiGameState, movingDirection);
            return;

        }

        nextPosition = getNextPosition(currentPosition, movingDirection);
        nextBoardElement = aiGameState.getGameBoard().getGameBoard()[nextPosition.getY()][nextPosition.getX()];

        if (nextBoardElement.isBlueConveyorBelt()) {

            aiGameState.setIntermediatePosition(nextBoardElement.getXY());
            aiGameState.setIntermediateBoardElement(nextBoardElement);

            for (String rotations : rotatingDirections) {

                switch (rotations) {

                    case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                    case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

                }

            }

        } else if (nextBoardElement.isBlueRotatingConveyorBelt()) {

            aiGameState.setIntermediatePosition(nextBoardElement.getXY());
            aiGameState.setIntermediateBoardElement(nextBoardElement);

            RotatingBeltFieldObject rotatingBeltFieldObject = nextBoardElement.getRotatingConveyorBelt();

            String rotatingDirection = getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]);

            rotatingDirections.add(rotatingDirection);

            for (String rotations : rotatingDirections) {

                switch (rotations) {

                    case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                    case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

                }

            }

        } else {

            if (nextBoardElement.isPit()) {

                RebootHandler rebootHandler = new RebootHandler();

                rebootHandler.handleReboot(aiGameState);

            } else {

                aiGameState.setIntermediatePosition(nextBoardElement.getXY());
                aiGameState.setIntermediateBoardElement(nextBoardElement);

                for (String rotations : rotatingDirections) {

                    switch (rotations) {

                        case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                        case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

                    }

                }

            }

        }

    }

    private Position getNextPosition(Position currentPosition, String movingDirection) {

        return switch (movingDirection) {

            case "up" -> new Position(currentPosition.getX(), currentPosition.getY() - 1);
            case "left" -> new Position(currentPosition.getX() - 1, currentPosition.getY());
            case "down" -> new Position(currentPosition.getX(), currentPosition.getY() + 1);
            case "right" -> new Position(currentPosition.getX() + 1, currentPosition.getY());
            default -> null;

        };

    }

    private String getRotatingDirection(String oldOrientation, String newOrientation) {

        String returnValue = "";

        switch (oldOrientation) {
            case "up" -> {
                switch (newOrientation) {
                    case "right" -> returnValue = "clockwise";
                    case "left" -> returnValue = "counterClockwise";
                }
            }
            case "right" -> {
                switch (newOrientation) {
                    case "up" -> returnValue = "counterClockwise";
                    case "down" -> returnValue = "clockwise";
                }
            }
            case "down" -> {
                switch (newOrientation) {
                    case "right" -> returnValue = "counterClockwise";
                    case "left" -> returnValue = "clockwise";
                }
            }
            case "left" -> {
                switch (newOrientation) {
                    case "up" -> returnValue = "clockwise";
                    case "down" -> returnValue = "counterClockwise";
                }
            }
        }

        return returnValue;

    }

}
