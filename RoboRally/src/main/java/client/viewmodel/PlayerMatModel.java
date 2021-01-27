package client.viewmodel;

import client.network.ClientThread;
import client.view.PlayerMatController;

public class PlayerMatModel {


    private ClientThread clientThread;
    private PlayerMatController playerMatController;
    private int playerID;

    public PlayerMatModel() {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setPlayerMatModel(this);
        //Model <-> Controller
        playerMatController = new PlayerMatController();
        playerMatController.setPlayerMatModel(this);

    }

}
