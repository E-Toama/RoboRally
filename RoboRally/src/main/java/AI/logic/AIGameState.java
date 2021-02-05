package AI.logic;

import game.gameboard.BoardElement;
import game.gameboard.GameBoard;
import game.utilities.Position;

public class AIGameState {

    private Position currentPosition;
    private String currentOrientation;
    private BoardElement currentBoardElement;

    private Position intermediatePosition;
    private String intermediateOrientation;
    private BoardElement intermediateBoardElement;

    private int activePhase;

    private String gameBoardName;
    private GameBoard gameBoard;

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

    public String getGameBoardName() {
        return gameBoardName;
    }

    public int getTargetCheckpoint() {
        return targetCheckpoint;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getActivePhase() {
        return activePhase;
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

    public void setGameBoardName(String gameBoardName) {
        this.gameBoardName = gameBoardName;
    }

    public void setTargetCheckpoint(int targetCheckpoint) {
        this.targetCheckpoint = targetCheckpoint;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setActivePhase(int activePhase) {
        this.activePhase = activePhase;
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
