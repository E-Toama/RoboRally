package game.gameboardV2.gameboardfieldobjects;

public class GearFieldObject extends GameBoardFieldObject {

    private final String orientation;

    public GearFieldObject(String orientation) {
        super("Gear");
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }

}
