package client.view;

import client.utilities.ImageBuilder;
import client.utilities.RobotImageBuilder;
import client.viewmodel.GameBoardViewModel;
import game.gameboard.BoardElement;
import game.utilities.Position;
import game.utilities.PositionLookUp;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import utilities.MyLogger;

import java.util.HashSet;
import java.util.logging.Logger;

public class GameBoardController {

    GameBoardViewModel gameBoardViewModel;

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
            gameTileArray[p.getY()][p.getX()].getChildren().add(startingPointButton);
        }

    }

    private void transmitStartingPoint(Button startingPointButton) {
        int position = Integer.parseInt(startingPointButton.getId());
        gameBoardViewModel.transmitStartingPosition(position);
        gameBoardViewModel.getStartingPositions().remove(position);
        removeAllStartingPoints();
    }

    public void removeAllStartingPoints() {
        for (Button b : startingPointButtonList) {
            Position p = PositionLookUp.positionToXY.get(Integer.parseInt(b.getId()));
            if (gameTileArray[p.getY()][p.getX()].getChildren().get(1) instanceof Button) {
                gameTileArray[p.getY()][p.getX()].getChildren().remove(1);
            }
        }
    }

    public void setStartingPosition(int robotFigure, int position) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        Position p = PositionLookUp.positionToXY.get(position);
        gameTileArray[p.getY()][p.getX()].getChildren().add(robotImage);

    }

    public void setOtherRobotStartingPosition(int robotFigure, int position) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        Position p = PositionLookUp.positionToXY.get(position);
        gameTileArray[p.getY()][p.getX()].getChildren().add(robotImage);
        gameBoardViewModel.getStartingPositions().removeIf(b -> b == position);
    }

    public void move(int robotFigure, int currentPosition, int newPosition) {
        Position current = PositionLookUp.positionToXY.get(currentPosition);
        Position newPos = PositionLookUp.positionToXY.get(newPosition);
        gameTileArray[newPos.getY()][newPos.getX()].getChildren().add(deleteRobot(current.getY(), current.getX()));
    }

    public void playerTurning(int currentPosition, String direction) {
        Position current = PositionLookUp.positionToXY.get(currentPosition);
        ImageView robotImage = deleteRobot(current.getY(), current.getX());
        if (direction.equals("clockwise")) {
            robotImage.setRotate(90);
        } else {
            robotImage.setRotate(270);
        }
        gameTileArray[current.getY()][current.getX()].getChildren().add(robotImage);

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
