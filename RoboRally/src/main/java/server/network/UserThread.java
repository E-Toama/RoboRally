package server.network;

import game.cards.Card;
import game.player.Player;
import utilities.MessageHandler;
import utilities.MyLogger;
import utilities.messages.*;
import utilities.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

/**
 * This class represents the thread of the server for each client connected.
 * 
 * Doc: Josef
 */
public class UserThread implements Runnable {

    private final Socket socket;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final Server server;
    private final MessageHandler messageHandler = new MessageHandler();
    private final MyLogger logger = new MyLogger();



    private Player player;
    private final int playerID;
    private String group;
    private Boolean userIsAI;

    /**
     * Constructor for initializing and establishing a connection between the server and the clients.
     * 
     * @param socket
     *          the socket
     * @param server
     *          the server
     * @param ID
     *          The unique player id
     * @throws IOException 
     *          exception if something went wrong with the connection
     */
    public UserThread(Socket socket, Server server, int ID) throws IOException {

        this.socket = socket;
        this.server = server;
        this.playerID = ID;
        this.player = new Player(this.playerID, Integer.toString(this.playerID), 0);

        incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        outgoing = new PrintWriter(socket.getOutputStream(), true);

    }

    /**
     * This method starts the connection between the server and the client so that they can communicate.
     */
    public void run() {

        try{

            establishConnection();

            handleIncomingMessages();

        } catch (IOException e) {

            e.printStackTrace();

        }

        finally{

            try{

                if (server.getGame().getGameState().playerList.size() <= 1) {

                    int otherPlayerID = 0;

                    for (Player player : server.getGame().getGameState().playerList) {

                        if (player.getPlayerID() != playerID) {

                            otherPlayerID = player.getPlayerID();

                        }

                    }

                    String gameWon = messageHandler.buildMessage("GameWon", new GameWon(otherPlayerID));
                    server.sendMessageToAllUsers(gameWon);

                } else {

                    server.removePlayer(playerID);

                }

                String connectionUpdate = messageHandler.buildMessage("ConnectionUpdate", new ConnectionUpdate(playerID, false, "remove"));
                server.sendMessageToAllUsers(connectionUpdate);

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

            if (server.getGame() == null) {

                if (server.checkIfRobotIsFree(receivedMessage.getFigure())) {

                    Player player = new Player(this.playerID, receivedMessage.getName(), receivedMessage.getFigure());

                    this.player = player;

                    server.addPlayer(this.playerID, player);

                    server.notifyPlayersAboutNewPlayer(this.player);

                    logger.getLogger().info("Player name: " + receivedMessage.getName() + ", Player figure: " + receivedMessage.getFigure() + ".");

                } else {

                    String error = messageHandler.buildMessage("Error", new Error("Figure already taken!"));

                    outgoing.println(error);

                    logger.getLogger().warning("Error: '" + error + "' happend.");

                }

            } else {

                String error = messageHandler.buildMessage("Error", new Error("Game has already started!"));
                outgoing.println(error);

                throw new IOException("Game has already started!");

            }

        } else {

            logger.getLogger().severe("Message body error in handlePlayerValues method.");

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

            logger.getLogger().info("Player " + player.getName() + ", status: " + receivedMessage.getReady() + ".");

        } else {

            logger.getLogger().severe("Message body error in handleSetStatus method.");
            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SetStatus)");

        }

    }

    private void handleSendChat(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SendChat) {

            SendChat receivedMessage = (SendChat) incomingMessage.getMessageBody();

            if (receivedMessage.getTo() == -1) {

                String outgoingMessage = messageHandler.buildMessage(
                        "ReceivedChat", new ReceivedChat(receivedMessage.getMessage(), player.getPlayerID(), false));

                server.sendMessageToAllUsers(outgoingMessage);

                logger.getLogger().info(player.getName() + " sent a message to all users.");

            } else {

                String outgoingMessage = messageHandler.buildMessage(
                        "ReceivedChat", new ReceivedChat(receivedMessage.getMessage(), player.getPlayerID(), true));

                server.sendMessageToSingleUser(outgoingMessage, playerID);
                server.sendMessageToSingleUser(outgoingMessage, receivedMessage.getTo());

                logger.getLogger().info(player.getName() + " sent a message to a single user.");

            }

        } else {
            logger.getLogger().severe("Message body error in handleSendChat method.");
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

            logger.getLogger().info(player.getName() + " played " + cardPlayed + ".");

        } else {
            logger.getLogger().severe("Message body error in handlePlayCard method.");
            throw new IOException("Something went wrong! Invalid Message Body! (not instance of PlayCard)");
        }
    }

    private void handleSetStartingPoint(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SetStartingPoint) {

            SetStartingPoint setStartingPoint = (SetStartingPoint) incomingMessage.getMessageBody();
            server.getGame().continueStartingPointSelection(this.player, setStartingPoint.getPosition());
            logger.getLogger().info(player.getName() + " chose position " + setStartingPoint.getPosition() + " as his starting point.");
        } else {
            logger.getLogger().severe("Message body error in handleSetStartingPoint method.");
            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SetStartingPoint)");

        }

    }

    private void handleSelectCard(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SelectCard) {

            SelectCard selectCard = (SelectCard) incomingMessage.getMessageBody();
            String selectedCard = selectCard.getCards();
            int register = selectCard.getRegister();

            server.getGame().selectCard(selectedCard, register, playerID);
            logger.getLogger().info(player.getName() + " selected " + selectedCard + " into register " + register + ".");

        } else {
            logger.getLogger().severe("Message body error in handleSelectCard method.");
            throw new IOException("Something went wrong! Invalid Message Body! (not instance of SelectCard)");

        }

    }

    private void handlePlayIt(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayIt) {

            if (server.getGame().getGameState().registerList.size() > 0) {

                if (server.getGame().getGameState().registerList.get(0).getPlayer().getPlayerID() == playerID) {

                    server.getGame().continuePlayersTurn();

                }

            }
            logger.getLogger().info("player " + playerID + " played a card.");
        } else {
            logger.getLogger().severe("Message body error in handlePlayIt method.");
            throw new IOException("Something went wrong! Invalid Message Body! (not instance of PlayIt)");

        }

    }
    
    private void handleSelectDamage(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof SelectDamage) {

            SelectDamage selectDamage = (SelectDamage) incomingMessage.getMessageBody();

            server.getGame().getGameState().drawDamageCardHandler.selectDamage(playerID, selectDamage.getCards());
            logger.getLogger().info(player.getName() + " selected " + Arrays.toString(selectDamage.getCards()) + ".");
        } else {
            logger.getLogger().severe("Message body error in handleSelectDamage method.");
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

                if (server.getGame() != null) {

                    String error = messageHandler.buildMessage("Error", new Error("Game has already started!"));
                    outgoing.println(error);

                    logger.getLogger().severe("Couldn't establish connection! Game has already started!");
                    throw new IOException("Couldn't establish connection! Game has already started!");

                } else {

                    String welcome = messageHandler.buildMessage("Welcome", new Welcome(this.playerID));
                    outgoing.println(welcome);

                    server.addPrintWriter(this.playerID, outgoing);

                    server.sendStatusToNewPlayer(this.playerID);

                    logger.getLogger().info("Connection established succesfully with the Client.");

                }

            } else {

                String error = messageHandler.buildMessage("Error", new Error("Client protocol version is not supported!"));

                outgoing.println(error);
                logger.getLogger().warning("Error '" + error + "' happend.");
                throw new IOException("Client protocol version is not supported!");

            }

        } else {

            logger.getLogger().severe("Message body error in establishConnection method.");
            throw new IOException("Couldn't establish connection!");

        }

    }

}
