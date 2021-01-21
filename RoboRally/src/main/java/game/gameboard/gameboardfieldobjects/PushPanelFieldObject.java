package game.gameboard.gameboardfieldobjects;

public class PushPanelFieldObject extends GameBoardFieldObject{

    private final String orientation;
    private final int[] registers;

    public PushPanelFieldObject(String orientation, int[] registers) {
        super("PushPanel");
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
