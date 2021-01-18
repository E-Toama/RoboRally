package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class EnergySpace extends FieldElement {
    private int count;

    public EnergySpace(int count) {
        super();
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
