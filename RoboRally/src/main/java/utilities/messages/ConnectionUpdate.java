package utilities.messages;

public class ConnectionUpdate extends MessageBody {
    private int playerID;
    private Boolean connected;
    private String action;

    public ConnectionUpdate(int playerID, Boolean connected, String action) {
        this.playerID = playerID;
        this.connected = connected;
        this.action = action;
    }

    public int getPlayerID() {
        return playerID;
    }

    public Boolean getConnected() {
        return connected;
    }

    public String getAction() {
        return action;
    }
}
