package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.CacheRequest;
import java.net.Socket;

public class UserThread implements Runnable {

    private Socket socket;
    private String userName;
    private BufferedReader incoming;
    private PrintWriter outgoing;

    public UserThread(Socket socket) throws IOException {
        this.socket = socket;
        // BufferedReader und PrintWriter zum Datenaustausch zwsichen Server und
        // Client
        incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoing = new PrintWriter(socket.getOutputStream(), true);

    }

    public void run() {

        try {

            //Namensduplikatabfrage (serverseitig)
            boolean isAccepted = false; // wird auf true gesetzt, wenn valider Input vom Client kommt
            String proposedName = incoming.readLine();
            if (ChatServer.getUserNames().contains(proposedName)) {
                outgoing.println("taken");
                return; //run-Methode wird beendet, Client wird nicht erstellt, alles von vorne!
            } else {
                outgoing.println("OK"); // wird nur zurückgegeben, weil Client einen Input vom Server erwartet
            }

            // Hinzukommender User wird angezeigt
            if (proposedName != null) {
                isAccepted = true;
                userName = proposedName;
                ChatServer.addUser(userName);
                for (PrintWriter writer : ChatServer.getWriters()) {
                    writer.println(userName + " has joined the room.");
                }
                ChatServer.addWriter(outgoing);
            }

            // Input wird gelesen, While-Schleife wird nur mit Eingabe von "bye" verlassen
            while (isAccepted) {

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

        // userName und writer werden aus dem Server entfernt
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

                socket.close(); // Keine Verbindung mehr zum Server

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
