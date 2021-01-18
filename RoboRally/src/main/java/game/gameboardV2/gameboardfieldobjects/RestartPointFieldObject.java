package game.gameboardV2.gameboardfieldobjects;

public class RestartPointFieldObject extends GameBoardFieldObject {

    //ToDo: does RestartPoint has an orientation?
    String orientation;

    public RestartPointFieldObject(String orientation) {
        super("RestartPoint");
        this.orientation = orientation;
    }

}
