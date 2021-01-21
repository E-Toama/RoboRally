package client.viewmodel;

import client.view.BoardTileView;
import game.gameboard.BoardElement;
import javafx.scene.Group;
import javafx.scene.Scene;

public class GameBoardViewModel {

    private double boardWidth = 650;
    private double boardHeight = 500;

    private int verticalTiles = 10;
    private int horizontalTiles = 13;

    double gridWidth = boardWidth / horizontalTiles;
    double gridHeight = boardHeight / verticalTiles;

    BoardTileView[][] playfield = new BoardTileView[verticalTiles][horizontalTiles];

    public Scene createGameBoardView(BoardElement[][] gameBoard) {
        Group root = new Group();
        for (int i = 0; i < horizontalTiles; i++) {
            for (int j = 0; j < verticalTiles; j++) {
                BoardTileView tile = new BoardTileView(gameBoard[j][i], i * gridWidth, j * gridHeight, gridHeight, gridWidth);
                root.getChildren().add(tile);
                playfield[j][i] = tile;
            }
        }
        return new Scene(root, boardWidth, boardHeight);

    }
}
