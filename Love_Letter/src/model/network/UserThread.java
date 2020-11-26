package model.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread implements Runnable {

    private final Socket socket;
    private String userName;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final ChatServer server;

    public UserThread(Socket socket, ChatServer server) throws IOException {

        this.socket = socket;
        this.server = server;

        incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoing = new PrintWriter(socket.getOutputStream(), true);

        try {

            String proposedName = incoming.readLine();
            if (server.getUserMap().containsKey(proposedName) || proposedName == null) {

                outgoing.println("taken");
                throw new IOException();

            } else {

                outgoing.println("OK");
                userName = proposedName;
                server.sendMessageToAllUsers(userName + " has joined the room.");
                server.addUser(userName, outgoing);

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void run() {

        try {

            while (true) {

                String input = incoming.readLine();

                int i = input.indexOf(' ');
                String key = "";
                String messageWithoutSecondAttribute = "";
                if (i > -1) {

                    key = input.substring(0, i);
                    messageWithoutSecondAttribute = input.substring(i + 1);

                } else if (input.length() > 0) {

                    key = input;

                }

                switch (key) {

                    case "@USERNAME":

                        int j = messageWithoutSecondAttribute.indexOf(' ');
                        if (j > 0) {

                            String secondAttribute = messageWithoutSecondAttribute.substring(0, j);
                            String messageWithSecondAttribute = messageWithoutSecondAttribute.substring(j + 1);

                            if (server.getUserMap().containsKey(secondAttribute)) {

                                server.sendMessageToSingleUser(secondAttribute, "[" + userName + "] private: " + messageWithSecondAttribute);
                                server.sendMessageToSingleUser(userName, "[" + userName + "] private to " + secondAttribute + " : " + messageWithSecondAttribute);

                            } else {

                                server.sendMessageToSingleUser(userName, "There is no user named: " + secondAttribute + ".");

                            }

                        } else {

                            server.sendMessageToSingleUser(userName, "You specified no User.");

                        }
                        break;

                    case "@ALL":

                        if (!messageWithoutSecondAttribute.equals(" ")) {

                            server.sendMessageToAllUsers("[" + userName + "] " + messageWithoutSecondAttribute);

                        } else {

                            server.sendMessageToSingleUser(userName, "Your Message was empty!");

                        }
                        break;

                    case "?INFO":
                        //return information about your current card
                        break;

                    case "?STATS":
                        server.getStatus(userName);
                        break;

                    case "?DISCARDED":
                        server.getDiscardedCards(userName);
                        break;

                    case "!PLAYLEFTCARD":
                        server.userPLaysLeftCard(userName);
                        break;

                    case "!PLAYRIGHTCARD":
                        server.userPLaysRightCard(userName);
                        break;

                    case "!CHOOSEANYPLAYER":

                        if (true) {

                            if (server.getUserMap().containsKey(messageWithoutSecondAttribute)) {

                                server.chooseAnyPlayer(userName, messageWithoutSecondAttribute);

                            } else {

                                server.sendMessageToSingleUser(userName, "There is no user named: " + messageWithoutSecondAttribute + ".");

                            }

                        } else {

                            server.sendMessageToSingleUser(userName, "You specified no User.");

                        }
                        break;

                    case "!CHOOSEANOTHERPLAYER":

                        if (true) {

                            if (server.getUserMap().containsKey(messageWithoutSecondAttribute)) {

                                server.chooseAnotherPlayer(userName, messageWithoutSecondAttribute);

                            } else {

                                server.sendMessageToSingleUser(userName, "There is no user named: " + messageWithoutSecondAttribute + ".");

                            }

                        } else {

                            server.sendMessageToSingleUser(userName, "You specified no User.");

                        }
                        break;

                    case "!GUESSCARD":

                        if (true) {

                            server.guessCard(userName, messageWithoutSecondAttribute);

                        } else {

                            server.sendMessageToSingleUser(userName, "You specified no Value.");

                        }
                        break;

                    case "!CREATEGAME":
                        server.creatGame(userName);
                        break;

                    case "!JOINGAME":
                        server.joinGame(userName);
                        break;

                    case "!STARTGAME":
                        server.startGame(userName);

                    case "!BYE":
                        break;

                    default:
                        server.sendMessageToSingleUser(userName, "Please use a valid Command!");
                        break;

                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        // userName und writer werden aus dem Server entfernt
        finally {

            server.getUserMap().remove(userName);
            server.sendMessageToAllUsers(userName + " has left.");


            try {

                socket.close(); // Keine Verbindung mehr zum Server

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
