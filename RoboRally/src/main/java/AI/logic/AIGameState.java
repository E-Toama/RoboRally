package AI.logic;

import AI.logic.simplecards.CardS;
import AI.logic.utilities.tablebases.CSVHandler;
import AI.logic.utilities.tablebases.MapArrays;
import game.gameboard.BoardElement;
import game.gameboard.GameBoard;
import game.utilities.Position;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class AIGameState {

    private Position startPosition;
    private BoardElement startBoardElement;

    private Position currentPosition;
    private String currentOrientation = "right";
    private BoardElement currentBoardElement;

    private Position intermediatePosition;
    private String intermediateOrientation;
    private BoardElement intermediateBoardElement;
    private CardS[] intermediateRegister;

    private boolean wasRebooted = false;

    private int activePhase;

    private String gameBoardName;
    private GameBoard gameBoard;

    private int targetCheckpoint;

    private int[][] orientationUpRating;
    private int[][] orientationLeftRating;
    private int[][] orientationDownRating;
    private int[][] orientationRightRating;

    private int[][] intermediateOrientationUpRating;
    private int[][] intermediateOrientationLeftRating;
    private int[][] intermediateOrientationDownRating;
    private int[][] intermediateOrientationRightRating;


    public AIGameState() {}

    public Position getStartPosition() {
        return startPosition;
    }

    public BoardElement getStartBoardElement() {
        return startBoardElement;
    }

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

    public boolean getWasRebooted() {
        return wasRebooted;
    }

    public CardS[] getIntermediateRegister() {
        return intermediateRegister;
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

    public int[][] getIntermediateOrientationDownRating() {
        return intermediateOrientationDownRating;
    }

    public int[][] getIntermediateOrientationLeftRating() {
        return intermediateOrientationLeftRating;
    }

    public int[][] getIntermediateOrientationRightRating() {
        return intermediateOrientationRightRating;
    }

    public int[][] getIntermediateOrientationUpRating() {
        return intermediateOrientationUpRating;
    }

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setStartBoardElement(BoardElement startBoardElement) {
        this.startBoardElement = startBoardElement;
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

    public void setIntermediateRegister(CardS[] intermediateRegister) {
        this.intermediateRegister = intermediateRegister;
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

                this.orientationUpRating = MapArrays.dizzyRight;
                this.orientationRightRating = MapArrays.dizzyRight;
                this.orientationDownRating = MapArrays.dizzyRight;
                this.orientationLeftRating = MapArrays.dizzyRight;

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

    public void setIntermediateOrientationUpRating(int[][] intermediateOrientationUpRating) {
        this.intermediateOrientationUpRating = intermediateOrientationUpRating;
    }

    public void setIntermediateOrientationLeftRating(int[][] intermediateOrientationLeftRating) {
        this.intermediateOrientationLeftRating = intermediateOrientationLeftRating;
    }

    public void setIntermediateOrientationDownRating(int[][] intermediateOrientationDownRating) {
        this.intermediateOrientationDownRating = intermediateOrientationDownRating;
    }

    public void setIntermediateOrientationRightRating(int[][] intermediateOrientationRightRating) {
        this.intermediateOrientationRightRating = intermediateOrientationRightRating;
    }

    public void setIntermediateRatingMaps(String gameBoardName, int targetCheckPoint) {

        this.gameBoardName = gameBoardName;
        this.targetCheckpoint = targetCheckPoint;

        switch (gameBoardName) {

            case "DizzyHighway" -> {

                this.intermediateOrientationUpRating = null;
                this.intermediateOrientationLeftRating = null;
                this.intermediateOrientationDownRating = null;
                this.intermediateOrientationRightRating = null;

            }

            case "ExtraCrispy" -> {

                switch (targetCheckPoint) {

                    case 1 -> {

                        this.intermediateOrientationUpRating = null;
                        this.intermediateOrientationLeftRating = null;
                        this.intermediateOrientationDownRating = null;
                        this.intermediateOrientationRightRating = null;

                    }

                    case 2 -> {

                        this.intermediateOrientationUpRating = null;
                        this.intermediateOrientationLeftRating = null;
                        this.intermediateOrientationDownRating = null;
                        this.intermediateOrientationRightRating = null;

                    }

                    case 3 -> {

                        this.intermediateOrientationUpRating = null;
                        this.intermediateOrientationLeftRating = null;
                        this.intermediateOrientationDownRating = null;
                        this.intermediateOrientationRightRating = null;

                    }

                    case 4 -> {

                        this.intermediateOrientationUpRating = null;
                        this.intermediateOrientationLeftRating = null;
                        this.intermediateOrientationDownRating = null;
                        this.intermediateOrientationRightRating = null;

                    }

                }

            }

        }

    }

    public void setWasRebooted(boolean wasRebooted) {
        this.wasRebooted = wasRebooted;
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

    public void turningClockwiseIntermediate() {

        switch (intermediateOrientation) {
            case "up" -> intermediateOrientation = "left";
            case "left" -> intermediateOrientation = "down";
            case "down" -> intermediateOrientation = "right";
            case "right" -> intermediateOrientation = "up";
        }

    }

    public void turningCounterClockwiseIntermediate() {

        switch (intermediateOrientation) {
            case "up" -> intermediateOrientation = "right";
            case "right" -> intermediateOrientation = "down";
            case "down" -> intermediateOrientation = "left";
            case "left" -> intermediateOrientation = "up";
        }

    }


}
