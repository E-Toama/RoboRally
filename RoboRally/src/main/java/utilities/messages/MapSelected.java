package utilities.messages;

public class MapSelected extends MessageBody {

    private final String[] map;

    public MapSelected(String[] map) {

        this.map = map;

    }

    public String[] getMap() {
        return map;
    }

}
