package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class Wall extends BoardElement implements FieldElement {
    private String[] orientations;

    public Wall(String[] orientations) {
        super();
        this.orientations = orientations;
    }

    public String[] getOrientations() {
        return orientations;
    }
}
