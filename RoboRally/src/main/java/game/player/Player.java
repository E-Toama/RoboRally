package game.player;

public class Player {

    private final int playerID;
    private final String name;
    private final int figure;

    private Boolean status = false;

    /**
     * Class Player contains relevant player information of each client
     *
     * @param id is the playerID of a player
     * @param name is the username of the player
     * @param figure is the number of the chosen robot figure
     *
     * @author
     */
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
