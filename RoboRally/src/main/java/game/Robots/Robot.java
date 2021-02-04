package game.Robots;

import game.utilities.Position;
import game.utilities.PositionLookUp;

public class Robot {

    private final int figure;
    private final int playerID;
    private Position position;
    private Position startingPosition;
    private String orientation = "right";

    public Robot(int figure, int playerID) {
        this.figure = figure;
        this.playerID = playerID;
    }

    public int getFigure() {
        return figure;
    }

    public void setRobotPosition(int position) {

        this.position = PositionLookUp.positionToXY.get(position);

    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    public void setXY(Position position) {

        this.position = position;

    }

    public Position getRobotXY() {
        return position;
    }

    public int getRobotPosition() {

        return PositionLookUp.XYToPosition.get(position);

    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    private static String getString(int figure) {

        return switch (figure) {
            case 0 -> "Hammer Bot";
            case 1 -> "Hulk x90";
            case 2 -> "Smash Bot";
            case 3 -> "Spin Bot";
            case 4 -> "Twonky";
            case 5 -> "Zoom Bot";
            default -> "";
        };

    }

    public String getRobotName() {

        return getString(figure);

    }

    public static String getRobotName(int figure) {

        return getString(figure);

    }

    public void turnLeft() {

        switch (orientation) {
            case "up" -> orientation = "left";
            case "left" -> orientation = "down";
            case "down" -> orientation = "right";
            case "right" -> orientation = "up";
        }

    }

    public void turnRight() {

        switch (orientation) {
            case "up" -> orientation = "right";
            case "left" -> orientation = "up";
            case "down" -> orientation = "left";
            case "right" -> orientation = "down";
        }

    }

    public int getPlayerID() {
        return playerID;
    }
}
