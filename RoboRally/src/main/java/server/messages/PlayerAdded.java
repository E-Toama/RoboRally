package server.messages;

import player.Player;

public class PlayerAdded extends MessageBody {

    private final Player playerID;

    public PlayerAdded(Player player) {

        this.playerID = player;

    }

    public Player getPlayerID() {
        return playerID;
    }
}
