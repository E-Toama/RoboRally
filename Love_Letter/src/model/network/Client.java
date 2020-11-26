package model.network;

import exceptions.DuplicateNameException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Elias, Dennis, Josef
 */
public class Client implements Runnable {

    private Socket socket;
    private String userName;
    private BufferedReader serverToClient;
    private PrintWriter clientToServer;
    public ObservableList<String> chatMessages;

    public Client(String userName) throws IOException, DuplicateNameException {

        socket = new Socket("localhost", 9090);
        serverToClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientToServer = new PrintWriter(socket.getOutputStream(), true);

        clientToServer.println(userName);
        String response = serverToClient.readLine();

        if (response.equals("taken")) {

            socket.close();
            throw new DuplicateNameException("Name already taken, choose another one.");

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
