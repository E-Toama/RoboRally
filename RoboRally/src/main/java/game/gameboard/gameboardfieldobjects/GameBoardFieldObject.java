package game.gameboard.gameboardfieldobjects;

public abstract class GameBoardFieldObject {

    private final String type;

    protected GameBoardFieldObject(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
