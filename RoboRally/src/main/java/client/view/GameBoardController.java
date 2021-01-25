package client.view;

import client.utilities.ImageBuilder;
import client.utilities.RobotImageBuilder;
import client.viewmodel.GameBoardViewModel;
import game.gameboard.BoardElement;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class GameBoardController {

    GameBoardViewModel gameBoardViewModel = new GameBoardViewModel();

    @FXML
    GridPane gameGrid;
    private int verticalTiles = 10;
    private int horizontalTiles = 13;

    StackPane[][] gameTileArray;

    public void initialize() {
        BoardElement[][] gameBoard = gameBoardViewModel.getGameBoard();
        for (int i = 0; i < horizontalTiles; i++) {
            for (int j = 0; j < verticalTiles; j++) {
                ImageView imageOfBoardElement = ImageBuilder.buildImage(gameBoard[j][i]);
                StackPane pane = new StackPane(imageOfBoardElement);
                pane.getChildren().add(imageOfBoardElement);
                gameTileArray[i][j] = pane;
                gameGrid.add(pane, i, j);
            }
        }
    }

    public void setStartingPosition(int robotFigure, int startingPosition) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);

    }

    public void updateRobotPosition(int robotFigure, int oldPosition, int newPosition) {

    }
}
