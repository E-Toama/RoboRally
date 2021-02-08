package client.viewmodel;

import client.network.ClientThread;
import client.view.EnemyMatController;
import game.robots.Robot;
import game.player.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel class for the enemy player mats
 * @author
 */
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

    /**
     * Constructor for the EnemyMatModel
     * Initializes default values for the player identification  and roboter values on the enemy playermat
     * @param enemyMatController is the controller class of the enemy playdrmats
     */
    public EnemyMatModel(EnemyMatController enemyMatController) {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setEnemyMatModel(this);
        //Model <-> Controller
        this.enemyMatController = enemyMatController;

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

    /**
     *
     *Setter for player identification values
     */
    public void setPlayerValues(Player player) {
        this.playerID = player.getPlayerID();
        this.userName.set(player.getName());
        this.figure = player.getFigure();
        this.robotName.set(Robot.getRobotName(figure));
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    /**
     * Setter for the current player
     * @param currentPlayer is true if its the players turn
     */
    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }


    /**
     * gets the playerID of a player
     * @return playerID of a player
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * sets the playerID  of a player
     * @param playerID is going to be the playerID of a player
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     * gets the current players position
     * @return currentPosition
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * sets the current position
     * @param currentPosition
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }


    /**
     * sets the energy point count of the player mat
     * @param energyPoints
     */
    public void setEnergyPoints(int energyPoints) {
        int energyPointSum = energyPoints + Integer.parseInt(this.energyPoints.getValue());
        this.energyPoints.set(String.valueOf(energyPointSum));
    }

    /**
     * sets the checkpoint count of the player mat
     * @param checkpointsreached
     */
    public void setCheckpointsreached(String checkpointsreached) {
        this.checkpointsreached.set(checkpointsreached);
    }

    /**
     * sets the picked damage card count of the player mat
     * @param pickedUpDamageCards
     */
    public void setPickedUpDamageCards(int pickedUpDamageCards) {
        int damageCardSum = pickedUpDamageCards + Integer.parseInt(this.pickedUpDamageCards.getValue());
        this.pickedUpDamageCards.set(String.valueOf(damageCardSum));
    }


    /**
     * gets the controller class of the enemy mat
     * @return
     */
    public EnemyMatController getEnemyMatController() {
        return enemyMatController;
    }


    /**
     *
     * @return StringProperty userName for bindings
     */
    public StringProperty getUserName() {
        return userName;
    }

    /**
     *
     * @return StringProperty checkpointsreached for bindings
     */
    public StringProperty getCheckpointsreached() {
        return checkpointsreached;
    }

    /**

     * @return StringProperty energyPoints for bindings
     */
    public StringProperty getEnergyPoints() {
        return energyPoints;
    }

    /**
     * @return StringProperty pickedDamageCards for bindings
     */
    public StringProperty getPickedUpDamageCards() {
        return pickedUpDamageCards;
    }

    /**
     * gets the robot figure number
     * @return int figure
     */
    public int getFigure() {
        return this.figure;
    }
}
