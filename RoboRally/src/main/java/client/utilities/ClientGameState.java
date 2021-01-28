package client.utilities;

import game.gameboard.GameBoard;
import game.player.Player;

import java.util.LinkedList;
import java.util.List;

public class ClientGameState {

    private int ActivePhase = 0;
    private GameBoard gameBoard;
    private List<ClientPlayerState> playerStateList = new LinkedList<>();

    public int getActivePhase() {
        return ActivePhase;
    }

    public void setActivePhase(int activePhase) {
        ActivePhase = activePhase;
    }
}
