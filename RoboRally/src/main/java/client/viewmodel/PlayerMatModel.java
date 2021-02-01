package client.viewmodel;

import client.network.ClientThread;
import client.view.PlayerMatController;
import game.Robots.Robot;
import game.player.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerMatModel {

    private ClientThread clientThread;
    private PlayerMatController playerMatController;


    private int playerID;
    private int figure;
    private int currentPosition;
    private boolean isCurrentPlayer;
    private boolean finishedSelection;
    private String direction;


    private StringProperty userName = new SimpleStringProperty();
    private StringProperty robotName = new SimpleStringProperty();
    private StringProperty checkpointsreached = new SimpleStringProperty();
    private StringProperty energyPoints = new SimpleStringProperty();
    private StringProperty pickedUpDamageCards = new SimpleStringProperty();
    private StringProperty deckCount = new SimpleStringProperty();
    private StringProperty discardedCount = new SimpleStringProperty();


    //Constructor
    public PlayerMatModel(PlayerMatController playerMatController) {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setPlayerMatModel(this);
        //Model <-> Controller
        this.playerMatController = playerMatController;

        // Initialize defaults
        this.playerID = 0;
        this.figure = 0;
        this.currentPosition = -1;
        this.isCurrentPlayer = false;
        this.finishedSelection = false;
        this.direction = "";

/*        //Setting all values to the Getter-Values of PlayerState
        userName.setValue(playerState.getUserName());
        checkpointsreached.setValue(String.valueOf(playerState.getCheckpointsreached()));
        energyPoints.setValue(String.valueOf(playerState.getEnergyPoints()));
        pickedUpDamageCards.setValue(String.valueOf(playerState.getPickedUpDamageCards()));
        deckCount.setValue(String.valueOf(playerState.getDeckCount()));
        discardedCount.setValue(String.valueOf(playerState.getDiscardedCount()));*/

    }

    //Setter for player identification values
    public void setPlayerValues(Player player) {
        this.playerID = player.getPlayerID();
        this.userName.set(player.getName());
        this.figure = player.getFigure();
        this.robotName.set(Robot.getRobotName(figure));
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

    public void setDeckCount(int deckCount) {
        this.deckCount.set(String.valueOf(deckCount));
    }

    public void setDiscardedCount(String discardedCount) {
        this.discardedCount.set(discardedCount);
    }


    //Getters for Bindings
    public StringProperty getUserName() {
        return userName;
    }
    public StringProperty getRobotName() {return robotName;
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

    public StringProperty getDeckCount() {
        return deckCount;
    }
    public StringProperty getDiscardedCount() {
        return discardedCount;
    }


    //Traditional getters
    public PlayerMatController getPlayerMatController() {
        return playerMatController;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getFigure() {
        return figure;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public boolean isFinishedSelection() {
        return finishedSelection;
    }

    public String getDirection() {
        return direction;
    }

    //Traditional setters

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }

    public void setFinishedSelection(boolean finishedSelection) {
        this.finishedSelection = finishedSelection;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
