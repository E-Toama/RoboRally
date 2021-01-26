package game.utilities;

import game.Robots.Robot;
import game.player.Player;
import game.player.PlayerMat;
import server.network.Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameState {

    private final Server server;

    public int activePhase;
    public List<Player> playerList;
    public List<PlayerMat> playerMatList = new ArrayList<>();
    public HashMap<Integer, PlayerMat> playerMatHashMap = new HashMap<>();

    public GameState(List<Player> playerList, Server server) {

        this.playerList = playerList;
        this.server = server;

        for (Player player : playerList) {

            PlayerMat playerMat = new PlayerMat(player, new Robot(player.getFigure()), server);
            playerMatList.add(playerMat);
            playerMatHashMap.put(player.getPlayerID(), playerMat);

        }

    }


}
