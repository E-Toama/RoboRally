package game.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.BeltFieldObject;
import game.gameboard.gameboardfieldobjects.RotatingBeltFieldObject;
import game.player.PlayerMat;

public class ConveyorBeltsHelper {

    public ConveyorBeltIntermediateState calculateTargetPositionGreenConveyorBelt(GameState gameState, PlayerMat playerMat) {

        Position currentPosition = playerMat.getRobot().getRobotXY();
        BoardElement[][] gameBoard = gameState.gameBoard.getGameBoard();
        BoardElement currentBoardElement = gameBoard[currentPosition.getY()][currentPosition.getX()];

        if (currentBoardElement.isGreenConveyorBelt()) {

            BeltFieldObject beltFieldObject = currentBoardElement.getConveyorBelt();
            BoardElement targetBoardElement = getNextBoardElement(gameState, currentPosition, beltFieldObject.getOrientation());

            if (targetBoardElement.isGreenConveyorBelt()) {

                return new ConveyorBeltIntermediateState(playerMat.getPlayer().getPlayerID(), targetBoardElement.getXY(), beltFieldObject.getOrientation(), playerMat.getRobot().getOrientation(), true);

            } else if (targetBoardElement.isGreenRotatingConveyorBelt()) {

                return new ConveyorBeltIntermediateState(playerMat.getPlayer().getPlayerID(), targetBoardElement.getXY(), beltFieldObject.getOrientation(), playerMat.getRobot().getOrientation(), true);

            } else {

                return new ConveyorBeltIntermediateState(playerMat.getPlayer().getPlayerID(), targetBoardElement.getXY(), beltFieldObject.getOrientation(), playerMat.getRobot().getOrientation(), false);

            }

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject = currentBoardElement.getRotatingConveyorBelt();
            BoardElement targetBoardElement = getNextBoardElement(gameState, currentPosition, rotatingBeltFieldObject.getOrientations()[1]);

            String newOrientation = getNewOrientation(playerMat.getRobot().getOrientation(), rotatingBeltFieldObject.getOrientations());

            if (targetBoardElement.isGreenConveyorBelt()) {

                return new ConveyorBeltIntermediateState(playerMat.getPlayer().getPlayerID(), targetBoardElement.getXY(), rotatingBeltFieldObject.getOrientations()[1], newOrientation, true);

            } else if (targetBoardElement.isGreenRotatingConveyorBelt()) {

                return new ConveyorBeltIntermediateState(playerMat.getPlayer().getPlayerID(), targetBoardElement.getXY(), rotatingBeltFieldObject.getOrientations()[1], newOrientation, true);

            } else {

                return new ConveyorBeltIntermediateState(playerMat.getPlayer().getPlayerID(), targetBoardElement.getXY(), rotatingBeltFieldObject.getOrientations()[1], newOrientation, false);

            }

        }

    }

    public ConveyorBeltIntermediateState calculateTargetPositionBlueConveyorBelt(GameState gameState, PlayerMat playerMat) {

        int playerID = playerMat.getPlayer().getPlayerID();
        Position currentPosition = playerMat.getRobot().getRobotXY();
        BoardElement[][] gameBoard = gameState.gameBoard.getGameBoard();
        BoardElement currentBoardElement = gameBoard[currentPosition.getY()][currentPosition.getX()];

        if (currentBoardElement.isBlueConveyorBelt()) {

            BeltFieldObject beltFieldObject1 = currentBoardElement.getConveyorBelt();
            BoardElement targetBoardElement1 = getNextBoardElement(gameState, currentPosition, beltFieldObject1.getOrientation());
            Position target1Position = targetBoardElement1.getXY();

            if (targetBoardElement1.isBlueConveyorBelt()) {

                BeltFieldObject beltFieldObject2 = targetBoardElement1.getConveyorBelt();
                BoardElement targetBoardElement2 = getNextBoardElement(gameState, target1Position, beltFieldObject2.getOrientation());

                if (targetBoardElement2.isBlueConveyorBelt()) {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), beltFieldObject2.getOrientation(), playerMat.getRobot().getOrientation(), true);

                } else if (targetBoardElement2.isBlueRotatingConveyorBelt()) {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), beltFieldObject2.getOrientation(), playerMat.getRobot().getOrientation(), true);

                } else {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), beltFieldObject2.getOrientation(), playerMat.getRobot().getOrientation(), false);

                }

            } else if (targetBoardElement1.isBlueRotatingConveyorBelt()) {

                RotatingBeltFieldObject rotatingBeltFieldObject2 = targetBoardElement1.getRotatingConveyorBelt();
                BoardElement targetBoardElement2 = getNextBoardElement(gameState, target1Position, rotatingBeltFieldObject2.getOrientations()[1]);

                String newOrientation = getNewOrientation(playerMat.getRobot().getOrientation(), rotatingBeltFieldObject2.getOrientations());

                if (targetBoardElement2.isBlueConveyorBelt()) {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), rotatingBeltFieldObject2.getOrientations()[1], newOrientation, true);

                } else if (targetBoardElement2.isBlueRotatingConveyorBelt()) {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), rotatingBeltFieldObject2.getOrientations()[1], newOrientation, true);

                } else {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), rotatingBeltFieldObject2.getOrientations()[1], newOrientation, false);

                }


            } else {

                return new ConveyorBeltIntermediateState(playerID, targetBoardElement1.getXY(), beltFieldObject1.getOrientation(), playerMat.getRobot().getOrientation(), false);

            }

        } else {

            RotatingBeltFieldObject rotatingBeltFieldObject1 = currentBoardElement.getRotatingConveyorBelt();
            BoardElement targetBoardElement1 = getNextBoardElement(gameState, currentPosition, rotatingBeltFieldObject1.getOrientations()[1]);
            Position target1Position = targetBoardElement1.getXY();

            String newOrientation = getNewOrientation(playerMat.getRobot().getOrientation(), rotatingBeltFieldObject1.getOrientations());

            if (targetBoardElement1.isBlueConveyorBelt()) {

                BeltFieldObject beltFieldObject2 = targetBoardElement1.getConveyorBelt();
                BoardElement targetBoardElement2 = getNextBoardElement(gameState, target1Position, beltFieldObject2.getOrientation());

                if (targetBoardElement2.isBlueConveyorBelt()) {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), beltFieldObject2.getOrientation(), newOrientation, true);

                } else if (targetBoardElement2.isBlueRotatingConveyorBelt()) {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), beltFieldObject2.getOrientation(), newOrientation, true);

                } else {

                    return new ConveyorBeltIntermediateState(playerID, targetBoardElement2.getXY(), beltFieldObject2.getOrientation(), newOrientation, false);

                }

            } else if (targetBoardElement1.isBlueRotatingConveyorBelt()) {

                RotatingBeltFieldObject rotatingBeltFieldObject2 = targetBoardElement1.getRotatingConveyorBelt();
                BoardElement targetBoardElement2 = getNextBoardElement(gameState, target1Position, rotatingBeltFieldObject2.getOrientations()[1]);

                String newOrientation2 = getNewOrientation(newOrientation, rotatingBeltFieldObject1.getOrientations());

                if (targetBoardElement2.isBlueConveyorBelt()) {

                    return null;

                } else if (targetBoardElement2.isBlueRotatingConveyorBelt()) {

                    return null;

                } else {

                    return null;

                }


            } else {

                return new ConveyorBeltIntermediateState(playerID, targetBoardElement1.getXY(), rotatingBeltFieldObject1.getOrientations()[1], playerMat.getRobot().getOrientation(), false);

            }

        }

    }

    private BoardElement getNextBoardElement(GameState gameState, Position position, String orientation) {

        BoardElement[][] gameBoard = gameState.gameBoard.getGameBoard();

        return switch (orientation) {
            case "up" -> gameBoard[position.getY() - 1][position.getX()];
            case "right" -> gameBoard[position.getY()][position.getX() + 1];
            case "down" -> gameBoard[position.getY() + 1][position.getX()];
            case "left" -> gameBoard[position.getY()][position.getX() - 1];
            default -> null;
        };

    }

    private String getNewOrientation(String orientation, String[] orientations) {

        String direction = getDirection(orientations);

        if (direction.equals("right")) {

            return switch (orientation) {
                case "up" -> "right";
                case "left" -> "up";
                case "down" -> "left";
                case "right" -> "down";
                default -> "";
            };

        } else {

            return switch (orientation) {
                case "up" -> "left";
                case "left" -> "down";
                case "down" -> "right";
                case "right" -> "up";
                default -> "";
            };

        }

    }

    private String getDirection(String[] orientations) {

        String returnValue = "";

        switch (orientations[0]) {
            case "up" -> {
                switch (orientations[1]) {
                    case "right" -> returnValue = "right";
                    case "left" -> returnValue = "left";
                }
            }
            case "right" -> {
                switch (orientations[1]) {
                    case "up" -> returnValue = "left";
                    case "down" -> returnValue = "right";
                }
            }
            case "down" -> {
                switch (orientations[1]) {
                    case "right" -> returnValue = "left";
                    case "left" -> returnValue = "right";
                }
            }
            case "left" -> {
                switch (orientations[1]) {
                    case "up" -> returnValue = "right";
                    case "down" -> returnValue = "left";
                }
            }
        }

        return returnValue;

    }

    public String getTurning(String oldOrientation, String newOrientation) {

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
