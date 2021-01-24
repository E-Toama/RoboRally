package client.utilities;

public class ClientPlayerState {

    private int playerID;
    private int checkpointsreached;
    private int pickedUpDamageCards;
    private String userName;
    private int figure;
    private int deckCount;
    private int discardedCount;

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getCheckpointsreached() {
        return checkpointsreached;
    }

    public void setCheckpointsreached(int checkpointsreached) {
        this.checkpointsreached = checkpointsreached;
    }

    public int getPickedUpDamageCards() {
        return pickedUpDamageCards;
    }

    public void setPickedUpDamageCards(int pickedUpDamageCards) {
        this.pickedUpDamageCards = pickedUpDamageCards;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
