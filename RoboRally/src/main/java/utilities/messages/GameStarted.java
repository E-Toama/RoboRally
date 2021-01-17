package utilities.messages;

import game.gameboardV2.GameBoardMapObject;

public class GameStarted extends MessageBody {

    private final GameBoardMapObject[] map;

    public GameStarted(GameBoardMapObject[] map) {

        this.map = map;

    }

    public GameBoardMapObject[] getMap() {
        return map;
    }
}
