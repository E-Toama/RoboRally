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

    public static void main(String[] args) {

        ChatServer server = new ChatServer();
        server.execute();

    }

    public static Set<String> getUserNames() {

        return userNames;

    }

    public static void addUser(String newUser) {

        userNames.add(newUser);

    }

    public static Set<PrintWriter> getWriters() {

        return writers;

    }

    public static void addWriter(PrintWriter newWriter) {

        writers.add(newWriter);

    }

    public void execute() {

        System.out.println("Server is running on port: 9090");

        Executor pool = Executors.newFixedThreadPool(50);

        try (ServerSocket listener = new ServerSocket(9090)) {

            while (true) {

                pool.execute(new UserThread(listener.accept()));

            }

        } catch (IOException e) {

            System.out.println("Error in the server " + e.getMessage());

        }

    }

}
