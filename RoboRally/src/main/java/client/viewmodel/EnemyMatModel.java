package client.viewmodel;

import client.network.ClientThread;
import client.view.EnemyMatController;
import game.Robots.Robot;
import game.player.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class EnemyMatModel {

    private ClientThread clientThread;
    private EnemyMatController enemyMatController;

    //Rudimentary attributes for Robot Movements
    private int playerID;
    private int currentPosition;
    private boolean isCurrentPlayer;
    private boolean finishedSelection;
    private String direction;
    private int figure;

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty robotName = new SimpleStringProperty();
    private StringProperty checkpointsreached = new SimpleStringProperty();
    private StringProperty energyPoints = new SimpleStringProperty();
    private StringProperty pickedUpDamageCards = new SimpleStringProperty();

    public EnemyMatModel(EnemyMatController enemyMatController) {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setEnemyMatModel(this);
        //Model <-> Controller
        this.enemyMatController = enemyMatController;

        // Initialize defaults
        this.playerID = 0;
        this.currentPosition = -1;
        this.isCurrentPlayer = false;
        this.finishedSelection = false;
        this.direction = "";
        this.figure = -1;

        this.checkpointsreached.set("0");
        this.userName.set("");
        this.robotName.set("");
        this.pickedUpDamageCards.set("0");
        this.energyPoints.set("0");
        this.robotName.set("");

    }

    //Setter for player identification values
    public void setPlayerValues(Player player) {
        this.playerID = player.getPlayerID();
        this.userName.set(player.getName());
        this.figure = player.getFigure();
        this.robotName.set(Robot.getRobotName(figure));
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
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

    //Setters for Bindings
    public void setEnergyPoints(int energyPoints) {
        int energyPointSum = energyPoints + Integer.parseInt(this.energyPoints.getValue());
        this.energyPoints.set(String.valueOf(energyPointSum));
    }

    public void setCheckpointsreached(String checkpointsreached) {
        this.checkpointsreached.set(checkpointsreached);
    }

    public void setPickedUpDamageCards(int pickedUpDamageCards) {
        int damageCardSum = pickedUpDamageCards + Integer.parseInt(this.pickedUpDamageCards.getValue());
        this.pickedUpDamageCards.set(String.valueOf(damageCardSum));
    }


    public EnemyMatController getEnemyMatController() {
        return enemyMatController;
    }

    //Getters for Bindings
    public StringProperty getUserName() {
        return userName;
    }
    public StringProperty getCheckpointsreached() {
        return checkpointsreached;
    }
    public StringProperty getEnergyPoints() {
        return energyPoints;
    }
    public StringProperty getPickedUpDamageCards() {
        return pickedUpDamageCards;
    }


    public int getFigure() {
        return this.figure;
    }
}
