package AI.logic;

import game.gameboard.BoardElement;
import game.utilities.Position;

public class AIGameState {

    private Position currentPosition;
    private String currentOrientation;
    private BoardElement currentBoardElement;

    private Position intermediatePosition;
    private String intermediateOrientation;
    private BoardElement intermediateBoardElement;

    private String gameBoardName;
    private int targetCheckpoint;

    private int[][] orientationUpRating;
    private int[][] orientationLeftRating;
    private int[][] orientationDownRating;
    private int[][] orientationRightRating;

    public AIGameState() {}

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public String getCurrentOrientation() {
        return currentOrientation;
    }

    public BoardElement getCurrentBoardElement() {
        return currentBoardElement;
    }

    public Position getIntermediatePosition() {
        return intermediatePosition;
    }

    public String getIntermediateOrientation() {
        return intermediateOrientation;
    }


    public BoardElement getIntermediateBoardElement() {
        return intermediateBoardElement;
    }

    public int[][] getOrientationUpRating() {
        return orientationUpRating;
    }

    public int[][] getOrientationLeftRating() {
        return orientationLeftRating;
    }

    public int[][] getOrientationDownRating() {
        return orientationDownRating;
    }

    public int[][] getOrientationRightRating() {
        return orientationRightRating;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setCurrentOrientation(String currentOrientation) {
        this.currentOrientation = currentOrientation;
    }

    public void setCurrentBoardElement(BoardElement currentBoardElement) {
        this.currentBoardElement = currentBoardElement;
    }

    public void setIntermediatePosition(Position intermediatePosition) {
        this.intermediatePosition = intermediatePosition;
    }

    public void setIntermediateOrientation(String intermediateOrientation) {
        this.intermediateOrientation = intermediateOrientation;
    }

    public void setIntermediateBoardElement(BoardElement intermediateBoardElement) {
        this.intermediateBoardElement = intermediateBoardElement;
    }

    public void setRatingMaps(String gameBoardName, int targetCheckPoint) {

        this.gameBoardName = gameBoardName;
        this.targetCheckpoint = targetCheckPoint;

        switch (gameBoardName) {

            case "DizzyHighway" -> {

                this.orientationUpRating = null;
                this.orientationLeftRating = null;
                this.orientationDownRating = null;
                this.orientationRightRating = null;

            }

            case "ExtraCrispy" -> {

                switch (targetCheckPoint) {

                    case 1 -> {

                        this.orientationUpRating = null;
                        this.orientationLeftRating = null;
                        this.orientationDownRating = null;
                        this.orientationRightRating = null;

                    }

                    case 2 -> {

                        this.orientationUpRating = null;
                        this.orientationLeftRating = null;
                        this.orientationDownRating = null;
                        this.orientationRightRating = null;

                    }

                    case 3 -> {

                        this.orientationUpRating = null;
                        this.orientationLeftRating = null;
                        this.orientationDownRating = null;
                        this.orientationRightRating = null;

                    }

                    case 4 -> {

                        this.orientationUpRating = null;
                        this.orientationLeftRating = null;
                        this.orientationDownRating = null;
                        this.orientationRightRating = null;

                    }

                }

            }

        }

    }

    public void turningClockwise() {

        switch (currentOrientation) {
            case "up" -> currentOrientation = "left";
            case "left" -> currentOrientation = "down";
            case "down" -> currentOrientation = "right";
            case "right" -> currentOrientation = "up";
        }

    }

    public void turningCounterClockwise() {

        switch (currentOrientation) {
            case "up" -> currentOrientation = "right";
            case "right" -> currentOrientation = "down";
            case "down" -> currentOrientation = "left";
            case "left" -> currentOrientation = "up";
        }

    }


}
