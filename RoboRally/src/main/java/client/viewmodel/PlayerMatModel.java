package client.viewmodel;

import client.network.ClientThread;
import client.utilities.ClientPlayerState;
import client.view.PlayerMatController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerMatModel {

    private ClientThread clientThread;
    private PlayerMatController playerMatController;
    private ClientPlayerState playerState;

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty robotName = new SimpleStringProperty();
    private StringProperty checkpointsreached = new SimpleStringProperty();
    private StringProperty energyPoints = new SimpleStringProperty();
    private StringProperty pickedUpDamageCards = new SimpleStringProperty();
    private StringProperty deckCount = new SimpleStringProperty();
    private StringProperty discardedCount = new SimpleStringProperty();

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

    public PlayerMatModel(PlayerMatController playerMatController) {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setPlayerMatModel(this);
        //Model <-> Controller
        this.playerMatController = playerMatController;

        //Default initialization of PlayerState - will be overwritten with setter in ClientThread (handlePlayerAdded)
        playerState = new ClientPlayerState();

        //Setting all values to the Getter-Values of PlayerState
        userName.setValue(playerState.getUserName());
        checkpointsreached.setValue(String.valueOf(playerState.getCheckpointsreached()));
        energyPoints.setValue(String.valueOf(playerState.getEnergyPoints()));
        pickedUpDamageCards.setValue(String.valueOf(playerState.getPickedUpDamageCards()));
        deckCount.setValue(String.valueOf(playerState.getDeckCount()));
        discardedCount.setValue(String.valueOf(playerState.getDiscardedCount()));
        
    }

    public void setEnergyPoints(int energyPoints) {
        int energyPointSum = energyPoints + Integer.parseInt(this.energyPoints.getValue());
        this.energyPoints.set(String.valueOf(energyPointSum));
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
