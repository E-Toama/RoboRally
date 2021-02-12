package client.utilities;

import game.gameboard.BoardElement;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import game.gameboard.gameboardfieldobjects.LaserFieldObject;
import game.gameboard.gameboardfieldobjects.WallFieldObject;
import game.utilities.DrawDamageCardHandler;
import game.utilities.GameState;
import game.utilities.Position;
import game.utilities.PositionLookUp;

/**
 * Doc: Yashar
 */
public class ClientLaserHandler {

    private BoardElement[][] inputGameBoard;
    private BoardElement[][] updatedGameBoard;

    public BoardElement[][] getUpdatedGameBoard() {
        return updatedGameBoard;
    }

    public void handleBoardLasers(BoardElement[][] gameBoard, BoardElement laser) {
        inputGameBoard = gameBoard;
        updatedGameBoard = gameBoard;
        LaserFieldObject laserFieldObject = laser.getLaser();
        int count = laserFieldObject.getCount();
        String direction = laserFieldObject.getOrientation();

        handleLaserPositions(gameBoard, laser, count, direction);

    }

    private void handleLaserPositions(BoardElement[][] gameBoard, BoardElement boardElement, int count, String direction) {


        BoardElement nextBordElement = getNextBoardElementByMovingDirection(inputGameBoard, boardElement.getXY(), direction);

        //Base case for recursive function: If no neighboring tile exists or element itself is already a laser
        if (nextBordElement == null || nextBordElement.isLaser()) {
            return;
        }

        if (nextBordElement.isWall() && !nextBordElement.isLaser()) {
            Position xy = nextBordElement.getXY();
            BoardElement updatedElement = new BoardElement(nextBordElement.getPosition(), new GameBoardFieldObject[]{new LaserFieldObject(direction, count), nextBordElement.getWalls()});
            updatedGameBoard[xy.getY()][xy.getX()] = updatedElement;
            return;
        }

        if ("Empty".equals(nextBordElement.getField()[0].getType())) {
            Position xy = nextBordElement.getXY();
            BoardElement updatedElement = new BoardElement(nextBordElement.getPosition(), new GameBoardFieldObject[]{new LaserFieldObject(direction, count)});
            updatedGameBoard[xy.getY()][xy.getX()] = updatedElement;
        }

        if (nextBordElement.isBlueConveyorBelt()) {
            Position xy = nextBordElement.getXY();
            BoardElement updatedElement = new BoardElement(nextBordElement.getPosition(), new GameBoardFieldObject[]{nextBordElement.getConveyorBelt(), new LaserFieldObject(direction, count)});
            updatedGameBoard[xy.getY()][xy.getX()] = updatedElement;
        }

        handleLaserPositions(updatedGameBoard, nextBordElement, count, direction);

    }

    private BoardElement getNextBoardElementByMovingDirection(BoardElement[][] gameBoard, Position currentPosition, String movingDirection) {


        BoardElement boardElement = null;

        switch (movingDirection) {
            case "up" -> {
                if (currentPosition.getY() != 0) {
                    boardElement = gameBoard[currentPosition.getY() - 1][currentPosition.getX()];
                }
            }
            case "right" -> {
                if (currentPosition.getX() < 12) {
                    boardElement = gameBoard[currentPosition.getY()][currentPosition.getX() + 1];
                }
            }
            case "down" -> {
                if (currentPosition.getY() < 9) {
                    boardElement = gameBoard[currentPosition.getY() + 1][currentPosition.getX()];
                }
            }
            case "left" -> {
                if (currentPosition.getX() != 0) {
                    boardElement = gameBoard[currentPosition.getY()][currentPosition.getX() - 1];
                }
            }
        }

        return boardElement;

    }

}
