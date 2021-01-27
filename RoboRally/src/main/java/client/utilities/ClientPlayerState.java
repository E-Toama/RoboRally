package client.utilities;

public class ClientPlayerState {

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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean hasFinishedSelection() {
        return hasFinishedSelection;
    }

    public void setHasFinishedSelection(boolean hasFinishedSelection) {
        this.hasFinishedSelection = hasFinishedSelection;
    }

    public boolean isCurrentPlayer() {
        return isCurrentPlayer;
    }

    public void setCurrentPlayer(boolean currentPlayer) {
        isCurrentPlayer = currentPlayer;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public ClientPlayerState() {
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCheckpointsreached() {
        return checkpointsreached;
    }

    public void setCheckpointsreached(int checkpointsreached) {
        this.checkpointsreached = checkpointsreached;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    public void setEnergyPoints(int energyPoints) {
        this.energyPoints = energyPoints;
    }

    public int getPickedUpDamageCards() {
        return pickedUpDamageCards;
    }

    public void setPickedUpDamageCards(int pickedUpDamageCards) {
        this.pickedUpDamageCards = pickedUpDamageCards;
    }

    public int getFigure() {
        return figure;
    }

    public void setFigure(int figure) {
        this.figure = figure;
    }

    public int getDeckCount() {
        return deckCount;
    }

    public void setDeckCount(int deckCount) {
        this.deckCount = deckCount;
    }

    public int getDiscardedCount() {
        return discardedCount;
    }

    public void setDiscardedCount(int discardedCount) {
        this.discardedCount = discardedCount;
    }
}
