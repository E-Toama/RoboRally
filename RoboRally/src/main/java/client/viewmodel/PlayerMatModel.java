package client.viewmodel;

import client.network.ClientThread;
import client.utilities.ClientPlayerState;
import client.view.PlayerMatController;

public class PlayerMatModel {

    private ClientThread clientThread;
    private PlayerMatController playerMatController;
    private ClientPlayerState playerState;

    public PlayerMatModel(PlayerMatController playerMatController) {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setPlayerMatModel(this);
        //Model <-> Controller
        this.playerMatController = playerMatController;
    }

    public ClientPlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(ClientPlayerState state) {
        this.playerState = state;
    }

    public PlayerMatController getPlayerMatController() {
        return playerMatController;
    }
}
