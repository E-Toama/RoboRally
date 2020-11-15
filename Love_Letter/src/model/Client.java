package model;

import exceptions.DuplicateNameException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

  private Socket socket;
  private String userName;
  private BufferedReader serverToClient;
  private PrintWriter clientToServer;
  public ObservableList<String> chatMessages;

  public Client(String userName) throws IOException, DuplicateNameException {

    socket = new Socket("localhost", 9090);

    // BufferedReader zum Lesen der Daten vom Server
    serverToClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    // PrintWriter zum Schreiben von Daten an den Server
    clientToServer = new PrintWriter(socket.getOutputStream(), true);

    //Nutzernamensduplikate abfragen
    clientToServer.println(userName);
    String response = serverToClient.readLine(); //response ist entweder "taken" oder "OK"
    if (response.equals("taken")) {
      socket.close();
      throw new DuplicateNameException(); //wird gefangen in WelcomeView: submitUserName()
    } else {
      this.userName = userName;
      chatMessages = FXCollections.observableArrayList();
    }
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String name) {
    this.userName = name;
  }

  // Übermittelt Input an den Server
  public void writeToServer(String input) {
    clientToServer.println(input);
  }

  public String receiveFromServer() throws IOException {
    return serverToClient.readLine();
  }

  public void closeConnection() throws IOException {
    socket.close();

  }

  @Override

  public void run() {

    while (true) {

      try {

        String inputFromServer = serverToClient.readLine(); // Gelesene Daten
                                                            // vom Server

        if (inputFromServer == null) {
          break;
        }

        Platform.runLater(() -> {

          chatMessages.add(inputFromServer); // Input wird zu chatMessages
                                             // hinzugefügt

        });

      } catch (IOException e) {

        e.printStackTrace();
        break;

      }

    }

  }

}
