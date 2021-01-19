package game.gameboard.gameboardfieldobjects;

public class EnergySpaceFieldObject extends GameBoardFieldObject {

    private final int count;

    public EnergySpaceFieldObject(int count) {
        super("EnergySpace");
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}
