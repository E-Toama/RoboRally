package server.messages;

public class Welcome extends MessageBody {

    private final int playerID;

    public Welcome(int id) {

        this.playerID = id;

    }

    public int getPlayerID() {
        return playerID;
    }
}
