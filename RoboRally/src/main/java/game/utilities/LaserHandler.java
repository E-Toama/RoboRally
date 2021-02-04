package game.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.LaserFieldObject;
import game.gameboard.gameboardfieldobjects.WallFieldObject;

public class LaserHandler {

    public void handleBoardLaserFire(GameState gameState, BoardElement laser) {

        LaserFieldObject laserFieldObject = laser.getLaser();
        int count = laserFieldObject.getCount();
        String direction = laserFieldObject.getOrientation();

        handleLaserFire(gameState, laser, count, direction);

    }

    public void handleRobotFire(GameState gameState, BoardElement robotBoardElement) {

        int count = 1;
        String direction = robotBoardElement.getRobot().getOrientation();

        boolean condition = true;

        if (robotBoardElement.isWall()) {

            WallFieldObject wallFieldObject = robotBoardElement.getWalls();

            for (String wallOrientation : wallFieldObject.getOrientations()) {

                if (direction.equals(wallOrientation)) {

                    condition = false;

                }

            }

        }

        if (condition) {

            handleLaserFire(gameState, getNextBoardElementByMovingDirection(gameState, robotBoardElement.getXY(), direction), count, direction);

        }

    }

    private void handleLaserFire(GameState gameState, BoardElement boardElement, int count, String direction) {

        DrawDamageCardHandler drawDamageCardHandler = new DrawDamageCardHandler(gameState);
        boolean condition = true;

        if (boardElement.getRobot() != null) {

            condition = false;

            String[] damageCards = new String[count];

            for (int i = 0; i < count; i++) {

                damageCards[i] = "Spam";

            }

            drawDamageCardHandler.drawDamageCards(boardElement.getRobot().getPlayerID(), damageCards);

        } else if (boardElement.isWall()) {

            WallFieldObject wallFieldObject = boardElement.getWalls();

            for (String wallOrientation : wallFieldObject.getOrientations()) {

                if (direction.equals(wallOrientation)) {

                    condition = false;

                }

            }

        }

        BoardElement nextBordElement = getNextBoardElementByMovingDirection(gameState, boardElement.getXY(), direction);

        if (nextBordElement.isWall()) {

            WallFieldObject wallFieldObject = boardElement.getWalls();

            for (String wallOrientation : wallFieldObject.getOrientations()) {

                String oppositeDirection = nextBordElement.getOppositeDirection(direction);

                if (oppositeDirection.equals(wallOrientation)) {

                    condition = false;

                }

            }

        }

        if (nextBordElement.isAntenna()) {

            condition = false;

        }

        if (condition) {

            handleLaserFire(gameState, nextBordElement, count, direction);

        }

    }

    private BoardElement getNextBoardElementByMovingDirection(GameState gameState, Position currentPosition, String movingDirection) {

        BoardElement[][] gameBoard = gameState.gameBoard.getGameBoard();

        return switch (movingDirection) {
            case "up" -> gameBoard[currentPosition.getY() - 1][currentPosition.getX()];
            case "right" -> gameBoard[currentPosition.getY()][currentPosition.getX() + 1];
            case "down" -> gameBoard[currentPosition.getY() + 1][currentPosition.getX()];
            case "left" -> gameBoard[currentPosition.getY()][currentPosition.getX() - 1];
            default -> null;
        };

    }

}
