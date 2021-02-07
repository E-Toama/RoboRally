package client.viewmodel;

import client.network.ClientThread;
import client.view.PlayerMatController;
import game.Robots.Robot;
import game.player.Player;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel class of the player mats
 * @author
 */
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


    /**
     * constructor of PlayerMatModel with client thread and playerMatController
     * initializes default values of the player mat
     * @param playerMatController
     */
    public PlayerMatModel(PlayerMatController playerMatController) {
        //Client <-> Model
        clientThread = ClientThread.getInstance();
        clientThread.setPlayerMatModel(this);
        //Model <-> Controller
        this.playerMatController = playerMatController;


        this.playerID = 0;
        this.figure = 0;
        this.currentPosition = -1;
        this.isCurrentPlayer = false;
        this.finishedSelection = false;
        this.direction = "";

        this.checkpointsreached.set("0");
        this.userName.set("");
        this.robotName.set("");
        this.pickedUpDamageCards.set("0");
        this.energyPoints.set("0");
        this.deckCount.set("20");
        this.robotName.set("");
        this.discardedCount.set("0");

    }

    /**
     * sets player identification values
     * @param player whose values are set
     */
    public void setPlayerValues(Player player) {
        this.playerID = player.getPlayerID();
        this.userName.set(player.getName());
        this.figure = player.getFigure();
        this.robotName.set(Robot.getRobotName(figure));
    }


    /**
     * sets energyPoints counter
     * @param energyPoints is the amount of obtained energy Points
     */
    public void setEnergyPoints(int energyPoints) {
        int energyPointSum = energyPoints + Integer.parseInt(this.energyPoints.getValue());
        this.energyPoints.set(String.valueOf(energyPointSum));
    }

    /**
     * sets checkpoint counter
     * @param checkpointsreached is the amount of reached checkpoints
     */
    public void setCheckpointsreached(String checkpointsreached) {
        this.checkpointsreached.set(checkpointsreached);
    }

    /**
     * sets the damage card counter
     * @param pickedUpDamageCards is the amount of picked damage cards
     */
    public void setPickedUpDamageCards(int pickedUpDamageCards) {
        int damageCardSum = pickedUpDamageCards + Integer.parseInt(this.pickedUpDamageCards.getValue());
        this.pickedUpDamageCards.set(String.valueOf(damageCardSum));
    }

    /**
     * sets the deck count
     * @param deckCount is the amount of cards in a deck
     */
    public void setDeckCount(int deckCount) {
        this.deckCount.set(String.valueOf(deckCount));
    }

    /**
     * sets the discarded cards count
     * @param discardedCount is the amount of discarded cards
     */
    public void setDiscardedCount(String discardedCount) {
        this.discardedCount.set(discardedCount);
    }

    public void updateDiscardedCount() {
        int discarded = Integer.parseInt(this.discardedCount.getValue()) + 9;
        this.discardedCount.set(String.valueOf(discarded));
    }


    /**
     *
     * @return StringProperty userName
     */
    public StringProperty getUserName() {
        return userName;
    }

    /**
     *
     * @return StringProperty robotName
     */
    public StringProperty getRobotName() {return robotName;
    }

    /**
     *
     * @return StringProperty checkpointreached
     */
    public StringProperty getCheckpointsreached() {
        return checkpointsreached;
    }

    /**
     *
     * @return StringProperty energyPoints
     */
    public StringProperty getEnergyPoints() {
        return energyPoints;
    }

    /**
     *
     * @return StringProperty pickedUpDamageCards
     */
    public StringProperty getPickedUpDamageCards() {
        return pickedUpDamageCards;
    }

    /**
     *
     * @return StringProperty deckCount
     */
    public StringProperty getDeckCount() {
        return deckCount;
    }

    /**
     *
     * @return StringProperty discardedCount
     */
    public StringProperty getDiscardedCount() {
        return discardedCount;
    }


    /**
     * gets playerMatController
     * @return PlayerMatController playerMatController
     */
    public PlayerMatController getPlayerMatController() {
        return playerMatController;
    }

    /**
     * gets the playerID of a player
     * @return int playerID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * gets the robot figure number of a player
     * @return int figure
     */
    public int getFigure() {
        return figure;
    }

    /**
     * gets the current position of a player
     * @return int currentPosition
     */
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

    /**
     * sets the current position of a player
     * @param currentPosition is the current position
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * sets the current player
     * @param currentPlayer
     */
    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }

    /**
     * sets if the selection of the cards is finished
     * @param finishedSelection
     */
    public void setFinishedSelection(boolean finishedSelection) {
        this.finishedSelection = finishedSelection;
    }

    /**
     * sets the direction of a robot
     * @param direction is either clockwise or counterclockwise
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * sends playIt in clientThread
     */
    public void sendPlayIt() {
        clientThread.sendPlayIt();
    }
}
