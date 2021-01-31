package client.utilities;

import client.viewmodel.PlayerMatModel;
import game.player.Player;

public class ClientPlayerState {

    private PlayerMatModel playerMatModel;

    private int playerID;
    private String userName;
    private int checkpointsreached;
    private int energyPoints;
    private int pickedUpDamageCards;
    private int figure;
    private int deckCount;
    private int discardedCount;
    private int currentPosition;
    private boolean isCurrentPlayer;
    private boolean hasFinishedSelection;
    private String direction;
    
    public ClientPlayerState() {
        // Initialize defaults
       playerID = 0;
       userName = "";
       checkpointsreached = 0;
       energyPoints = 0;
       pickedUpDamageCards = 0;
       figure = 0;
       deckCount = 0;
       discardedCount = 0;
       currentPosition = -1;
       isCurrentPlayer = false;
       hasFinishedSelection = false;
       direction = "";
    }

    public void setPlayerValues(Player player) {
        this.userName = player.getName();
        this.figure = player.getFigure();
        this.playerID = player.getId();
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setCheckpointsreached(int checkpointsreached) {
        this.checkpointsreached = checkpointsreached;
    }
    public void setEnergyPoints(int energyPoints) {
        this.energyPoints = energyPoints;
    }
    public void setPickedUpDamageCards(int pickedUpDamageCards) {
        this.pickedUpDamageCards = pickedUpDamageCards;
    }
    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }
    public void setDiscardedCount(int discardedCount) {
        this.discardedCount = discardedCount;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }
    public void setHasFinishedSelection(boolean hasFinishedSelection) {
        this.hasFinishedSelection = hasFinishedSelection;
    }
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setFigure(int figure) {
        this.figure = figure;
    }


    //All the getters
    public String getDirection() {
        return direction;
    }
    public boolean hasFinishedSelection() {
        return hasFinishedSelection;
    }
    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
    public int getPlayerID() {
        return playerID;
    }
    public String getUserName() {
        return userName;
    }
    public int getCheckpointsreached() {
        return checkpointsreached;
    }
    public int getEnergyPoints() {
        return energyPoints;
    }
    public int getPickedUpDamageCards() {
        return pickedUpDamageCards;
    }
    public int getFigure() {
        return figure;
    }
    public int getDeckCount() {
        return deckCount;
    }
    public int getDiscardedCount() {
        return discardedCount;
    }


    //Todo For testing reasons, CAN BE DELETED AFTERWARDS
    public void printAllThatStuff(){
        System.out.println(playerID);
        System.out.println(userName);
        System.out.println(checkpointsreached);
        System.out.println(energyPoints);
        System.out.println(pickedUpDamageCards);
        System.out.println(figure);
        System.out.println(deckCount);
        System.out.println(discardedCount);
        System.out.println(currentPosition);
        System.out.println(isCurrentPlayer);
        System.out.println(hasFinishedSelection);
        System.out.println(direction);
    }


}
