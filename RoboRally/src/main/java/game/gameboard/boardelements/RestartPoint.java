package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class RestartPoint extends BoardElement  implements FieldElement {

    private String orientation;
    public RestartPoint(String orientation) {
        super();
        this.orientation = orientation;
    }
}
