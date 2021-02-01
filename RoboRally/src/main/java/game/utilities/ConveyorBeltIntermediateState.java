package game.utilities;

public class ConveyorBeltIntermediateState {

    private final int playerID;
    private final Position newPosition;
    private final String movingOrientation;
    private final String newOrientation;
    private final boolean newPositionIsBelt;

    public ConveyorBeltIntermediateState(int playerID, Position newPosition, String movingOrientation, String newOrientation, boolean newPositionIsBelt) {

        this.playerID = playerID;
        this.newPosition = newPosition;
        this.movingOrientation = movingOrientation;
        this.newOrientation = newOrientation;
        this.newPositionIsBelt = newPositionIsBelt;

    }

    public int getPlayerID() {
        return playerID;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public String getMovingOrientation() {
        return movingOrientation;
    }

    public String getNewOrientation() {
        return newOrientation;
    }

    public boolean isNewPositionBelt() {
        return newPositionIsBelt;
    }

}
