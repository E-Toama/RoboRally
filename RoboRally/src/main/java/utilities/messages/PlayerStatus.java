package utilities.messages;

public class PlayerStatus extends MessageBody {

    private final int playerID;
    private final Boolean ready;

    public PlayerStatus(int playerID, Boolean ready) {

        this.playerID = playerID;
        this.ready = ready;

    }

    public int getPlayerID() {
        return playerID;
    }

    public Boolean getReady() {
        return ready;
    }
}
