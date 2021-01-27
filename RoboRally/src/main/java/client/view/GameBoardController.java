package client.view;

import client.utilities.ImageBuilder;
import client.utilities.RobotImageBuilder;
import client.viewmodel.GameBoardViewModel;
import game.gameboard.BoardElement;
import game.utilities.Position;
import game.utilities.PositionLookUp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.HashSet;

public class GameBoardController {

    GameBoardViewModel gameBoardViewModel;

    @FXML
    GridPane gameGrid = new GridPane();
    private int verticalTiles = 10;
    private int horizontalTiles = 13;

    StackPane[][] gameTileArray;
    HashSet<Button> startingPointButtonList;


    /**
     * Connect Controller with ViewModel
     * Build GameBoard-Array
     */
    public void initialize() {
        startingPointButtonList = new HashSet<>();
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

    public void updateBoard() {
        gameGrid = new GridPane();
        for (int i = 0; i < horizontalTiles; i++) {
            for (int j = 0; j < verticalTiles; j++) {
                gameGrid.add(gameTileArray[j][i], i, j);
            }
        }
    }

    public void initStartingPoints() {
        startingPointButtonList = new HashSet<>();
        for (Integer pos : gameBoardViewModel.getStartingPositions()) {
            Button startingPointButton = new Button();
            startingPointButton.setPrefWidth(50);
            startingPointButton.setPrefHeight(50);
            startingPointButton.setStyle("-fx-border-color: blue; -fx-border-radius: 90; -fx-background-color: transparent");
            startingPointButton.setId(String.valueOf(pos));
            startingPointButton.setOnAction(e -> transmitStartingPoint(startingPointButton));
            startingPointButtonList.add(startingPointButton);
            Position p = PositionLookUp.positionToXY.get(pos);
            gameTileArray[p.getX()][p.getY()].getChildren().add(startingPointButton);
        }

    }

    private void transmitStartingPoint(Button startingPointButton) {
        int position = Integer.parseInt(startingPointButton.getId());
        gameBoardViewModel.transmitStartingPosition(position);
    }

    public void setStartingPosition(int robotFigure, int position) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        Position p = PositionLookUp.positionToXY.get(position);
        gameTileArray[p.getX()][p.getY()].getChildren().remove(1);
        gameTileArray[p.getX()][p.getY()].getChildren().add(robotImage);
        startingPointButtonList.removeIf(b -> b.getId().equals(String.valueOf(position)));

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

    public void setOtherRobotStartingPosition(int robotFigure, int position) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        Position p = PositionLookUp.positionToXY.get(position);
        gameTileArray[p.getX()][p.getY()].getChildren().add(robotImage);
    }
}
