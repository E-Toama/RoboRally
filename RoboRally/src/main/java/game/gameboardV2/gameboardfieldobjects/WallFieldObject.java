package game.gameboardV2.gameboardfieldobjects;

public class WallFieldObject extends GameBoardFieldObject {

    private final String[] orientations;

    public WallFieldObject(String[] orientations) {
        super("Wall");
        this.orientations = orientations;
    }

    public String[] getOrientations() {
        return orientations;
    }

}
