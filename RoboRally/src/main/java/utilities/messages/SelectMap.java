package utilities.messages;

public class SelectMap extends MessageBody {

    private final String[] availableMaps;

    public SelectMap(String[] availableMaps) {

        this.availableMaps = availableMaps;

    }

    public String[] getAvailableMaps() {
        return availableMaps;
    }
}
