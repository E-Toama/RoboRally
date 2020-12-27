package server.messages;

public class PlayerStatus extends MessageBody {

    private final double playerID;
    private final Boolean ready;

    public PlayerStatus(double playerID, Boolean ready) {

        this.playerID = playerID;
        this.ready = ready;

    }

    public double getPlayerID() {
        return playerID;
    }

    public Boolean getReady() {
        return ready;
    }
}
