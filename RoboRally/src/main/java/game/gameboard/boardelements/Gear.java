package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class Gear extends BoardElement implements FieldElement {
    private String orientation;

    public Gear(String orientation) {
        super();
        this.orientation = orientation;
    }

    public String getOrientation() {
        return orientation;
    }
}
