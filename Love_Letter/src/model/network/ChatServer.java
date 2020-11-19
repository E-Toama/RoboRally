package model.network;

import model.game.Game;

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

    if (this.game == null) {

      this.game = new Game();

      sendMessageToSingleUser(userName, "You have successfully created a game, wait for other players to join!");

      sendMessageToAllUsers(userName + " created a game. Join him!");

    } else {

      sendMessageToSingleUser(userName, "There is already a game!");

    }

  }

  public synchronized void joinGame(String userName) {

    sendMessageToSingleUser(userName, "You have successfully joined the game. Wait until it start's!");

  }

  public synchronized void startGame(String userName) {

    if(userMap.size() >= 2) {

      sendMessageToAllUsers("The Game has started!");

    } else {

      sendMessageToSingleUser(userName, "Wait for other Players to join.");

    }


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
