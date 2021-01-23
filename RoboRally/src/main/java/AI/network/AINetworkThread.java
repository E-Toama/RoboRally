package AI.network;

import utilities.MessageHandler;
import utilities.messages.*;
import utilities.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AINetworkThread implements Runnable {

    private final Socket socket;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final MessageHandler messageHandler = new MessageHandler();
    private int ID;

    private final double protocolVersion = 1.0;
    private final String group = "NeidischeNarwale";

    public AINetworkThread(int port) throws IOException {

        this.socket = new Socket("localhost", port);
        this.incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outgoing = new PrintWriter(socket.getOutputStream(), true);

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

    private void establishConnection() throws IOException {

        String firstIncomingJSON = incoming.readLine();

        Message firstIncomingMessage = messageHandler.handleMessage(firstIncomingJSON);

        if (firstIncomingMessage.getMessageType().equals("HelloClient") && firstIncomingMessage.getMessageBody() instanceof HelloClient) {

            String helloServer = messageHandler.buildMessage("HelloServer", new HelloServer(protocolVersion, group, true));

            outgoing.println(helloServer);

            String secondIncomingJSON = incoming.readLine();

            Message secondIncomingMessage = messageHandler.handleMessage(secondIncomingJSON);

            if(secondIncomingMessage.getMessageType().equals("Welcome") && secondIncomingMessage.getMessageBody() instanceof Welcome) {

                Welcome receivedMessage = (Welcome) secondIncomingMessage.getMessageBody();
                this.ID = receivedMessage.getPlayerID();

            } else if (secondIncomingMessage.getMessageType().equals("Error") && secondIncomingMessage.getMessageBody() instanceof utilities.messages.Error) {

                utilities.messages.Error receivedMessage = (Error) secondIncomingMessage.getMessageBody();

                throw new IOException(receivedMessage.getError());

            } else {

                throw new IOException("Something went wrong! Couldn't establish Connection!");

            }

        } else {

            throw new IOException("Something went wrong! Couldn't establish Connection!");

        }

    }

    public void choosePlayerValues() throws IOException {



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

                    case "ConnectionUpdate":
                        handleConnectionUpdate(incomingMessage);
                        break;

                    case "CardPlayed":
                        handleCardPlayed(incomingMessage);
                        break;

                    case "CurrentPlayer":
                        handleCurrentPlayer(incomingMessage);
                        break;

                    case "ActivePhase":
                        handleActivePhase(incomingMessage);
                        break;

                    case "StartingPointTaken":
                        handleStartingPointTaken(incomingMessage);
                        break;

                    case "YourCards":
                        handleYourCards(incomingMessage);
                        break;

                    case "NotYourCards":
                        handleNotYourCards(incomingMessage);
                        break;

                    case "ShuffleCoding":
                        //TODO: IS this case going to be handled by the client? If so, implement the handlerMethod here
                        break;

                    case "CardSelected":
                        handleCardSelected(incomingMessage);
                        break;

                    case "SelectionFinished":
                        handleSelectionFinished(incomingMessage);
                        break;

                    case "TimerStarted":
                        handleTimerStarted(incomingMessage);
                        break;

                    case "TimerEnded":
                        handleTimerEnded(incomingMessage);
                        break;

                    case "DiscardHand":
                        handleDiscardHand(incomingMessage);
                        break;

                    case "CardsYouGotNow":
                        handleCardsYouGotNow(incomingMessage);
                        break;

                    case "CurrentCards":
                        handleCurrentCards(incomingMessage);
                        break;

                    case "Movement":
                        handleMovement(incomingMessage);
                        break;

                    case "DrawDamage":
                        handleDrawDamage(incomingMessage);
                        break;

                    case "PlayerShooting":
                        handlePlayerShooting(incomingMessage);
                        break;

                    case "Reboot":
                        handleReboot(incomingMessage);
                        break;

                    case "PlayerTurning":
                        handlePlayerTurning(incomingMessage);
                        break;

                    case "Energy":
                        handleEnergy(incomingMessage);
                        break;

                    case "CheckPointReached":
                        handleCheckPointReached(incomingMessage);
                        break;

                    case "GameWon":
                        handleGameWon(incomingMessage);
                        break;

                    default:
                        break;

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

    }

    private void handlePlayerAdded(Message incomingMessage) {
    }

    private void handlePlayerStatus(Message incomingMessage) {
    }

    private void handleGameStarted(Message incomingMessage) {
    }

    private void handleReceivedChat(Message incomingMessage) {
    }

    private void handleError(Message incomingMessage) {
    }

    private void handleConnectionUpdate(Message incomingMessage) {
    }

    private void handleCardPlayed(Message incomingMessage) {
    }

    private void handleCurrentPlayer(Message incomingMessage) {
    }

    private void handleActivePhase(Message incomingMessage) {
    }

    private void handleStartingPointTaken(Message incomingMessage) {
    }

    private void handleYourCards(Message incomingMessage) {
    }

    private void handleNotYourCards(Message incomingMessage) {
    }

    private void handleCardSelected(Message incomingMessage) {
    }

    private void handleSelectionFinished(Message incomingMessage) {
    }

    private void handleTimerStarted(Message incomingMessage) {
    }

    private void handleTimerEnded(Message incomingMessage) {
    }

    private void handleDiscardHand(Message incomingMessage) {
    }

    private void handleCardsYouGotNow(Message incomingMessage) {
    }

    private void handleCurrentCards(Message incomingMessage) {
    }

    private void handleMovement(Message incomingMessage) {
    }

    private void handleDrawDamage(Message incomingMessage) {
    }

    private void handlePlayerShooting(Message incomingMessage) {
    }

    private void handleReboot(Message incomingMessage) {
    }

    private void handlePlayerTurning(Message incomingMessage) {
    }

    private void handleEnergy(Message incomingMessage) {
    }

    private void handleCheckPointReached(Message incomingMessage) {
    }

    private void handleGameWon(Message incomingMessage) {
    }


}
