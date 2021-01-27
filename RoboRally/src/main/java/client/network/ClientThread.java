package client.network;

import client.utilities.ClientGameState;
import client.utilities.ClientPlayerState;
import client.view.GameBoardController;
import client.view.MainViewController;
import client.view.ProgrammingController;
import client.view.ViewController;
import client.viewmodel.ChatViewModel;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.InGameChatModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.PlayerMatModel;
import client.viewmodel.ProgrammingViewModel;
import client.viewmodel.WelcomeViewModel;
import game.Robots.Robot;
import game.cards.Card;
import game.gameboard.GameBoard;
import game.utilities.PositionLookUp;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import game.player.Player;
import utilities.MessageHandler;
import utilities.messages.*;
import utilities.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ClientThread implements Runnable {

    private static ClientThread clientThread;
    private static Thread client;
    private static ClientGameState clientGameState = new ClientGameState();

    static {

        try {

            clientThread = new ClientThread();
            client = new Thread(clientThread);
            client.start();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    private final Socket socket;
    private final BufferedReader incoming;
    private final PrintWriter outgoing;
    private final MessageHandler messageHandler = new MessageHandler();
    public ObservableList<String> chatMessages = FXCollections.observableArrayList();

    private Player player;
    private int ID;
    private final double protocolVersion = 1.0;
    private final String group = "NeidischeNarwale";
    private Boolean isAI;

    private WelcomeViewModel welcomeViewModel;
    private ChatViewModel chatViewModel;

    //ViewModels for MainView
    private MainViewModel mainViewModel;
    private InGameChatModel inGameChatModel;
    private GameBoardViewModel gameBoardViewModel;
    private ProgrammingViewModel programmingViewModel;
    private PlayerMatModel playerMatModel;
    private EnemyMatModel enemyMatModel;


    private final HashMap<Integer, Player> playerList = new HashMap<>();
    public ObservableList<Integer> takenRobotList = FXCollections.observableArrayList();
    public HashMap<String, Integer> messageMatchMap = new HashMap<>();
    public ObservableList<String> observablePlayerList = FXCollections.observableArrayList();
    public ObservableList<String> observablePlayerListWithDefault = FXCollections.observableArrayList();

    public ClientThread() throws IOException {

        this.socket = new Socket("localhost", 9090);
        this.incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outgoing = new PrintWriter(socket.getOutputStream(), true);
        observablePlayerListWithDefault.add("Message To (Default: To All)");
        messageMatchMap.put("Message To (Default: To All)",-1);

    }

    public static ClientThread getInstance() {
        return clientThread;
    }

    public void setWelcomeViewModel(WelcomeViewModel welcomeViewModel) {
        this.welcomeViewModel = welcomeViewModel;
    }

    public void setChatViewModel(ChatViewModel chatViewModel) {
        this.chatViewModel = chatViewModel;
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
    }

    public void setInGameChatModel(InGameChatModel inGameChatModel) {
        this.inGameChatModel = inGameChatModel;
    }

    public void setGameBoardViewModel(GameBoardViewModel gameBoardViewModel) {
        this.gameBoardViewModel = gameBoardViewModel;
    }

    public void setProgrammingViewModel(ProgrammingViewModel programmingViewModel) {
        this.programmingViewModel = programmingViewModel;
    }

    public void setPlayerMatModel(PlayerMatModel playerMatModel) {
        this.playerMatModel = playerMatModel;
    }

    public void setEnemyMatModel(EnemyMatModel enemyMatModel) {
        this.enemyMatModel = enemyMatModel;
    }

    public Player getPlayer() {
        return player;
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

                    case "PickDamage":
                      handlePickDamage(incomingMessage);
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


    private void handlePlayerAdded(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayerAdded) {

            PlayerAdded receivedMessage = (PlayerAdded) incomingMessage.getMessageBody();

            if (receivedMessage.getPlayer().getId() == this.ID) {

                this.player = receivedMessage.getPlayer();
                playerList.put(this.ID, player);

                Platform.runLater(() -> {
                    observablePlayerList.add(player.getName() + ", " + Robot.getRobotName(player.getFigure()));
                });

                Platform.runLater(() -> {
                    observablePlayerListWithDefault.add(player.getName() + ", " + Robot.getRobotName(player.getFigure()));
                });

                Platform.runLater(() -> {
                    messageMatchMap.put(player.getName() + ", " + Robot.getRobotName(player.getFigure()), player.getId());
                });

                welcomeViewModel.playerSuccesfullyAdded();

            } else {

                welcomeViewModel.disableRobotButton(receivedMessage.getPlayer().getFigure());

                Platform.runLater(() -> {
                    takenRobotList.add(receivedMessage.getPlayer().getFigure());
                });

                playerList.put(receivedMessage.getPlayer().getId() ,receivedMessage.getPlayer());

                Platform.runLater(() -> {
                    observablePlayerList.add(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()));
                });

                Platform.runLater(() -> {
                    observablePlayerListWithDefault.add(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()));
                });

                Platform.runLater(() -> {
                    messageMatchMap.put(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()), receivedMessage.getPlayer().getId());
                });

                String notificationName = receivedMessage.getPlayer().getName() + " has joined!";

                Platform.runLater(() -> {
                    chatMessages.add(notificationName);
                });

                String notificationRobot = "He has Robot No. " + receivedMessage.getPlayer().getFigure();

                Platform.runLater(() -> {
                    chatMessages.add(notificationRobot);
                });

            }

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerAdded)");

        }

    }

    private void handlePlayerStatus(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof PlayerStatus) {

            PlayerStatus receivedMessage = (PlayerStatus) incomingMessage.getMessageBody();

            playerList.get(receivedMessage.getPlayerID()).setStatus(receivedMessage.getReady());

            Platform.runLater(() -> {
                chatMessages.add("[" + playerList.get(receivedMessage.getPlayerID()).getName() + "] changed status to: " + receivedMessage.getReady());
            });

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerStatus)");

        }

    }

    private void handleGameStarted(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof GameStarted){
            GameStarted receivedMessage = (GameStarted) incomingMessage.getMessageBody();
            GameBoard gameBoard = new GameBoard(receivedMessage.getMap());
            //toDo: GridPane, consisting of StackPanes, to be loaded into the MainView
            Scene mainViewScene = initMainView(gameBoard);
            Platform.runLater(() -> {
                ViewController.getViewController().setScene(mainViewScene);
            });

        } else {

            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of GameStarted)");

        }

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

    private void handleConnectionUpdate(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof ConnectionUpdate) {
            ConnectionUpdate connectionUpdate = (ConnectionUpdate) incomingMessage.getMessageBody();
            //ToDo: Implement "Remove game.player"-option
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ConnectionUpdate)");
        }
    }

    private void handleCardPlayed(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CardPlayed) {
            CardPlayed cardPlayed = (CardPlayed) incomingMessage.getMessageBody();
            //ToDo: Implement "Card Played" behaviour
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardPlayed)");
        }
    }

    private void handleCurrentPlayer(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  CurrentPlayer) {
            CurrentPlayer currentPlayer = (CurrentPlayer) incomingMessage.getMessageBody();
            //ToDo: Implement "CurrentPlayer" == Update GUI and turns
            if (currentPlayer.getPlayerID() == ID) {
               if (clientGameState.getActivePhase() == 0) {
                   Platform.runLater(() -> {
                       gameBoardViewModel.showStartingPoints();
                   });
               }
            }
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CurrentPlayer)");
        }
    }

    private void handleActivePhase(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  ActivePhase) {
            ActivePhase activePhase = (ActivePhase) incomingMessage.getMessageBody();
           /* TODO: Implement "ActivePhase"
           * Protocol:
           * 0 => Aufbauphase
           * 1 => Upgradephase
           * 2 => Programmierphase
           * 3 => Aktivierungsphase
           * */
            clientGameState.setActivePhase(activePhase.getPhase());

        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ActivePhase)");
        }
    }

    private void handleStartingPointTaken(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  StartingPointTaken) {
            StartingPointTaken startingPointTaken = (StartingPointTaken) incomingMessage.getMessageBody();
            int playerID = startingPointTaken.getPlayerID();
            int chosenPoint = startingPointTaken.getPosition();
            Player player = playerList.get(playerID);
            player.setCurrentPosition(chosenPoint);
            //ToDo: Implement "StartingPointTaken": "Wenn die gewünschte Position valide ist, werden alle Spieler darüber benachrichtigt."
            if (playerID == ID) {
                Platform.runLater(() -> {
                    gameBoardViewModel.setStartingPosition(player.getFigure(), chosenPoint);
                });
            } else {

                Platform.runLater(() -> {
                    gameBoardViewModel.setOtherRobotStartingPostion(player.getFigure(), chosenPoint);
                });
            }

        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of StartingPointTaken)");
        }
    }

    private void handleYourCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  YourCards) {
            YourCards yourCards = (YourCards) incomingMessage.getMessageBody();
            String[] cards = yourCards.getCards();
            int cardsInPile = yourCards.getCardsInPile();
            this.player.setCardsInDeck(cardsInPile);
            programmingViewModel = new ProgrammingViewModel();
            //CARDS aren't passed to the controller yet!
            programmingViewModel.setCards(cards);
            mainViewModel.getMainViewController().setProgrammingPane(programmingViewModel.getProgrammingController().getGridPane());
            Platform.runLater(() -> {
                mainViewModel.switchScenes();
            });


        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of YourCards)");
        }
    }

    private void handleNotYourCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  NotYourCards) {
            NotYourCards notYourCards = (NotYourCards) incomingMessage.getMessageBody();
            Player player = playerList.get(notYourCards.getPlayerID());
            player.setCardsInDeck(notYourCards.getCardsInPile());
            //ToDo: Implement "NotYourCards" (Client-Thread)
            mainViewModel.updateOtherPlayers(playerList);
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of NotYourCards)");
        }
    }

    private void handleCardSelected(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  CardSelected) {
            CardSelected cardSelected = (CardSelected) incomingMessage.getMessageBody();
            int playerID = cardSelected.getPlayerID();
            int register = cardSelected.getRegister();
            if (playerID == ID) {
                programmingViewModel.confirmRegister(register);
            } //ToDo: else update other PlayerMats with cardbackside
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardSelected)");
        }
    }

    private void handleSelectionFinished(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  SelectionFinished) {
            SelectionFinished selectionFinished = (SelectionFinished) incomingMessage.getMessageBody();

        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of SelectionFinished)");
        }
    }

    private void handleTimerStarted(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  TimerStarted) {
            TimerStarted timerStarted = (TimerStarted) incomingMessage.getMessageBody();
            //ToDo: "Als Folge des ersten fertigen Spielers startet der 30 Sekunden Timer."

        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of TimerStarted)");
        }
    }

    private void handleTimerEnded(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  TimerEnded) {
            TimerEnded timerEnded = (TimerEnded) incomingMessage.getMessageBody();
            //ToDo: "Meldung [...] beinhaltet auch die Information über evtl. zu langsam reagierende Spieler."
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of TimerEnded)");
        }
    }

    private void handleDiscardHand(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof DiscardHand) {
            DiscardHand discardHand = (DiscardHand) incomingMessage.getMessageBody();
            //ToDo: "Das Ergebnis wird dem Spieler dann mitgeteilt."
            // Will we do anything with this information? Some Visualization?
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of DiscardHand)");
        }
    }

    private void handleCardsYouGotNow(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CardsYouGotNow) {
            CardsYouGotNow cardsYouGotNow = (CardsYouGotNow) incomingMessage.getMessageBody();
            String[] yourCards = cardsYouGotNow.getCards();
            this.player.setRegisters(yourCards);
            mainViewModel.setCardsYouGotNow(yourCards);
            //ToDo: Game-logic for CardsYouGotNow

        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardsYouGotNow)");
        }
    }

    private void handleCurrentCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CurrentCards) {
            CurrentCards currentCards = (CurrentCards) incomingMessage.getMessageBody();
            //ToDo: Update GUI CurrentCards
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CurrentCards)");
        }
    }

    private void handleMovement(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Movement) {
            Movement movement = (Movement) incomingMessage.getMessageBody();
            //ToDo: Update GUI Movement
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Movement)");
        }
    }

    private void handleDrawDamage(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof DrawDamage) {
            DrawDamage drawDamage = (DrawDamage) incomingMessage.getMessageBody();
            //ToDo: Update GUI DrawDamage
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of DrawDamage)");
        }
    }


    private void handlePickDamage(Message incomingMessage) throws IOException {
      if (incomingMessage.getMessageBody() instanceof PickDamage) {
        PickDamage pickDamage = (PickDamage) incomingMessage.getMessageBody();
        // TODO: Update GUI PickDamage
      } else {
        throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PickDamage)");
      }
    }

    private void handlePlayerShooting(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof PlayerShooting) {
            PlayerShooting playerShooting = (PlayerShooting) incomingMessage.getMessageBody();
            //ToDo: Update GUI PlayerShooting
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerShooting)");
        }
    }

    private void handleReboot(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Reboot) {
            Reboot reboot = (Reboot) incomingMessage.getMessageBody();
            //ToDo: Update GUI Reboot
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Reboot)");
        }
    }

    private void handlePlayerTurning(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof PlayerTurning) {
            PlayerTurning playerTurning = (PlayerTurning) incomingMessage.getMessageBody();
            //ToDo: Update GUI PlayerTurning
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerTurning)");
        }
    }

    private void handleEnergy(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Energy) {
            Energy energy = (Energy) incomingMessage.getMessageBody();
            //ToDo: Update GUI Energy
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Energy)");
        }
    }

    private void handleCheckPointReached(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CheckpointReached) {
            CheckpointReached checkpointReached = (CheckpointReached) incomingMessage.getMessageBody();
            //ToDo: Update GUI CheckpointReached
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CheckpointReached)");
        }
    }

    private void handleGameWon(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof GameWon) {
            GameWon gameWon = (GameWon) incomingMessage.getMessageBody();
            //ToDo: Update GUI CheckpointReached
        } else {
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of GameWon)");
        }
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

                this.ID = receivedMessage.getPlayerID();

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

    public void submitPlayer(String name, int figure) {
        String outgoingMessage = messageHandler.buildMessage("PlayerValues", new PlayerValues(name, figure));
        outgoing.println(outgoingMessage);
    }

    public void sendPlayerStatus(boolean ready) {
        String outgoingMessage = messageHandler.buildMessage(("SetStatus"), new SetStatus(ready));
        outgoing.println(outgoingMessage);
    }

    public void sendStartingPosition(int position) {
        String outgoingMessage = messageHandler.buildMessage("SetStartingPoint", new SetStartingPoint(position));
        outgoing.println(outgoingMessage);
    }

    public void sendSelectionFinished() {
        String outgoingMessage = messageHandler.buildMessage("SelectionFinished", new SelectionFinished(ID));
        outgoing.println(outgoingMessage);
    }

    public void sendSelectedCard(String selectedCard, int register) {
        String outgoingMessage = messageHandler.buildMessage("SelectCard", new SelectCard(selectedCard, register));
        outgoing.println(outgoingMessage);
    }

    private Scene initMainView(GameBoard gameBoard) {
        mainViewModel = new MainViewModel();
        gameBoardViewModel = new GameBoardViewModel();
        gameBoardViewModel.setGameBoard(gameBoard.getGameBoard());

        gameBoardViewModel.getGameBoardController().initBoard();
        try {
            mainViewModel.getMainViewController().initializeMainView(playerList.size());
            mainViewModel.getMainViewController().setGameBoardPane(gameBoardViewModel.getGameBoardController().getGameGrid());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scene(mainViewModel.getMainViewController().getMainViewPane());
    }


}
