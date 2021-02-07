package game.Robots;

import game.utilities.Position;
import game.utilities.PositionLookUp;

/**
 * This class represents the robot, its orientation, to which player it belongs 
 * and its position on the game board.
 * 
 * @author 
 */
public class Robot {

    private final int figure;
    private final int playerID;
    private Position position;
    private Position startingPosition;
    private String orientation = "right";

    /**
     * Constructor for initializing the robot figure and to which player it belongs.
     * 
     * @param figure
     *          the number of the robot
     * @param playerID
     *          the player id
     */
    public Robot(int figure, int playerID) {
        this.figure = figure;
        this.playerID = playerID;
    }

    /**
     * This method returns the number of the robot.
     * 
     * @return the robot figure
     */
    public int getFigure() {
        return figure;
    }

    /**
     * This method sets the robot to the passed position.
     * 
     * @param position
     *          the passed position on the game board
     */
    public void setRobotPosition(int position) {

        this.position = PositionLookUp.positionToXY.get(position);

    }

    /**
     * This method returns the starting position of the robot on the game board.
     * 
     * @return the starting position 
     */
    public Position getStartingPosition() {
        return startingPosition;
    }

    /**
     * This method sets the starting position of the robot on the game board.
     * 
     * @param startingPosition
     *              the starting position of the robot
     */
    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    /**
     * This method sets the position.
     * 
     * @param position
     *           the passed position
     */
    public void setXY(Position position) {

        this.position = position;

    }

    /**
     * This method returns the robot position.
     * 
     * @return the robot position
     */
    public Position getRobotXY() {
        return position;
    }

    /**
     * This method return the number of the field on which the robot stands.
     * 
     * @return the field number 
     */
    public int getRobotPosition() {

        return PositionLookUp.XYToPosition.get(position);

    }

    /**
     * This method returns the robot facing direction.
     * 
     * @return the robot direction
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * This method changes the direction of the robot.
     * 
     * @param orientation
     *              the direction in which the robot should be facing
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * This method returns the robot full name using the passed robot number.
     * 
     * @param figure
     *          the robot number
     *          
     * @return the robot name
     */
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

    /**
     * This method returns the robot full name using the passed robot number.
     * 
     * @param figure
     *          the robot number
     *          
     * @return the robot name
     */
    public static String getRobotName(int figure) {

        return getString(figure);

    }

    /**
     * This method turns the robot counterclockwise.
     */
    public void turnLeft() {

        switch (orientation) {
            case "up" -> orientation = "left";
            case "left" -> orientation = "down";
            case "down" -> orientation = "right";
            case "right" -> orientation = "up";
        }

    }

    /**
     * This method turns the robot clockwise.
     */
    public void turnRight() {

        switch (orientation) {
            case "up" -> orientation = "right";
            case "left" -> orientation = "up";
            case "down" -> orientation = "left";
            case "right" -> orientation = "down";
        }

    }

    /**
     * This method returns the player id.
     * 
     * @return the player id
     */
    public int getPlayerID() {
        return playerID;
    }
}
