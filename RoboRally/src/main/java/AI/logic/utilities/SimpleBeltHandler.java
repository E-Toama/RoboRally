package AI.logic.utilities;

import AI.logic.AIGameState;
import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.BeltFieldObject;
import game.gameboard.gameboardfieldobjects.RotatingBeltFieldObject;
import game.utilities.Position;

import java.util.ArrayList;
import java.util.List;

public class SimpleBeltHandler {

    private final AIGameState aiGameState;
    SimpleMoveHandler simpleMoveHandler = new SimpleMoveHandler();

    public SimpleBeltHandler(AIGameState aiGameState) {

        this.aiGameState = aiGameState;

    }

    public void simulateGreenConveyorBelts() {

        Position targetPosition;
        BoardElement targetBoardElement;
        String movingOrientation;
        String rotatingOrientation = "";

        if (aiGameState.getIntermediateBoardElement().isGreenConveyorBelt()) {

            BeltFieldObject beltFieldObject = aiGameState.getIntermediateBoardElement().getConveyorBelt();

            targetPosition = getTargetPosition(aiGameState.getIntermediatePosition(), beltFieldObject.getOrientation());
            targetBoardElement = getNextBoardElement(aiGameState, aiGameState.getIntermediatePosition(), beltFieldObject.getOrientation());

            movingOrientation = beltFieldObject.getOrientation();

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = aiGameState.getIntermediateBoardElement().getRotatingConveyorBelt();

            targetPosition = getTargetPosition(aiGameState.getIntermediatePosition(), rotatingBeltFieldObject.getOrientations()[1]);
            targetBoardElement = getNextBoardElement(aiGameState, aiGameState.getIntermediatePosition(), rotatingBeltFieldObject.getOrientations()[1]);

            rotatingOrientation = getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]);
            movingOrientation = rotatingBeltFieldObject.getOrientations()[1];

        }

        if (targetBoardElement.isGreenConveyorBelt()) {

            aiGameState.setIntermediatePosition(targetPosition);
            aiGameState.setIntermediateBoardElement(targetBoardElement);


        } else if (targetBoardElement.isGreenRotatingConveyorBelt()) {

            aiGameState.setIntermediatePosition(targetPosition);
            aiGameState.setIntermediateBoardElement(targetBoardElement);

            switch (rotatingOrientation) {

                case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

            }


        } else {

            simpleMoveHandler.move(aiGameState, movingOrientation);

        }

    }

    public void simulateBlueConveyorBelts() {

        Position targetPosition;
        BoardElement targetBoardElement;
        String movingOrientation;
        List<String> rotatingOrientations = new ArrayList<>();

        if (aiGameState.getIntermediateBoardElement().isBlueConveyorBelt()) {

            BeltFieldObject beltFieldObject = aiGameState.getIntermediateBoardElement().getConveyorBelt();

            targetPosition = getTargetPosition(aiGameState.getIntermediatePosition(), beltFieldObject.getOrientation());
            targetBoardElement = getNextBoardElement(aiGameState, aiGameState.getIntermediatePosition(), beltFieldObject.getOrientation());

            movingOrientation = beltFieldObject.getOrientation();

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = aiGameState.getIntermediateBoardElement().getRotatingConveyorBelt();

            targetPosition = getTargetPosition(aiGameState.getIntermediatePosition(), rotatingBeltFieldObject.getOrientations()[1]);
            targetBoardElement = getNextBoardElement(aiGameState, aiGameState.getIntermediatePosition(), rotatingBeltFieldObject.getOrientations()[1]);

            movingOrientation = rotatingBeltFieldObject.getOrientations()[1];

        }

        if (targetBoardElement.isBlueConveyorBelt()) {

            targetPosition = getTargetPosition(targetPosition, movingOrientation);
            targetBoardElement = getNextBoardElement(aiGameState, targetPosition, movingOrientation);

        } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

            RotatingBeltFieldObject rotatingBeltFieldObject = targetBoardElement.getRotatingConveyorBelt();
            rotatingOrientations.add(getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]));

            targetPosition = getTargetPosition(targetPosition, movingOrientation);
            targetBoardElement = getNextBoardElement(aiGameState, targetPosition, movingOrientation);

        } else {

            simpleMoveHandler.move(aiGameState, movingOrientation);

        }

        if (targetBoardElement.isBlueConveyorBelt()) {

            aiGameState.setIntermediatePosition(targetPosition);
            aiGameState.setIntermediateBoardElement(targetBoardElement);

            for (String rotations : rotatingOrientations) {

                switch (rotations) {

                    case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                    case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

                }

            }

        } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

            RotatingBeltFieldObject rotatingBeltFieldObject = targetBoardElement.getRotatingConveyorBelt();
            rotatingOrientations.add(getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]));

            aiGameState.setIntermediatePosition(targetPosition);
            aiGameState.setIntermediateBoardElement(targetBoardElement);

            for (String rotations : rotatingOrientations) {

                switch (rotations) {

                    case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                    case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

                }

            }

        } else {

            if (targetBoardElement.isPit()) {

                RebootHandler rebootHandler = new RebootHandler();

                rebootHandler.handleReboot(aiGameState);

            } else {

                aiGameState.setIntermediatePosition(targetPosition);
                aiGameState.setIntermediateBoardElement(targetBoardElement);

                for (String rotations : rotatingOrientations) {

                    switch (rotations) {

                        case "clockwise" -> aiGameState.turningClockwiseIntermediate();
                        case "counterClockwise" -> aiGameState.turningCounterClockwiseIntermediate();

                    }

                }

            }

        }

    }

    private Position getTargetPosition(Position position, String movingDirection) {

        return switch (movingDirection) {
            case "up" -> new Position(position.getX(), position.getY() - 1);
            case "left" -> new Position(position.getX() - 1, position.getY());
            case "down" -> new Position(position.getX(), position.getY() + 1);
            case "right" -> new Position(position.getX() + 1, position.getY());
            default -> null;
        };

    }

    private BoardElement getNextBoardElement(AIGameState aiGameState, Position position, String orientation) {

        return switch (orientation) {

            case "up" -> aiGameState.getGameBoard().getGameBoard()[position.getY() - 1][position.getX()];
            case "left" -> aiGameState.getGameBoard().getGameBoard()[position.getY()][position.getX() - 1];
            case "down" -> aiGameState.getGameBoard().getGameBoard()[position.getY() + 1][position.getX()];
            case "right" -> aiGameState.getGameBoard().getGameBoard()[position.getY()][position.getX() + 1];
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
