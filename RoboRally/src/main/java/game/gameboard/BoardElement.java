package game.gameboard;

import client.utilities.BoardTile;
import client.utilities.ImageBuilder;
import game.gameboard.gameboardfieldobjects.GameBoardFieldObject;
import javafx.scene.image.Image;

public class BoardElement {

    private int position;
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

    public BoardTile getBoardTile() {

        Image image = ImageBuilder.buildImage();

        return new BoardTile(image);

    }
}
