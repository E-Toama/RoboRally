package model;

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

    public Client(String userName) throws IOException {

        socket = new Socket("localhost", 9090);
        this.userName = userName;
        serverToClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientToServer = new PrintWriter(socket.getOutputStream(), true);
        clientToServer.println(userName);
        chatMessages = FXCollections.observableArrayList();

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

    @Override
    public void run() {

        while (true) {

            try {

                String inputFromServer = serverToClient.readLine();

                if (inputFromServer == null) {
                    break;
                }

                Platform.runLater(() -> {

                    chatMessages.add(inputFromServer);

                });

            } catch (IOException e) {

                e.printStackTrace();
                break;

            }

        }

    }

}
