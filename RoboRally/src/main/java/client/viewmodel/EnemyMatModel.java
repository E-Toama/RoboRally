package client.viewmodel;

import client.network.ClientThread;
import client.view.EnemyMatController;
import client.view.GameBoardController;

public class EnemyMatModel {

    ClientThread clientThread;
    EnemyMatController enemyMatController;

    public EnemyMatModel() {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setEnemyMatModel(this);

        //Model <-> Controller
        enemyMatController = new EnemyMatController();
        enemyMatController.setEnemyMatModel(this);
    }

}
