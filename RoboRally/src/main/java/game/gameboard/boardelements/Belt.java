package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class Belt extends BoardElement implements FieldElement {
    private String orientation;
    private int speed;

    public Belt(String orientation, int speed) {
        super();
        this.orientation = orientation;
        this.speed = speed;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getSpeed() {
        return speed;
    }
}
