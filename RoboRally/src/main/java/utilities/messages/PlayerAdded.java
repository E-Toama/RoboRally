package utilities.messages;

import game.player.Player;

public class PlayerAdded extends MessageBody {

    private final Player player;

    public PlayerAdded(Player player) {

        this.player = player;

    }

    public Player getPlayer() {
        return player;
    }
}
