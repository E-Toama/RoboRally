package game.Robots;

import game.utilities.Position;
import game.utilities.PositionLookUp;

public class Robot {

    private final int figure;
    private final int playerID;
    private int x;
    private int y;
    private String orientation = "up";

    public Robot(int figure, int playerID) {
        this.figure = figure;
        this.playerID = playerID;
    }

    public int getFigure() {
        return figure;
    }

    public void setRobotPosition(int position) {

        Position xy = PositionLookUp.positionToXY.get(position);
        this.x = xy.getX();
        this.y = xy.getY();

    }

    public void setXY(Position position) {

        this.x = position.getX();
        this.y = position.getY();

    }

    public Position getRobotXY() {
        return new Position(x, y);
    }

    public int getRobotPosition() {

        Position xy = new Position(x, y);
        return PositionLookUp.XYToPosition.get(xy);

    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    private static String getString(int figure) {
        switch (figure) {

            case 0:
                return "Hammer Bot";

            case 1:
                return "Hulk x90";

            case 2:
                return "Smash Bot";

            case 3:
                return "Spin Bot";

            case 4:
                return "Twonky";

            case 5:
                return "Zoom Bot";

            default:
                return "";

        }
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
