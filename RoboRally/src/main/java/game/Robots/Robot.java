package game.Robots;

import game.utilities.Position;
import game.utilities.PositionLookUp;

public class Robot {

    private final int figure;
    private int x;
    private int y;
    private String orientation = "up";

    public Robot(int figure) {
        this.figure = figure;
    }

    public int getFigure() {
        return figure;
    }

    public void setRobotPosition(int position) {

        Position xy = PositionLookUp.positionToXY.get(position);
        this.x = xy.getX();
        this.y = xy.getY();

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

}
