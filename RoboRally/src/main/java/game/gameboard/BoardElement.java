package game.gameboard;

import game.Robots.Robot;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;

public class BoardElement {

    private int position;
    private GameBoardFieldObject[] field;
    private Robot robot;

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

    public Robot getRobot() {
        return robot;
    }
}
