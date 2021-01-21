package game.player;

import game.Robots.Robot;

public class Player {

    private final int id;
    private final String name;

    private final int figure;
    private final Robot robot;

    private Boolean status = false;

    private int priority;
    private int x;
    private int y;

    public Player(int id, String name, int figure) {

        this.id = id;
        this.name = name;
        this.figure = figure;
        this.robot = Robot.getRobotByFigure(figure);

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFigure() {
        return figure;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Robot getRobot() {
        return robot;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
