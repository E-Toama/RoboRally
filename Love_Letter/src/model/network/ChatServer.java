package model.network;

import model.gameV2.Game;
import model.gameV2.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatServer {

    private final HashMap<String, PrintWriter> userMap = new HashMap<String, PrintWriter>();
    private Game game;

    public static void main(String[] args) {

        ChatServer server = new ChatServer();
        server.execute();

    }

    public synchronized HashMap<String, PrintWriter> getUserMap() {

        return this.userMap;

    }

    public synchronized void addUser(String newUserName, PrintWriter newWriter) {

        userMap.put(newUserName, newWriter);

    }

    public synchronized void sendMessageToAllUsers(String message) {

        for (PrintWriter writer : userMap.values()) {

            writer.println(message);

        }

    }

    public synchronized void sendMessageToSingleUser(String userName, String message) {

        PrintWriter writer = userMap.get(userName);

        writer.println(message);

    }

    public synchronized void creatGame(String userName) {

        if (game == null) {

            game = new Game(this, new Player(userName));

            sendMessageToSingleUser(userName, "You have successfully created a game, wait for other players to join!");

            sendMessageToAllUsers(userName + " created a game. Join him!");

        } else {

            sendMessageToSingleUser(userName, "There is already a game!");

        }

    }

    public synchronized void joinGame(String userName) {

        game.addPlayer(new Player(userName));

    }

    public synchronized void startGame(String userName) {

        if (game != null) {

            game.startGame(userName);

        } else {

            sendMessageToSingleUser(userName, "There is no Game, create one first!");

        }


    }

    public synchronized void userPLaysLeftCard(String username) {

        game.playLeftCard(username);

    }

    public synchronized void userPLaysRightCard(String username) {

        game.playRightCard(username);

    }

    public synchronized void chooseAnyPlayer(String username, String chosenName) {

        game.chooseAnyPlayer(username, chosenName);

    }

    public synchronized void chooseAnotherPlayer(String username, String chosenName) {

        game.chooseAnotherPlayer(username, chosenName);

    }

    public synchronized void guessCard(String username, String value) {

        game.guessCard(username, value);

    }

    public synchronized void getDiscardedCards(String username) {

        if (game != null) {

            sendMessageToSingleUser(username, game.getDiscardedCards());

        } else {

            sendMessageToSingleUser(username, "There is no game!");

        }

    }

    public synchronized void getStatus(String username) {

        if (game != null) {

            game.getStatus(username);

        } else {

            sendMessageToSingleUser(username, "There is no game!");

        }

    }

    public void endGame() {

        game = null;

    }

    public void execute() {

        System.out.println("Server is running on port: 9090");

        Executor pool = Executors.newFixedThreadPool(50);

        try (ServerSocket listener = new ServerSocket(9090)) {

            while (true) {

                pool.execute(new UserThread(listener.accept(), this));

            }

        } catch (IOException e) {

            System.out.println("Error in the server " + e.getMessage());

        }

    }

}
