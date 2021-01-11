package server.messages;

import game.gameboard.MapElement;

import java.util.ArrayList;

public class GameStarted extends MessageBody {

    private MapElement[][] map;

    public GameStarted(MapElement[][] map) {
        this.map = map;
    }

    public MapElement[][] getMap() {
        return this.map;
    }

}
