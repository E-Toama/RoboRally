package game.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.BeltFieldObject;
import game.gameboard.gameboardfieldobjects.RotatingBeltFieldObject;
import game.player.PlayerMat;

import java.util.ArrayList;
import java.util.List;

public class ConveyorBeltsHelperV2 {

    public ConveyorBeltTarget calculateGreenConveyorBeltTarget(GameState gameState, PlayerMat playerMat) {

        int playerID = playerMat.getPlayer().getPlayerID();
        BoardElement targetBoardElement;
        List<String> rotatingDirections = new ArrayList<>();
        boolean targetIsBelt = false;
        String movingDirection;

        Position currentPosition = playerMat.getRobot().getRobotXY();
        BoardElement currentBoardElement = gameState.gameBoard.getGameBoard()[currentPosition.getY()][currentPosition.getX()];

        if (currentBoardElement.isGreenConveyorBelt()) {

            BeltFieldObject beltFieldObject = currentBoardElement.getConveyorBelt();
            targetBoardElement = getNextBoardElementByMovingDirection(gameState, currentPosition, beltFieldObject.getOrientation());
            movingDirection = beltFieldObject.getOrientation();

            if (targetBoardElement.isGreenConveyorBelt()) {

                targetIsBelt = true;

            } else if (targetBoardElement.isGreenRotatingConveyorBelt()) {

                targetIsBelt = true;

                RotatingBeltFieldObject rotatingBeltFieldObject = targetBoardElement.getRotatingConveyorBelt();

                if (!beltFieldObject.getOrientation().equals(rotatingBeltFieldObject.getOrientations()[1])) {

                    rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]));
                    movingDirection = rotatingBeltFieldObject.getOrientations()[1];

                }

            }

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = currentBoardElement.getRotatingConveyorBelt();
            targetBoardElement = getNextBoardElementByMovingDirection(gameState, currentPosition, rotatingBeltFieldObject.getOrientations()[1]);
            movingDirection = rotatingBeltFieldObject.getOrientations()[1];

            if (targetBoardElement.isGreenConveyorBelt()) {

                targetIsBelt = true;

            } else if (targetBoardElement.isGreenRotatingConveyorBelt()) {

                targetIsBelt = true;

                RotatingBeltFieldObject rotatingBeltFieldObject2 = targetBoardElement.getRotatingConveyorBelt();

                if (!rotatingBeltFieldObject.getOrientations()[1].equals(rotatingBeltFieldObject2.getOrientations()[1])) {

                    rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObject2.getOrientations()[0], rotatingBeltFieldObject2.getOrientations()[1]));
                    movingDirection = rotatingBeltFieldObject2.getOrientations()[1];

                }

            }

        }

        return new ConveyorBeltTarget(playerID, targetBoardElement, rotatingDirections.toArray(new String[0]), targetIsBelt, movingDirection);

    }

    public ConveyorBeltTarget calculateBlueConveyorBeltTarget(GameState gameState, PlayerMat playerMat) {

        int playerID = playerMat.getPlayer().getPlayerID();
        BoardElement targetBoardElement;
        List<String> rotatingDirections = new ArrayList<>();
        boolean targetIsBelt = false;
        String movingDirection;

        Position currentPosition = playerMat.getRobot().getRobotXY();
        BoardElement currentBoardElement = gameState.gameBoard.getGameBoard()[currentPosition.getY()][currentPosition.getX()];

        if (currentBoardElement.isBlueConveyorBelt()) {

            BeltFieldObject beltFieldObject = currentBoardElement.getConveyorBelt();
            targetBoardElement = getNextBoardElementByMovingDirection(gameState, currentBoardElement.getXY(), beltFieldObject.getOrientation());
            movingDirection = beltFieldObject.getOrientation();

            if (targetBoardElement.isBlueConveyorBelt()) {

                BeltFieldObject beltFieldObjectTwo = targetBoardElement.getConveyorBelt();
                targetBoardElement = getNextBoardElementByMovingDirection(gameState, targetBoardElement.getXY(), beltFieldObject.getOrientation());
                movingDirection = beltFieldObjectTwo.getOrientation();

                if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                    targetIsBelt = true;

                } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                    targetIsBelt = true;

                    RotatingBeltFieldObject rotatingBeltFieldObject = targetBoardElement.getRotatingConveyorBelt();
                    movingDirection = rotatingBeltFieldObject.getOrientations()[1];

                    if (!beltFieldObjectTwo.getOrientation().equals(rotatingBeltFieldObject.getOrientations()[1])) {

                        rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]));

                    }

                }

            } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                RotatingBeltFieldObject rotatingBeltFieldObject = targetBoardElement.getRotatingConveyorBelt();
                movingDirection = rotatingBeltFieldObject.getOrientations()[1];

                if (!beltFieldObject.getOrientation().equals(rotatingBeltFieldObject.getOrientations()[1])) {

                    rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObject.getOrientations()[0], rotatingBeltFieldObject.getOrientations()[1]));

                }

                targetBoardElement = getNextBoardElementByMovingDirection(gameState, targetBoardElement.getXY(), rotatingBeltFieldObject.getOrientations()[1]);

                if (targetBoardElement.isBlueConveyorBelt()) {

                    targetIsBelt = true;

                } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                    targetIsBelt = true;

                    RotatingBeltFieldObject rotatingBeltFieldObjectTwo = targetBoardElement.getRotatingConveyorBelt();
                    movingDirection = rotatingBeltFieldObjectTwo.getOrientations()[1];

                    if (!rotatingBeltFieldObject.getOrientations()[1].equals(rotatingBeltFieldObjectTwo.getOrientations()[1])) {

                        rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObjectTwo.getOrientations()[0], rotatingBeltFieldObjectTwo.getOrientations()[1]));

                    }

                }

            }

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = currentBoardElement.getRotatingConveyorBelt();
            targetBoardElement = getNextBoardElementByMovingDirection(gameState, currentPosition, rotatingBeltFieldObject.getOrientations()[1]);
            movingDirection = rotatingBeltFieldObject.getOrientations()[1];

            if (targetBoardElement.isBlueConveyorBelt()) {

                BeltFieldObject beltFieldObject = targetBoardElement.getConveyorBelt();
                targetBoardElement = getNextBoardElementByMovingDirection(gameState, targetBoardElement.getXY(), beltFieldObject.getOrientation());

                if (targetBoardElement.isBlueConveyorBelt()) {

                    targetIsBelt = true;

                } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                    targetIsBelt = true;

                    RotatingBeltFieldObject rotatingBeltFieldObjectTwo = targetBoardElement.getRotatingConveyorBelt();
                    movingDirection = rotatingBeltFieldObjectTwo.getOrientations()[1];

                    if (!beltFieldObject.getOrientation().equals(rotatingBeltFieldObjectTwo.getOrientations()[1])) {

                        rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObjectTwo.getOrientations()[0], rotatingBeltFieldObjectTwo.getOrientations()[1]));

                    }

                }

            } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                RotatingBeltFieldObject rotatingBeltFieldObjectTwo = targetBoardElement.getRotatingConveyorBelt();
                movingDirection = rotatingBeltFieldObjectTwo.getOrientations()[1];

                if (!rotatingBeltFieldObject.getOrientations()[1].equals(rotatingBeltFieldObjectTwo.getOrientations()[1])) {

                    rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObjectTwo.getOrientations()[0], rotatingBeltFieldObjectTwo.getOrientations()[1]));

                }

                targetBoardElement = getNextBoardElementByMovingDirection(gameState, targetBoardElement.getXY(), rotatingBeltFieldObjectTwo.getOrientations()[1]);

                if (targetBoardElement.isBlueConveyorBelt()) {

                    targetIsBelt = true;

                } else if (targetBoardElement.isBlueRotatingConveyorBelt()) {

                    targetIsBelt = true;

                    RotatingBeltFieldObject rotatingBeltFieldObjectThree = targetBoardElement.getRotatingConveyorBelt();
                    movingDirection = rotatingBeltFieldObjectThree.getOrientations()[1];

                    if (!rotatingBeltFieldObjectTwo.getOrientations()[1].equals(rotatingBeltFieldObjectThree.getOrientations()[1])) {

                        rotatingDirections.add(getRotatingDirection(rotatingBeltFieldObjectThree.getOrientations()[0], rotatingBeltFieldObjectThree.getOrientations()[1]));

                    }

                }

            }

        }

        return new ConveyorBeltTarget(playerID, targetBoardElement, rotatingDirections.toArray(new String[0]), targetIsBelt, movingDirection);

    }

    public BoardElement getNextBoardElementByMovingDirection(GameState gameState, Position currentPosition, String movingDirection) {

        BoardElement[][] gameBoard = gameState.gameBoard.getGameBoard();

        return switch (movingDirection) {
            case "up" -> gameBoard[currentPosition.getY() - 1][currentPosition.getX()];
            case "right" -> gameBoard[currentPosition.getY()][currentPosition.getX() + 1];
            case "down" -> gameBoard[currentPosition.getY() + 1][currentPosition.getX()];
            case "left" -> gameBoard[currentPosition.getY()][currentPosition.getX() - 1];
            default -> null;
        };

    }

    public String getRotatingDirection(String oldOrientation, String newOrientation) {

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
