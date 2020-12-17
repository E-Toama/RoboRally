package server.network;

import player.Player;
import server.messages.*;
import server.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread implements Runnable {

    private final Socket socket;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final Server server;
    private final MessageHandler messageHandler = new MessageHandler();

    private Player player;
    private final int ID;
    private String group;
    private Boolean userIsAI;

    public UserThread(Socket socket, Server server, int ID) throws IOException {

        this.socket = socket;
        this.server = server;
        this.ID = ID;

        incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoing = new PrintWriter(socket.getOutputStream(), true);

        run();

    }

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

            try{

                String incomingJSON = incoming.readLine();

                if (incomingJSON == null) {

                    return;

                }

                Message incomingMessage = messageHandler.handleMessage(incomingJSON);

                switch (incomingMessage.getMessageType()) {

                    case "PlayerValues":
                        handlePlayerValues(incomingMessage);
                        break;

                    case "SetStatus":
                        handleSetStatus(incomingMessage);
                        break;

                    case "SendChat":
                        handleSendChat(incomingMessage);
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

    private void handlePlayerValues(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayerValues) {

            PlayerValues receivedMessage = (PlayerValues) incomingMessage.getMessageBody();

            String nameRobotCheck = server.checkIfNameAndRobotAreFree(receivedMessage.getName(), receivedMessage.getFigure());

            if (nameRobotCheck.equals("OK")) {

                Player player = new Player(this.ID, receivedMessage.getName(), receivedMessage.getFigure());

                this.player = player;

                server.addPlayer(this.ID, outgoing, player);

                String playerAdded = messageHandler.buildMessage("PlayerAdded", new PlayerAdded(player));

                outgoing.println(playerAdded);

            } else if (nameRobotCheck.equals("Name already taken!")) {

                String error = messageHandler.buildMessage("Error", new Error("Name already taken!"));

                outgoing.println(error);

            } else if (nameRobotCheck.equals("Figure already taken!")) {

                String error = messageHandler.buildMessage("Error", new Error("Figure already taken!"));

                outgoing.println(error);

            }


        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of PlayerValues)");

        }

    }

    private void handleSetStatus(Message incomingMessage) throws IOException {

        if(incomingMessage.getMessageBody() instanceof SetStatus) {

            SetStatus receivedMessage = (SetStatus) incomingMessage.getMessageBody();

            String outgoingMessage = messageHandler.buildMessage("PlayerStatus", new PlayerStatus(this.ID, receivedMessage.getReady()));

            server.sendMessageToAllUsers(outgoingMessage);

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SetStatus)");

        }

    }

    private void handleSendChat(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SendChat) {

            SendChat receivedMessage = (SendChat) incomingMessage.getMessageBody();

            if (receivedMessage.getTo() == -1) {

                String outgoingMessage = messageHandler.buildMessage(
                        "ReceivedChat", new ReceivedChat(receivedMessage.getMessage(), player.getName(), false));

                server.sendMessageToAllUsers(outgoingMessage);

            } else {

                String outgoingMessage = messageHandler.buildMessage(
                        "ReceivedChat", new ReceivedChat(receivedMessage.getMessage(), player.getName(), true));

                server.sendMessageToSingleUser(outgoingMessage, receivedMessage.getTo());

            }

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SendChat)");

        }

    }

    private void handleError(Message incomingMessage) throws IOException {

        //ToDo: handleError (UserThread)

    }

    private void establishConnection() throws IOException {

        String helloClient =
                messageHandler.buildMessage(
                        "HelloClient", new HelloClient(this.server.getProtocolVersion()));

        outgoing.println(helloClient);

        String incomingJSON = incoming.readLine();

        Message incomingMessage = messageHandler.handleMessage(incomingJSON);

        if (incomingMessage.getMessageType().equals("HelloServer") && incomingMessage.getMessageBody() instanceof HelloServer) {

                HelloServer receivedMessage = (HelloServer) incomingMessage.getMessageBody();

                if (receivedMessage.getProtocol() == server.getProtocolVersion()) {

                    this.group = receivedMessage.getGroup();
                    this.userIsAI = receivedMessage.getAI();

                    String welcome = messageHandler.buildMessage("Welcome", new Welcome(this.ID));
                    outgoing.println(welcome);

                } else {

                    String error = messageHandler.buildMessage("Error", new Error("Client protocol version is not supported!"));

                    outgoing.println(error);

                    throw new IOException("Client protocol version is not supported!");

                }

        } else {

            throw new IOException("Couldn't establish connection!");

        }

    }

}
