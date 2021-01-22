package game.gameboard;

import client.utilities.BoardTile;
import client.utilities.ImageBuilder;
import game.Robots.Robot;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    public BoardTile getBoardTile() {

        ImageView imageView = ImageBuilder.buildImage(this);

        return new BoardTile(imageView);

    }

    public Robot getRobot() {
        return robot;
    }
}
