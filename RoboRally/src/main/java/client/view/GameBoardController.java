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

    GameBoardViewModel gameBoardViewModel;

    @FXML
    GridPane gameGrid = new GridPane();
    private int verticalTiles = 10;
    private int horizontalTiles = 13;

    StackPane[][] gameTileArray;


    /**
     * Connect Controller with ViewModel
     * Build GameBoard-Array
     */
    public void initialize() {

    }

    public void initBoard() {
        BoardElement[][] gameBoard = gameBoardViewModel.getGameBoard();
        gameTileArray = new StackPane[gameBoard.length][gameBoard[0].length];
        for (int i = 0; i < horizontalTiles; i++) {
            for (int j = 0; j < verticalTiles; j++) {
                ImageView imageOfBoardElement = ImageBuilder.buildImage(gameBoard[j][i]);
                StackPane pane = new StackPane();
                pane.getChildren().add(imageOfBoardElement);
                gameTileArray[j][i] = pane;
                gameGrid.add(pane, i, j);
            }
        }
    }

    public void setStartingPosition(int robotFigure, int row, int column) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        gameTileArray[row][column].getChildren().add(robotImage);

    }

    public void updateRobotPosition(int robotFigure, int oldRow, int oldColumn, int newRow, int newColumn) {
        gameTileArray[newRow][newColumn].getChildren().add(deleteRobot(oldRow, oldColumn));
    }

    private ImageView deleteRobot(int oldRow, int oldColumn) {
        return (ImageView) gameTileArray[oldRow][oldColumn].getChildren().remove(1);
    }

    public GridPane getGameGrid() {
        return gameGrid;
    }

    public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
        this.gameBoardViewModel = gameBoardViewModel;
    }
}
