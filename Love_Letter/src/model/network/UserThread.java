package model.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Handles the Client-Threads from the server-side
 * @author Josef, Dennis
 */
public class UserThread implements Runnable {

    private final Socket socket;
    private String userName;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final ChatServer server;

    /**
     * Constructs a UserThread only if Client successfully connects to server
     * and provides unique username
     * @param socket the server socket (port 9090)
     * @param server reference to the main ChatServer
     * @throws IOException if client could not connect to server
     */
    public UserThread(Socket socket, ChatServer server) throws IOException {

        this.socket = socket;
        this.server = server;

        incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoing = new PrintWriter(socket.getOutputStream(), true);

        try {
            //Look up proposed username in list
            String proposedName = incoming.readLine();
            if (server.getUserMap().containsKey(proposedName) || proposedName == null || proposedName.isBlank() || proposedName.isEmpty() || proposedName.equals("null")) {

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

    /**
     * Receives and processes user input in endless while-loop
     * Uses switch-cases to handle user commands and distribute them to the respecting server methods.
     * Catches IOExceptions and finally cleans up after user has left/disconnected
     */
    public void run() {

        try {

            server.sendMessageToSingleUser(userName, "You can use the following commands:");
            server.sendMessageToSingleUser(userName, "@ALL : Send a message to all users");
            server.sendMessageToSingleUser(userName, "@Username : Send message to single user");
            server.sendMessageToSingleUser(userName, "!CREATEGAME : To create a game");
            server.sendMessageToSingleUser(userName, "!JOINGAME : To join a game");
            server.sendMessageToSingleUser(userName, "!STARTGAME : To start the game");
            server.sendMessageToSingleUser(userName, "!BYE : Exit the chat / game");

            while (true) {
                //Unfiltered input from user
                String input = incoming.readLine();

                //Split user input into command (key) and arguments
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

                        if (!messageWithoutSecondAttribute.equals("null")) {

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

                        if (!messageWithoutSecondAttribute.equals("null")) {

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

                        if (!messageWithoutSecondAttribute.equals("null")) {

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
                        return;

                    default:
                        //Return game command suggestion
                        server.sendMessageToSingleUser(userName, EditDistance.getClosestString(input));
                        break;
                }

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        // userName and writer are removed from the server
        finally {

            server.getUserMap().remove(userName);
            server.sendMessageToAllUsers(userName + " has left.");


            try {

                socket.close(); // disconnect from server

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

}
