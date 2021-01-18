package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class RotatingBelt extends FieldElement {
    private int speed;
    private boolean isCrossing;
    private String[] orientations;

    public RotatingBelt(int speed, boolean isCrossing, String[] orientations) {
        super();
        this.speed = speed;
        this.isCrossing = isCrossing;
        this.orientations = orientations;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isCrossing() {
        return isCrossing;
    }

    public String[] getOrientations() {
        return orientations;
    }
}
