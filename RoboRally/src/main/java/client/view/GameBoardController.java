package client.view;

import client.utilities.ImageBuilder;
import client.utilities.RobotImageBuilder;
import client.viewmodel.GameBoardViewModel;
import game.gameboard.BoardElement;
import game.utilities.Position;
import game.utilities.PositionLookUp;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import utilities.MyLogger;

import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Controller class for the GameBoard
 *
 */
public class GameBoardController {

    GameBoardViewModel gameBoardViewModel;

    GridPane gameGrid = new GridPane();
    private int verticalTiles = 10;
    private int horizontalTiles = 13;

    StackPane[][] gameTileArray;
    HashSet<Button> startingPointButtonList;


    /**
     * connects Controller with ViewModel
     * builds GameBoard-Array
     */
    public void initialize() {
        startingPointButtonList = new HashSet<>();
    }

    /**
     * initializes GameBoard with board tiles
     */
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

    /**
     * creates new GameBoard
     */
    public void updateBoard() {
        gameGrid = new GridPane();
        for (int i = 0; i < horizontalTiles; i++) {
            for (int j = 0; j < verticalTiles; j++) {
                gameGrid.add(gameTileArray[j][i], i, j);
            }
        }
    }

    /**
     * initializes starting point buttons with blue circles for indication
     */
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

    /**
     * passes the chosen starting point button to the gameBoardViewModel
     * @param startingPointButton
     */
    private void transmitStartingPoint(Button startingPointButton) {
        int position = Integer.parseInt(startingPointButton.getId());
        gameBoardViewModel.transmitStartingPosition(position);
        gameBoardViewModel.getStartingPositions().remove(position);
        removeAllStartingPoints();
    }

    /**
     * removes starting point buttons
     */
    public void removeAllStartingPoints() {
        for (Button b : startingPointButtonList) {
            Position p = PositionLookUp.positionToXY.get(Integer.parseInt(b.getId()));
            if (gameTileArray[p.getY()][p.getX()].getChildren().get(1) instanceof Button) {
                gameTileArray[p.getY()][p.getX()].getChildren().remove(1);
            }
        }
    }

    /**
     * places the robot figure of the player on the chosen starting point
     * @param robotFigure of the player
     * @param position of the starting point and the robot
     */
    public void setStartingPosition(int robotFigure, int position) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        Position p = PositionLookUp.positionToXY.get(position);
        gameTileArray[p.getY()][p.getX()].getChildren().add(robotImage);

    }

    /**
     * places the robot figure of other players on the choosen starting point
     * @param robotFigure of the other players
     * @param position of the starting point of the other players
     */
    public void setOtherRobotStartingPosition(int robotFigure, int position) {
        ImageView robotImage = RobotImageBuilder.buildRobotImage(robotFigure);
        Position p = PositionLookUp.positionToXY.get(position);
        gameTileArray[p.getY()][p.getX()].getChildren().add(robotImage);
        gameBoardViewModel.getStartingPositions().removeIf(b -> b == position);
    }

    /**
     * moves a robot from after being deleted from the current position to a new position
     * @param currentPosition is the current position of the player
     * @param newPosition is the new position of the player
     */
    public void move(int currentPosition, int newPosition) {
        Position current = PositionLookUp.positionToXY.get(currentPosition);
        Position newPos = PositionLookUp.positionToXY.get(newPosition);
        gameTileArray[newPos.getY()][newPos.getX()].getChildren().add(deleteRobot(current.getY(), current.getX()));
    }

    /**
     * turns the robot figure on the current position either clockwise or counterclockwise
     * @param currentPosition is the current position of the playder
     * @param direction can be clockwise or counterclockwise
     */
    public void playerTurning(int currentPosition, String direction) {
        Position current = PositionLookUp.positionToXY.get(currentPosition);
        ImageView robotImage = deleteRobot(current.getY(), current.getX());
        if (direction.equals("clockwise")) {
           robotImage.setRotate(robotImage.getRotate() + 90);
        } else {
            robotImage.setRotate(robotImage.getRotate() - 90);
        }
        gameTileArray[current.getY()][current.getX()].getChildren().add(robotImage);

    }

    /**
     * deletes the robot from the old position
     * @param oldRow row of the old roboter position
     * @param oldColumn column of the old roboter position
     * @return
     */
    private ImageView deleteRobot(int oldRow, int oldColumn) {
        return (ImageView) gameTileArray[oldRow][oldColumn].getChildren().remove(1);
    }

    /**
     * gets the girdpane of the GameBoard
     * @return GridPane gameGrid
     */
    public GridPane getGameGrid() {
        return gameGrid;
    }

    /**
     * sets a GameBoardViewModel
     * @param gameBoardViewModel is the current viewModel of the gameBoard
     */
    public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
        this.gameBoardViewModel = gameBoardViewModel;
    }


    /**
     * Removes the Robot from the board in case of Disconnect / Abort
     * @param position of the robot to be removed
     */
    public void removeRobot(int position) {
        Position xy = PositionLookUp.positionToXY.get(position);
        gameTileArray[xy.getY()][xy.getX()].getChildren().remove(1);
    }


    //Methods only used for cheat-codes

    /**
     * Spins the robot in place for about 3 Seconds
     * @param position of the robot to be spun
     */
    public void whirlwind(int position) {
        Position xy = PositionLookUp.positionToXY.get(position);
        ImageView robotImage = (ImageView) gameTileArray[xy.getY()][xy.getX()].getChildren().get(1);
        robotImage.setEffect(new MotionBlur());
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(100), robotImage);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(12);
        rotateTransition.play();
        rotateTransition.setOnFinished(e -> {
            robotImage.setEffect(null);
        });
    }


    /**
     * Generates a quick movement from the current position to the "ceiling" (i.e. top row of the gameboard)
     *
     * @param position of the robot the transition is applied to
     */
    public void skyIsTheLimit(int position) {
        Position xy = PositionLookUp.positionToXY.get(position);
        ImageView robotImage = (ImageView) gameTileArray[xy.getY()][xy.getX()].getChildren().get(1);
        robotImage.setEffect(new MotionBlur());
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(80), robotImage);
        translateTransition.setToY(-50 * xy.getY());
        translateTransition.setCycleCount(10);
        translateTransition.setAutoReverse(true);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.play();
        translateTransition.setOnFinished(e -> {
            robotImage.setEffect(null);

        });
    }

    /**
     * Fades out the robot by setting the opacity to 0
     * @param position of the robot to be faded out
     */
    public void invisible(int position) {
        Position xy = PositionLookUp.positionToXY.get(position);
        ImageView robotImage = (ImageView) gameTileArray[xy.getY()][xy.getX()].getChildren().get(1);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), robotImage);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    /**
     * Fades in the robot by setting the ImageView's opacity back to 100 %
     * @param position of the robot to be faded in
     */
    public void reappear(int position) {
        Position xy = PositionLookUp.positionToXY.get(position);
        ImageView robotImage = (ImageView) gameTileArray[xy.getY()][xy.getX()].getChildren().get(1);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), robotImage);
        fadeTransition.setToValue(100);
        fadeTransition.play();
    }

}
