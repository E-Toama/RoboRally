package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class Laser extends BoardElement  implements FieldElement {
    private String orientation;
    private int count;

    public Laser(String orientation, int count) {
        super();
        this.orientation = orientation;
        this.count = count;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getCount() {
        return count;
    }
}
