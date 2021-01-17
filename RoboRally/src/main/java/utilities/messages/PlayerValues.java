package utilities.messages;

public class PlayerValues extends MessageBody {

    private final String name;
    private final int figure;

    public PlayerValues(String name, int figure) {

        this.name = name;
        this.figure = figure;

    }

    public String getName() {
        return name;
    }

    public int getFigure() {
        return figure;
    }
}
