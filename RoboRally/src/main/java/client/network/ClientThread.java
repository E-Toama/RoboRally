package client.network;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import player.Player;
import server.messages.*;
import server.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class ClientThread implements Runnable {

    private final Socket socket;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final MessageHandler messageHandler = new MessageHandler();
    public ObservableList<String> chatMessages;

    private Player player;
    private int ID;
    private final double protocolVersion = 0.1;
    private final String group = "NeidischeNarwale";
    private final Boolean isAI;

    private HashMap<Integer, Player> playerList = new HashMap<>();
    private ObservableMap<Integer, Player> observablePlayerMap;

    public ClientThread(Boolean isAI) throws IOException {

        this.socket = new Socket("localhost", 9090);
        this.incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outgoing = new PrintWriter(socket.getOutputStream(), true);

        this.isAI = isAI;

        run();

    }


    @Override
    public void run() {

        try{

            establishConnection();

            handleIncomingMessages();

        } catch (IOException e) {

            e.printStackTrace();

        }

        finally{

            try{

                socket.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void handleIncomingMessages() throws IOException {

        while (true) {

            try {

                String incomingJSON = incoming.readLine();

                if (incomingJSON == null) {

                    return;

                }

                Message incomingMessage = messageHandler.handleMessage(incomingJSON);

                switch (incomingMessage.getMessageType()) {

                    case "PlayerAdded":
                        handlePlayerAdded(incomingMessage);
                        break;

                    case "PlayerStatus":
                        handlePlayerStatus(incomingMessage);
                        break;

                    case "GameStarted":
                        handleGameStarted(incomingMessage);
                        break;

                    case "ReceivedChat":
                        handleReceivedChat(incomingMessage);
                        break;

                    case "Error":
                        handleError(incomingMessage);
                        break;

                    default:
                        break;

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void handlePlayerAdded(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayerAdded) {

            PlayerAdded receivedMessage = (PlayerAdded) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayer().getId() == this.ID) {

                this.player = receivedMessage.getPlayer();
                playerList.put(this.ID, player);
                observablePlayerMap.put(this.ID, player);

                //ToDo: name und roboter waren noch frei => view kann geändert werden

            } else if (this.player != null) {

                playerList.put(receivedMessage.getPlayer().getId() ,receivedMessage.getPlayer());
                observablePlayerMap.put(receivedMessage.getPlayer().getId() ,receivedMessage.getPlayer());

                String notificationName = receivedMessage.getPlayer().getName() + " has joined!";
                chatMessages.add(notificationName);

                String notificationRobot = "He has Robot No. " + receivedMessage.getPlayer().getFigure();
                chatMessages.add(notificationRobot);

            } else {

                throw new IOException("Something went wrong! Invalid PlayerAdded message!");

            }

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerAdded)");

        }

    }

    private void handlePlayerStatus(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayerStatus) {

            PlayerStatus receivedMessage = (PlayerStatus) incomingMessage.getMessageBody();

            playerList.get(receivedMessage.getId()).setStatus(receivedMessage.getReady());
            observablePlayerMap.get(receivedMessage.getId()).setStatus(receivedMessage.getReady());

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerStatus)");

        }

    }

    private void handleGameStarted(Message incomingMessage) throws IOException {

        //ToDo: handleGameStarted

    }

    private void handleReceivedChat(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof ReceivedChat) {

            ReceivedChat receivedMessage = (ReceivedChat) incomingMessage.getMessageBody();

            String message;

            if (receivedMessage.getPrivate()) {

                message = "[" + receivedMessage.getFrom() + "] private to you: " + receivedMessage.getMessage();

            } else {

                message = "[" + receivedMessage.getFrom() + "]: " + receivedMessage.getMessage();

            }

            Platform.runLater(() -> {
                chatMessages.add(message);
            });

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ReceivedChat)");

        }

    }

    private void handleError(Message incomingMessage) throws IOException {

        //ToDo: handleError (ClientThread)

    }

    private void establishConnection() throws IOException {

        String firstIncomingJSON = incoming.readLine();

        Message firstIncomingMessage = messageHandler.handleMessage(firstIncomingJSON);

        if (firstIncomingMessage.getMessageType().equals("HelloClient") && firstIncomingMessage.getMessageBody() instanceof HelloClient) {

            String helloServer = messageHandler.buildMessage("HelloServer", new HelloServer(protocolVersion, group, isAI));

            outgoing.println(helloServer);

            String secondIncomingJSON = incoming.readLine();

            Message secondIncomingMessage = messageHandler.handleMessage(secondIncomingJSON);

            if(secondIncomingMessage.getMessageType().equals("Welcome") && secondIncomingMessage.getMessageBody() instanceof Welcome) {

                Welcome receivedMessage = (Welcome) secondIncomingMessage.getMessageBody();

                this.ID = receivedMessage.getId();

            } else if (secondIncomingMessage.getMessageType().equals("Error") && secondIncomingMessage.getMessageBody() instanceof Error) {

                Error receivedMessage = (Error) secondIncomingMessage.getMessageBody();

                throw new IOException(receivedMessage.getError());

            } else {

                throw new IOException("Something went wrong! Couldn't establish Connection!");

            }

        } else {

            throw new IOException("Something went wrong! Couldn't establish Connection!");

        }

    }

    public void sendMessage(String message, int to) {

        String outgoingMessage = messageHandler.buildMessage("SendChat", new SendChat(message, to));
        outgoing.println(outgoingMessage);

    }


}
