package server.network;

import game.Game;
import game.gameboard.GameBoardMapObject;
import game.player.Player;
import utilities.MessageHandler;
import utilities.messages.GameStarted;
import utilities.messages.PlayerAdded;
import utilities.messages.PlayerStatus;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    private final HashMap<Integer, PrintWriter> printWriterMap = new HashMap<>();
    private final HashMap<Integer, Player> playerMap = new HashMap<>();
    private final HashMap<Integer, Boolean> statusMap = new HashMap<>();

    private final double protocolVersion = 1.0;
    private int currentID = 972123;

    private Game game;

    private final MessageHandler messageHandler = new MessageHandler();

    public static void main(String[] args) {

        Server server = new Server();
        server.start(9090);

    }

    private void start(int portNumber) {

        System.out.println("Server is running on port: " + portNumber);

        Executor pool = Executors.newCachedThreadPool();

        try (ServerSocket listener = new ServerSocket(portNumber)) {

            while (true) {

                pool.execute(new UserThread(listener.accept(), this, getNewID()));

            }

        } catch (IOException e) {

            System.out.println("Error in the server " + e.getMessage());

        }

    }

    public synchronized HashMap<Integer, PrintWriter> getPrintWriterMap() {
        return printWriterMap;
    }

    public double getProtocolVersion() {
        return protocolVersion;
    }

    public synchronized void addPlayer(int playerID, Player player) {
        playerMap.put(playerID, player);
        statusMap.put(playerID, false);
    }

    public synchronized void addPrintWriter(int playerID, PrintWriter playerOutgoing) {
        printWriterMap.put(playerID, playerOutgoing);
    }

    public synchronized void setStatus(int playerID, boolean status) {
        statusMap.replace(playerID, status);
        playerMap.get(playerID).setStatus(status);
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

            if (player.getId() != ID) {

                String playerAdded = messageHandler.buildMessage("PlayerAdded", new PlayerAdded(player));
                outgoing.println(playerAdded);

                String playerStatus = messageHandler.buildMessage("PlayerStatus", new PlayerStatus(player.getId(), player.getStatus()));
                outgoing.println(playerStatus);

            }

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

            if (playerReady) {
                startGame();
            }

        }

    }

    public synchronized void startGame() {
        System.out.println("Game started");
        game = new Game();

        GameBoardMapObject[] testmap = game.getGameBoard().toMap();

        System.out.println(testmap);

        String gameStarted = messageHandler.buildMessage("GameStarted", new GameStarted(testmap));

        for (PrintWriter outgoing : printWriterMap.values()) {

            outgoing.println(gameStarted);

        }

    }

}
