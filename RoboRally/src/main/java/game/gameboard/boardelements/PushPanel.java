package game.gameboard.boardelements;

import game.gameboard.FieldElement;

public class PushPanel extends BoardElement  implements FieldElement {
    private String orientation;
    private int[] registers;

    public PushPanel(String orientation, int[] registers) {
        super();
        this.orientation = orientation;
        this.registers = registers;
    }

    public String getOrientation() {
        return orientation;
    }

    public int[] getRegisters() {
        return registers;
    }
}
