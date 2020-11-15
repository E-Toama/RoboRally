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

            //BufferedReader und PrintWriter zum Datenaustausch zwsichen Server und Client
            incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outgoing = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void run() {

        try {

            //Hinzukommender User wird angezeigt
            userName = incoming.readLine();

            for (PrintWriter writer : ChatServer.getWriters()) {

                writer.println(userName + " has joined");

            }

            ChatServer.addWriter(outgoing);

            //Input wird gelesen, While-Schleife wird mit Eingabe "bye" verlassen
            while (true) {

                String input = incoming.readLine();

                if (input.toLowerCase().startsWith("bye")) {

                    break;

                }

                for (PrintWriter writer : ChatServer.getWriters()) {

                    writer.println("[" + userName + "] " + input);

                }
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        //userName und writer werden aus dem Server entfernt
         finally {

            if (outgoing != null) {

                ChatServer.getWriters().remove(outgoing);

            }
            if (userName != null) {

                ChatServer.getUserNames().remove(userName);

                for (PrintWriter writer : ChatServer.getWriters()) {

                    writer.println(userName + " has left.");

                }

            }

            try {

                socket.close(); //Keine Verbindung mehr zum Server

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
