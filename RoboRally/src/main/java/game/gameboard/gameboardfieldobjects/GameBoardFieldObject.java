package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.MessageHandler;

public abstract class GameBoardFieldObject {

    private final String type;
    protected final MessageHandler messageHandler = new MessageHandler();

    protected GameBoardFieldObject(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract void activate(Game game, GameState gameState, int playerID);

}
