package player;

public class Player {

    private final int id;
    private final String name;
    private final int figure;
    private Boolean status = false;

    public Player(int id, String name, int figure) {

        this.id = id;
        this.name = name;
        this.figure = figure;

    }

    public int getId() {
        return id;
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
