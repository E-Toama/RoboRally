package client.viewmodel;

import client.network.ClientThread;
import client.view.EnemyMatController;
import client.view.GameBoardController;

public class EnemyMatModel {

    ClientThread clientThread;
    EnemyMatController enemyMatController;

    //Rudimentary attributes for Robot Movements
    private int playerID;
    private int currentPosition;
    private boolean isCurrentPlayer;

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }

    public EnemyMatModel() {
        //Client <-> Model
        this.clientThread = ClientThread.getInstance();
        clientThread.setEnemyMatModel(this);

        //Model <-> Controller
/*        enemyMatController.setEnemyMatModel(this);

        //ToDo: If we use FXML-File for Mat, we don't need this line:
        enemyMatController = new EnemyMatController();*/
    }

    //Basic Getters and Setters


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
