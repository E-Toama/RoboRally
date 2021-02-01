package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;

public class RestartPointFieldObject extends GameBoardFieldObject {

    //ToDo: does RestartPoint has an orientation?
    private String orientation;

    public RestartPointFieldObject(String orientation) {
        super("RestartPoint");
        this.orientation = orientation;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

    }

    public String getOrientation() {
        return orientation;
    }
}
