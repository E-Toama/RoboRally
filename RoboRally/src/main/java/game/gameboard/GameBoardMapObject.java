package game.gameboard;

import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;

/**
 *
 *
 */
public class GameBoardMapObject {

    private final int position;
    private final GameBoardFieldObject[] field;

    public GameBoardMapObject(int position, GameBoardFieldObject[] field) {

        this.position = position;
        this.field = field;

    }

    public int getPosition() {
        return position;
    }

    public GameBoardFieldObject[] getField() {
        return field;
    }

}
