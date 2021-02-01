package server.network;

import game.player.Player;
import utilities.MessageHandler;
import utilities.messages.*;
import utilities.messages.Error;

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
    private final int playerID;
    private String group;
    private Boolean userIsAI;

    public UserThread(Socket socket, Server server, int ID) throws IOException {

        this.socket = socket;
        this.server = server;
        this.playerID = ID;
        this.player = new Player(this.playerID, Integer.toString(this.playerID), 0);

        incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoing = new PrintWriter(socket.getOutputStream(), true);

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

                    case "PlayCard":
                        handlePlayCard(incomingMessage);
                        break;

                    case "SetStartingPoint":
                        handleSetStartingPoint(incomingMessage);
                        break;

                    case "SelectCard":
                        handleSelectCard(incomingMessage);
                        break;

                    case "PlayIt":
                        handlePlayIt(incomingMessage);
                        break;
                        
                    case "SelectDamage":
                        handleSelectDamage(incomingMessage);
                        break;

                    case "MapSelected":
                        handleMapSelected(incomingMessage, incomingJSON);

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

            if (server.checkIfRobotIsFree(receivedMessage.getFigure())) {

                Player player = new Player(this.playerID, receivedMessage.getName(), receivedMessage.getFigure());

                this.player = player;

                server.addPlayer(this.playerID, player);

                server.notifyPlayersAboutNewPlayer(this.player);

                //server.(this.player.getId());

            } else {

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

            server.setStatus(playerID, receivedMessage.getReady());

            String outgoingMessage = messageHandler.buildMessage("PlayerStatus", new PlayerStatus(this.playerID, receivedMessage.getReady()));

            server.sendMessageToAllUsers(outgoingMessage);

            server.checkIfGameCanStart();

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

    private void handlePlayCard(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof PlayCard) {
            PlayCard playCard = (PlayCard) incomingMessage.getMessageBody();
            String cardPlayed = playCard.getCard();
            //ToDo: handle PlayCard (UserThread)
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (not instance of PlayCard)");
        }
    }

    private void handleSetStartingPoint(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SetStartingPoint) {

            SetStartingPoint setStartingPoint = (SetStartingPoint) incomingMessage.getMessageBody();
            server.getGame().continueStartingPointSelection(this.player, setStartingPoint.getPosition());

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SetStartingPoint)");

        }

    }

    private void handleSelectCard(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SelectCard) {

            SelectCard selectCard = (SelectCard) incomingMessage.getMessageBody();
            String selectedCard = selectCard.getCards();
            int register = selectCard.getRegister();

            server.getGame().selectCard(selectedCard, register, playerID);


        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SelectCard)");

        }

    }

    private void handlePlayIt(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayIt) {

            if (server.getGame().getGameState().registerList.get(0).getPlayer().getPlayerID() == playerID) {

                server.getGame().continuePlayersTurn();

            }

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of PlayIt)");

        }

    }
    
    private void handleSelectDamage(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SelectDamage) {

            SelectDamage selectDamage = (SelectDamage) incomingMessage.getMessageBody();

            server.getGame().getGameState().drawDamageCardHandler.selectDamage(playerID, selectDamage.getCards());

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SelectDamage)");

        }
      
    }

    private void handleMapSelected(Message incomingMessage, String incomingJson) throws IOException {

        if (incomingMessage.getMessageBody() instanceof MapSelected) {

            MapSelected mapSelected = (MapSelected) incomingMessage.getMessageBody();

            server.setSelectedGameBoard(mapSelected.getMap()[0]);

            server.sendMessageToAllUsers(incomingJson);

            server.checkIfGameCanStart();

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SelectDamage)");

        }

    }

    private void establishConnection() throws IOException {

        String helloClient = messageHandler.buildMessage("HelloClient", new HelloClient(this.server.getProtocolVersion()));

        outgoing.println(helloClient);

        String incomingJSON = incoming.readLine();

        Message incomingMessage = messageHandler.handleMessage(incomingJSON);

        if (incomingMessage.getMessageType().equals("HelloServer") && incomingMessage.getMessageBody() instanceof HelloServer) {

                HelloServer receivedMessage = (HelloServer) incomingMessage.getMessageBody();

                if (receivedMessage.getProtocol() == server.getProtocolVersion()) {

                    this.group = receivedMessage.getGroup();
                    this.userIsAI = receivedMessage.getAI();

                    String welcome = messageHandler.buildMessage("Welcome", new Welcome(this.playerID));
                    outgoing.println(welcome);

                    server.addPrintWriter(this.playerID, outgoing);

                    server.sendStatusToNewPlayer(this.playerID);

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
