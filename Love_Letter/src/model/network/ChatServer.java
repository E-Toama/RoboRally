package model.network;

import model.gameV2.Game;
import model.gameV2.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Holds a pool of UserThreads, provides methods for broadcasting to single user or all users
 * Holds the game-object, provides methods for interacting with the game. (Start, Join, etc.)
 * @author Josef, Dennis, Yashar
 */
public class ChatServer {

    private final HashMap<String, PrintWriter> userMap = new HashMap<String, PrintWriter>();
    private Game game;

    /**
     * Creates and runs the Server
     * @param args Command-line-args (not in use)
     */
    public static void main(String[] args) {

        ChatServer server = new ChatServer();
        server.execute();

    }

    /**
     * Access the HashMap that stores pairs of usernames and their corresponding PrintWriters
     * @return The map of username/Printwriter-pairs
     */
    public synchronized HashMap<String, PrintWriter> getUserMap() {

        return this.userMap;

    }

    /**
     * Add a new user to the userMap.
     * @param newUserName The name of the new user to add.
     * @param newWriter The PrintWriter of the user for broadcasting messages.
     */
    public synchronized void addUser(String newUserName, PrintWriter newWriter) {

        userMap.put(newUserName, newWriter);

    }

    /**
     * Sends a message (passed as String) to all connected Clients
     * @param message String to send to all connected Clients.
     */
    public synchronized void sendMessageToAllUsers(String message) {

        for (PrintWriter writer : userMap.values()) {

            writer.println(message);

        }

    }

    /**
     * Sends a private message to a single user with specified username
     * @param userName The addressee of the message
     * @param message The content of the message
     */
    public synchronized void sendMessageToSingleUser(String userName, String message) {

        PrintWriter writer = userMap.get(userName);

        writer.println(message);

    }

    /**
     * creates a new game-object if it has not been created yet and auto-joins the first user
     * @param userName the creator of the game
     */
    public synchronized void creatGame(String userName) {

        if (game == null) {
            sendMessageToSingleUser(userName, "You have successfully created a game, wait for other players to join!");
            game = new Game(this, new Player(userName));
            sendMessageToAllUsers(userName + " created a game. Join him!");

        } else {

            sendMessageToSingleUser(userName, "There is already a game!");

        }

    }

    /**
     * Tries to add the user to the game, if she has not joined yet and the game is not running.
     * @param userName to check if already joined. Passed to the game as displayed playername.
     */
    public synchronized void joinGame(String userName) {
        if (game.getActivePlayerByUsername(userName) == null && !game.isRunning()) {
            game.addPlayer(new Player(userName));
        } else {
            sendMessageToSingleUser(userName, "You have already joined the game, just wait and relax.");
        }
    }

    /**
     * Starts a new game.
     * @param userName checked against creator of the game (i.e. the only one allowed to start it)
     */
    public synchronized void startGame(String userName) {

        if (game != null) {
            //Allows only the user who created the game to start it
            game.startGame(userName);

        } else {

            sendMessageToSingleUser(userName, "There is no Game, create one first!");

        }


    }

    /**
     * Passes the chosen game command of the user to the game
     * @param username The user who is currently playing
     */
    public synchronized void userPLaysLeftCard(String username) {

        game.playLeftCard(username);

    }

    /**
     * Passes the chosen game command of the user to the game
     * @param username The user who is currently playing
     */
    public synchronized void userPLaysRightCard(String username) {

        game.playRightCard(username);

    }

    /**
     * Method passes user-choice of target player to game.
     * Choose any player for card effect (including self, e.g. for Prince)
     * @param username the player currently active
     * @param chosenName the chosen opponent
     */
    public synchronized void chooseAnyPlayer(String username, String chosenName) {

        game.chooseAnyPlayer(username, chosenName);

    }

    /**
     * Method passes user-choice of target player to the game.
     * Choose any player excluding self (e.g. for King, Priest, ...)
     * @param username the player currently active
     * @param chosenName the chosen opponent
     */
    public synchronized void chooseAnotherPlayer(String username, String chosenName) {

        game.chooseAnotherPlayer(username, chosenName);

    }

    /**
     * Passes the user's guess to the game (in case of Guard-Card-Action)
     * @param username The user who guessed a card value
     * @param value The Card value (2-8)
     */
    public synchronized void guessCard(String username, String value) {

        game.guessCard(username, value);

    }

    /**
     * Displays the already discarded cards to the user on command "?DISCARDED"
     * @param username who requested to see the discarded cards.
     */
    public synchronized void getDiscardedCards(String username) {

        if (game != null) {

            sendMessageToSingleUser(username, game.getDiscardedCards());

        } else {

            sendMessageToSingleUser(username, "There is no game!");

        }

    }

    /**
     * Displays the current score to the player who requested "?STATS"
     * @param username user to send the current score to.
     */
    public synchronized void getStatus(String username) {

        if (game != null) {

            game.getStatus(username);

        } else {

            sendMessageToSingleUser(username, "There is no game!");

        }

    }

    /**
     * Ends the game by setting the game object to null
     */
    public void endGame() {

        game = null;

    }

    /**
     * Basic server loop; hard-coded port for convenience;
     * Uses cached ThreadPool to handle UserThreads
     */
    public void execute() {

        System.out.println("Server is running on port: 9090");

        Executor pool = Executors.newCachedThreadPool();

        try (ServerSocket listener = new ServerSocket(9090)) {

            while (true) {

                pool.execute(new UserThread(listener.accept(), this));

            }

        } catch (IOException e) {

            System.out.println("Error in the server " + e.getMessage());

        }

    }

}
