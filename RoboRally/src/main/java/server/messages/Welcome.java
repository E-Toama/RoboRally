package server.messages;

public class Welcome extends MessageBody {

    private final int playerId;

    public Welcome(int id) {

        this.playerId = id;

    }

    public int getPlayerId() {
        return playerId;
    }
}
