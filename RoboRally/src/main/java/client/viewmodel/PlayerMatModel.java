package client.viewmodel;

import client.network.ClientThread;
import client.utilities.ClientPlayerState;
import client.view.PlayerMatController;
import game.Robots.Robot;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerMatModel {

    private ClientThread clientThread;
    private PlayerMatController playerMatController;
    private ClientPlayerState playerState;

    private StringProperty userName = new SimpleStringProperty();
    private StringProperty robot = new SimpleStringProperty();
    private StringProperty checkPoints = new SimpleStringProperty();
    private StringProperty cardsInDeck = new SimpleStringProperty();
    private StringProperty discardedCards = new SimpleStringProperty();
    private StringProperty damageCards = new SimpleStringProperty();
    private StringProperty energyCubes = new SimpleStringProperty();

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
        userName.set(playerState.getUserName());
        robot.set(Robot.getRobotName(playerState.getFigure()));
        checkPoints.set(String.valueOf(playerState.getCheckpointsreached()));
        cardsInDeck.set(String.valueOf(playerState.getDeckCount()));
        discardedCards.set(String.valueOf(playerState.getDiscardedCount()));
        damageCards.set(String.valueOf(playerState.getPickedUpDamageCards()));
        energyCubes.set(String.valueOf(playerState.getEnergyPoints()));
    }

    public StringProperty userNameTextProperty() {
        return userName;
    }

    public Property<String> robotTextProperty() {
        return robot;
    }

    public Property<String> checkPointTextProperty() {
        return checkPoints;
    }

    public Property<String> cardsInDeckTextProperty() {
        return cardsInDeck;
    }

    public Property<String> discardedCardsTextProperty() {
        return discardedCards;
    }

    public Property<String> damageCardsTextProperty() {
        return damageCards;
    }

    public Property<String> energyCubesTextProperty() {
        return energyCubes;
    }
}
