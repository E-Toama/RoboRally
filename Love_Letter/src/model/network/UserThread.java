package model.network;

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
    private ChatServer server;

    public UserThread(Socket socket, ChatServer server) throws IOException {
        this.socket = socket;
        this.server = server;
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
            if (server.getUserNames().contains(proposedName)) {
                outgoing.println("taken");
                return; //run-Methode wird beendet, Client wird nicht erstellt, alles von vorne!
            } else {
                outgoing.println("OK"); // wird nur zurückgegeben, weil Client einen Input vom Server erwartet
            }

            // Hinzukommender User wird angezeigt
            if (proposedName != null) {
                isAccepted = true;
                userName = proposedName;
                server.addUser(userName);
                for (PrintWriter writer : ChatServer.getWriters()) {
                    writer.println(userName + " has joined the room.");
                }
                server.addWriter(outgoing);
            }

            // Input wird gelesen, While-Schleife wird nur mit Eingabe von "bye" verlassen
            while (isAccepted) {

                String input = incoming.readLine();

                int i = input.indexOf(' ');
                String key = input.substring(0, i);
                String message = input.substring(i);

                switch (key) {

                    case "@USERNAME":
                        //write direct message to user

                    case "@ALL":
                        for (PrintWriter writer : server.getWriters()) {

                            writer.println("[" + userName + "] " + message);

                        }

                    case "?HELP":
                        //return game rules

                    case "?INFO":
                        //return information about your current card

                    case "?STATS":
                        //return current game state

                    case "?DISCARDED":
                        //return already played cards

                    case "!PLAYCARD":
                        //play card + handle second argument

                    case "!BYE":
                        break;

                    default:
                        System.out.println("false command");

                }

//                if (input.toLowerCase().startsWith("bye")) {
//
//                    break;
//
//                }
//
//                for (PrintWriter writer : server.getWriters()) {
//
//                    writer.println("[" + userName + "] " + input);
//
//                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        // userName und writer werden aus dem Server entfernt
        finally {

            if (outgoing != null) {

                server.getWriters().remove(outgoing);

            }
            if (userName != null) {

                server.getUserNames().remove(userName);

                for (PrintWriter writer : server.getWriters()) {

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
