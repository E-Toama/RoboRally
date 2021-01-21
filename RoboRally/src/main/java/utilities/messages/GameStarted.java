package utilities.messages;

import game.gameboard.GameBoardMapObject;

public class GameStarted extends MessageBody {

    private final GameBoardMapObject[] map;

    public GameStarted(GameBoardMapObject[] map) {

        this.map = map;

    }

    public GameBoardMapObject[] getMap() {
        return map;
    }
}
