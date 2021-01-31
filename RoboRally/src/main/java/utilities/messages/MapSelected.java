package utilities.messages;

public class MapSelected extends MessageBody {
    String selectedMap;

    public MapSelected(String selectedMap) {
        this.selectedMap = selectedMap;
    }

    public String getSelectedMap() {
        return selectedMap;
    }
}
