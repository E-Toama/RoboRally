package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatServer {

    private static Set<String> userNames = new HashSet<>();
    private static Set<PrintWriter> writers = new HashSet<>();

    //ChatServer wird erstellt und ausgeführt
    public static void main(String[] args) {

        ChatServer server = new ChatServer();
        server.execute();

    }

    //Gibt userNames aus
    public static Set<String> getUserNames() {

        return userNames;

    }

    //Fügt User zur HashSet userNames hinzu
    public static void addUser(String newUser) {

        userNames.add(newUser);

    }
    //Gibt Writer aus
    public static Set<PrintWriter> getWriters() {

        return writers;

    }
    //Fügt Writer zum HashSet writer hinzu
    public static void addWriter(PrintWriter newWriter) {

        writers.add(newWriter);

    }
    //Server mit Portnummer 9090 wird ausgeführt
    public void execute() {

        System.out.println("Server is running on port: 9090");

        Executor pool = Executors.newFixedThreadPool(50);

        try (ServerSocket listener = new ServerSocket(9090)) {

            while (true) {

                pool.execute(new UserThread(listener.accept())); //UserThread wird gestartet wenn ein Cient sich verbindet

            }

        } catch (IOException e) {

            System.out.println("Error in the server " + e.getMessage());

        }

    }

}
