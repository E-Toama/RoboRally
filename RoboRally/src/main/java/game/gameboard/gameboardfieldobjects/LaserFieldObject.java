package game.gameboard.gameboardfieldobjects;

public class LaserFieldObject extends GameBoardFieldObject {

    private final String orientation;
    private final int count;

    public LaserFieldObject(String orientation, int count) {
        super("Laser");
        this.orientation = orientation;
        this.count = count;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getCount() {
        return count;
    }

}
