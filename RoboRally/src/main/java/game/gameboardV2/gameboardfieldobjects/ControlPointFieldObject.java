package game.gameboardV2.gameboardfieldobjects;

public class ControlPointFieldObject extends GameBoardFieldObject {

    private final int count;

    public ControlPointFieldObject(int count) {
        super("ControlPoint");
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}
