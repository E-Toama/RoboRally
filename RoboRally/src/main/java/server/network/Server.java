package server.network;

import player.Player;
import utilities.MessageHandler;
import utilities.messages.PlayerAdded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    private final HashMap<Integer, PrintWriter> playerMap = new HashMap<>();
    private final List<Player> playerList = new LinkedList<Player>();
    private final double protocolVersion = 0.1;
    private int currentID = 972123;
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

    public synchronized HashMap<Integer, PrintWriter> getPlayerMap() {
        return playerMap;
    }

    public double getProtocolVersion() {
        return protocolVersion;
    }

    public synchronized void addPlayer(int playerID, PrintWriter playerOutgoing, Player player) {
        playerMap.put(playerID, playerOutgoing);
        playerList.add(player);
    }

    private synchronized int getNewID() {
        currentID++;
        return currentID;
    }

    public synchronized Boolean checkIfRobotIsFree(int figure) {

        for (Player player : playerList) {

            if (player.getFigure() == figure) {

                return false;

            }

        }

        return true;

    }

    public synchronized void sendMessageToAllUsers(String message) {

        for (PrintWriter outgoing : playerMap.values()) {

            outgoing.println(message);

        }

    }

    public synchronized void sendMessageToSingleUser(String message, int ID) {

        PrintWriter outgoing = playerMap.get(ID);

        outgoing.println(message);

    }

    public synchronized void notifyPlayersAboutNewPlayer(Player player) {

        String playerAdded = messageHandler.buildMessage("PlayerAdded", new PlayerAdded(player));

        for (PrintWriter outgoing : playerMap.values()) {

            outgoing.println(playerAdded);

        }

    }

    public synchronized void sendStatusToNewPlayer(int ID) {

        PrintWriter outgoing = playerMap.get(ID);

        for(Player player: playerList) {

            if (player.getId() != ID) {

                String playerAdded = messageHandler.buildMessage("PlayerAdded", new PlayerAdded(player));

                outgoing.println(playerAdded);

            }

        }

    }

}
