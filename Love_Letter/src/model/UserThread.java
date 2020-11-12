package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread implements Runnable {

    private Socket socket;
    private String userName;
    private BufferedReader incoming;
    private PrintWriter outgoing;

    public UserThread(Socket socket) {

        this.socket = socket;

        try {

            incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outgoing = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void run() {

        try {

            userName = incoming.readLine();

            for (PrintWriter writer : ChatServer.getWriters()) {

                writer.println(userName + " has joined");
                System.out.println(userName + "has joined");

            }

            ChatServer.addWriter(outgoing);

            while (true) {

                String input = incoming.readLine();

                if (input.toLowerCase().startsWith("bye")) {

                    break;

                }

                for (PrintWriter writer : ChatServer.getWriters()) {

                    writer.println("[" + userName + "] " + input);
                    System.out.println(input);

                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (outgoing != null) {

                ChatServer.getWriters().remove(outgoing);

            }
            if (userName != null) {

                ChatServer.getUserNames().remove(userName);

                for (PrintWriter writer : ChatServer.getWriters()) {

                    writer.println(userName + " has left.");
                    System.out.println(userName + "has left.");

                }

            }

            try {

                socket.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
