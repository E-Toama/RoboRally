package client.viewmodel;

import client.network.ClientThread;
import client.utilities.ClientPlayerState;
import client.view.PlayerMatController;

public class PlayerMatModel {

    private ClientThread clientThread;
    private PlayerMatController playerMatController;
    private ClientPlayerState playerState;

    public PlayerMatModel() {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setPlayerMatModel(this);
        //Model <-> Controller
        playerMatController = new PlayerMatController();
        playerMatController.setPlayerMatModel(this);
    }

    public ClientPlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(ClientPlayerState state) {
        this.playerState = state;
    }

    public void updatePlayerStatus() {
        playerMatController.updateLabels();
    }


    public PlayerMatController getPlayerMatController() {
        return playerMatController;
    }
}
