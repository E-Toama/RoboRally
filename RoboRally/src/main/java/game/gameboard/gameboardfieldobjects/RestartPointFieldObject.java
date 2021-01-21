package game.gameboard.gameboardfieldobjects;

public class RestartPointFieldObject extends GameBoardFieldObject {

    //ToDo: does RestartPoint has an orientation?
    private String orientation;

    public RestartPointFieldObject(String orientation) {
        super("RestartPoint");
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }
}
