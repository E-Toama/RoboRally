package game.utilities;

import game.Robots.Robot;
import game.cards.Card;
import game.cards.damagecards.Spam;
import game.cards.damagecards.TrojanHorse;
import game.cards.damagecards.Virus;
import game.cards.damagecards.Worm;
import game.gameboard.GameBoard;
import game.player.Player;
import game.player.PlayerMat;
import server.network.Server;
import utilities.MessageHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameState {

    public Server server;
    private final MessageHandler messageHandler = new MessageHandler();

    public GameBoard gameBoard;

    public int checkPointsNeededToWin;
    public int activePhase;

    public List<Player> playerList;
    public List<PlayerMat> playerMatList = new ArrayList<>();
    public HashMap<Integer, PlayerMat> playerMatHashMap = new HashMap<>();

    public List<Integer> playersFinishedSelectionList = new ArrayList<>();

    public List<PlayerMat> registerList = new ArrayList<>();
    public List<PlayerMat> nextRegisterList = new ArrayList<>();
    public int register;

    public List<Card> spamCards = new ArrayList<>();
    public List<Card> virusCards = new ArrayList<>();
    public List<Card> trojanHorseCards = new ArrayList<>();
    public List<Card> wormCards = new ArrayList<>();

    public final DrawDamageCardHandler drawDamageCardHandler = new DrawDamageCardHandler(this);

    public GameState(List<Player> playerList, Server server, GameBoard gameBoard) {

        this.server = server;

        this.gameBoard = gameBoard;
        this.playerList = playerList;

        for (Player player : playerList) {

            PlayerMat playerMat = new PlayerMat(player, new Robot(player.getFigure(), player.getPlayerID()), server);
            playerMatList.add(playerMat);
            playerMatHashMap.put(player.getPlayerID(), playerMat);

        }

    }

    public int[] getNotFinishedPlayer() {

        int[] returnValue = new int[playerList.size() - playersFinishedSelectionList.size()];

        int i = 0;

        for (Player player : playerList) {

            if(!playersFinishedSelectionList.contains(player.getPlayerID())) {

                returnValue[i] = player.getPlayerID();
                i++;

            }

        }

        return returnValue;

    }

    public void initialize() {

        for (PlayerMat playerMat : playerMatList) {

            playerMat.initializeDeck();

        }

        for (int i = 0; i < 38; i++) {

            spamCards.add(new Spam());

        }

        for (int i = 0; i < 18; i++) {

            virusCards.add(new Virus());

        }

        for (int i = 0; i < 12; i++) {

            trojanHorseCards.add(new TrojanHorse());

        }

        for (int i = 0; i < 6; i++) {

            wormCards.add(new Worm());

        }

    }

}
