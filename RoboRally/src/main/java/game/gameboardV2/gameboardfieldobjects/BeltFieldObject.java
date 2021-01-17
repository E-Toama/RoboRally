package game.gameboardV2.gameboardfieldobjects;

public class BeltFieldObject extends GameBoardFieldObject {

    private final String orientation;
    private final int speed;

    public BeltFieldObject(String orientation, int speed) {
        super("Belt");
        this.orientation = orientation;
        this.speed = speed;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getSpeed() {
        return speed;
    }

}
