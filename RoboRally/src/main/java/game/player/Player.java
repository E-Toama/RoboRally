package game.player;

public class Player {

    private final int playerID;
    private final String name;
    private final int figure;

    private Boolean status = false;

    public Player(int id, String name, int figure) {

        this.playerID = id;
        this.name = name;
        this.figure = figure;

    }

    public int getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }

    public int getFigure() {
        return figure;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
