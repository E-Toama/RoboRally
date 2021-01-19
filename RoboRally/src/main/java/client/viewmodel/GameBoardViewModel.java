package client.viewmodel;

import client.utilities.BoardTile;
import client.utilities.ImageBuilder;
import client.view.BoardTileView;
import game.gameboard.BoardElement;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameBoardViewModel {

    private double boardWidth = 650;
    private double boardHeight = 500;

    private int verticalTiles = 10;
    private int horizontalTiles = 13;

    double gridWidth = boardWidth / horizontalTiles;
    double gridHeight = boardHeight / verticalTiles;

    BoardTile[][] playfield = new BoardTile[verticalTiles][horizontalTiles];

    public GridPane createGameBoardView(BoardElement[][] gameBoard) {

        GridPane gridPane = new GridPane();
        for (int i = 0; i < horizontalTiles; i++) {
            for (int j = 0; j < verticalTiles; j++) {
                ImageView imageOfBoardElement = ImageBuilder.buildImage(gameBoard[j][i]);
                BoardTile boardTile = new BoardTile(imageOfBoardElement);
                gridPane.add(boardTile, i, j);
            }
        }
        return gridPane;

    }
}
