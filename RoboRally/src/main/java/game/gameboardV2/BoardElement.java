package game.gameboardV2;

import game.gameboardV2.gameboardfieldobjects.GameBoardFieldObject;

public class BoardElement {

    private int position;
    private int x;
    private int y;
    private GameBoardFieldObject[] field;

    public BoardElement(int position, GameBoardFieldObject[] field) {
        this.position = position;
        this.field = field;
    }

    public GameBoardMapObject returnGameBoardMapObject() {
        return new GameBoardMapObject(position, field);
    }

    public GameBoardFieldObject[] getField() {
        return field;
    }
}
