package server.network;

import game.Game;
import game.cards.Card;
import game.gameboard.GameBoard;
import game.player.Player;
import game.utilities.PositionLookUp;
import utilities.MessageHandler;
import utilities.messages.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {

    private final HashMap<Integer, PrintWriter> printWriterMap = new HashMap<>();
    private final HashMap<Integer, Player> playerMap = new HashMap<>();

    private final List<Player> playerList = new ArrayList<>();
    private final HashMap<Integer, Boolean> statusMap = new HashMap<>();

    private final double protocolVersion = 1.0;
    private int currentID = 972123;

    private Game game;
    private int firstReadyPlayerID;
    private String[] availableMaps = {"DizzyHighway", "ExtraCrispy"};

    private String selectedGameBoard;
    private Boolean firstPlayer = true;

    private final MessageHandler messageHandler = new MessageHandler();

    public static void main(String[] args) {

        Server server = new Server();
        server.start(9090);

    }

    public void setSelectedGameBoard(String selectedGameBoard) {
        this.selectedGameBoard = selectedGameBoard;
    }

    private void start(int portNumber) {

        System.out.println("Server is running on port: " + portNumber);

        PositionLookUp.createMaps();

        Executor pool = Executors.newCachedThreadPool();

        try (ServerSocket listener = new ServerSocket(portNumber)) {

            while (true) {

                pool.execute(new UserThread(listener.accept(), this, getNewID()));

            }

        } catch (IOException e) {

            Logger.getLogger("").info("Error in the server " + e.getMessage());

        }

    }

    public double getProtocolVersion() {
        return protocolVersion;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Game getGame() {
        return game;
    }

    public synchronized void addPlayer(int playerID, Player player) {
        playerMap.put(playerID, player);
        statusMap.put(playerID, false);
        playerList.add(player);
    }

    public synchronized void addPrintWriter(int playerID, PrintWriter playerOutgoing) {
        printWriterMap.put(playerID, playerOutgoing);
    }

    public synchronized void setStatus(int playerID, boolean status) {

        statusMap.replace(playerID, status);
        playerMap.get(playerID).setStatus(status);

        if (firstPlayer) {

            firstPlayer = false;
            firstReadyPlayerID = playerID;

            String selectedMap = messageHandler.buildMessage("SelectMap", new SelectMap(availableMaps));
            sendMessageToSingleUser(selectedMap, playerID);

        }

        if (playerID == firstReadyPlayerID && !status) {

            firstPlayer = true;
            selectedGameBoard = null;

        }

    }

    private synchronized int getNewID() {
        currentID++;
        return currentID;
    }

    public synchronized Boolean checkIfRobotIsFree(int figure) {
        for (Player player : playerMap.values()) {
            if (player.getFigure() == figure) {
                return false;
            }
        }
        return true;
    }

    public synchronized void sendMessageToAllUsers(String message) {
        for (PrintWriter outgoing : printWriterMap.values()) {
            outgoing.println(message);
        }
    }

    public synchronized void sendMessageToSingleUser(String message, int ID) {
        PrintWriter outgoing = printWriterMap.get(ID);
        outgoing.println(message);
    }

    public synchronized void notifyPlayersAboutNewPlayer(Player player) {

        String playerAdded = messageHandler.buildMessage("PlayerAdded", new PlayerAdded(player));

        for (PrintWriter outgoing : printWriterMap.values()) {

            outgoing.println(playerAdded);

        }

    }

    public synchronized void sendStatusToNewPlayer(int ID) {

        PrintWriter outgoing = printWriterMap.get(ID);

        for(Player player: playerMap.values()) {

            if (player.getPlayerID() != ID) {

                String playerAdded = messageHandler.buildMessage("PlayerAdded", new PlayerAdded(player));
                outgoing.println(playerAdded);

                String playerStatus = messageHandler.buildMessage("PlayerStatus", new PlayerStatus(player.getPlayerID(), player.getStatus()));
                outgoing.println(playerStatus);

            }

        }

        if (selectedGameBoard != null) {

            String[] selectedMap = {selectedGameBoard};

            String mapSelected = messageHandler.buildMessage("MapSelected", new MapSelected(selectedMap));
            outgoing.println(mapSelected);

        }

    }

    public synchronized void checkIfGameCanStart() {

        if (playerMap.size() >= 2) {

            boolean playerReady = true;

            for (boolean status : statusMap.values()) {

                if(!status) {
                    playerReady = false;
                }

            }

            if (playerReady && selectedGameBoard != null) {
                startGame();
            }

        }

    }

    public synchronized void startGame() {

        this.game = new Game(this, playerList, new GameBoard(selectedGameBoard));

        String gameStarted = messageHandler.buildMessage("GameStarted", new GameStarted(game.getGameBoard().toMap()));
        sendMessageToAllUsers(gameStarted);

        game.startGame();

    }

    public synchronized void handOutCards(int playerID, Card[] cards, int cardsInPile) {

        String yourCards = messageHandler.buildMessage("YourCards", new YourCards(Card.toStringArray(cards), cardsInPile));
        String notYourCards = messageHandler.buildMessage("NotYourCards", new NotYourCards(playerID, cards.length, cardsInPile));

        printWriterMap.get(playerID).println(yourCards);

        for (int key : printWriterMap.keySet()) {

            if (key != playerID) {

                printWriterMap.get(key).println(notYourCards);

            }

        }

    }

    public void endGame() {
        this.game = null;
    }

}
