package game.utilities;

import game.gameboard.BoardElement;

public class ConveyorBeltTarget {

    private final int playerID;
    private final BoardElement targetBoardElement;
    private final String[] rotationDirection;
    private final boolean targetIsBelt;
    private final String movingDirection;

    public ConveyorBeltTarget(int playerID, BoardElement targetBoardElement, String[] rotationDirection, boolean targetIsBelt, String movingDirection) {

        this.playerID = playerID;
        this.targetBoardElement = targetBoardElement;
        this.rotationDirection = rotationDirection;
        this.targetIsBelt = targetIsBelt;
        this.movingDirection = movingDirection;

    }

    public int getPlayerID() {
        return playerID;
    }

    public BoardElement getTargetBoardElement() {
        return targetBoardElement;
    }

    public String[] getRotationDirection() {
        return rotationDirection;
    }

    public boolean getTargetIsBelt() {
        return targetIsBelt;
    }

    public String getMovingDirection() {
        return movingDirection;
    }

}
