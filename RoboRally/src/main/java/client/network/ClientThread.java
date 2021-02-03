package client.network;

import client.utilities.ClientGameState;
import client.utilities.ClientPlayerState;
import client.view.DamageChoiceDialog;
import client.view.GameBoardController;
import client.view.MainViewController;
import client.view.MapChoiceDialog;
import client.view.PlayerMatController;
import client.view.ProgrammingController;
import client.view.ViewController;
import client.viewmodel.ChatViewModel;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.PlayerMatModel;
import client.viewmodel.ProgrammingViewModel;
import client.viewmodel.WelcomeViewModel;
import game.Robots.Robot;
import game.cards.ActiveCard;
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
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import utilities.MessageHandler;
import utilities.MyLogger;
import utilities.messages.*;
import utilities.messages.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClientThread implements Runnable {

    private static ClientThread clientThread;
    private static Thread client;
    private final MyLogger logger = new MyLogger();
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

    private GameBoardViewModel gameBoardViewModel;
    private ProgrammingViewModel programmingViewModel;
    private PlayerMatModel playerMatModel;
    private EnemyMatModel enemyMatModel;


    private final HashMap<Integer, Player> playerList = new HashMap<>();
    private HashMap<Integer, ClientPlayerState> playerStateList = new HashMap<>();
    private ClientPlayerState clientPlayerState;
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

    public MainViewModel getMainViewModel() {
        return mainViewModel;
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

            //ToDO: FOr Testing reasons, the MainView is now initialized right at the beginning. Can be moved anytime.
            initializeEmptyMainView();

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

                    case "SelectMap":
                        handleSelectMap(incomingMessage);
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

            if (receivedMessage.getPlayer().getPlayerID() == this.ID) {

                this.player = receivedMessage.getPlayer();
                playerList.put(this.ID, player);

                Platform.runLater(() -> {
                    observablePlayerList.add(player.getName() + ", " + Robot.getRobotName(player.getFigure()));
                });

                Platform.runLater(() -> {
                    observablePlayerListWithDefault.add(player.getName() + ", " + Robot.getRobotName(player.getFigure()));
                });

                Platform.runLater(() -> {
                    messageMatchMap.put(player.getName() + ", " + Robot.getRobotName(player.getFigure()), player.getPlayerID());
                });

                welcomeViewModel.playerSuccesfullyAdded();

                //Get all infos from Message for Playerstate
                //ToDo: REWORK! Initialized PlayerMatModel here and bound to PlayerState for simple update mechanism

                clientPlayerState = new ClientPlayerState();
                //ToDO: Initialize PlayerMat-FXML and get Model-Instance-Variable
                playerMatModel.setPlayerState(clientPlayerState);
                playerStateList.put(receivedMessage.getPlayer().getPlayerID(), clientPlayerState);






            } else {
                //Initialize OtherPlayerState and add to PlayerStateList
                ClientPlayerState otherPlayerState = new ClientPlayerState();
                otherPlayerState.setPlayerValues(receivedMessage.getPlayer());
                playerStateList.put(receivedMessage.getPlayer().getPlayerID(), otherPlayerState);


                welcomeViewModel.disableRobotButton(receivedMessage.getPlayer().getFigure());

                Platform.runLater(() -> {
                    takenRobotList.add(receivedMessage.getPlayer().getFigure());
                });

                playerList.put(receivedMessage.getPlayer().getPlayerID() ,receivedMessage.getPlayer());

                Platform.runLater(() -> {
                    observablePlayerList.add(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()));
                });

                Platform.runLater(() -> {
                    observablePlayerListWithDefault.add(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()));
                });

                Platform.runLater(() -> {
                    messageMatchMap.put(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()), receivedMessage.getPlayer().getPlayerID());
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
            logger.getLogger().info("Player with name " + receivedMessage.getPlayer().getName() + " with id " + receivedMessage.getPlayer().getPlayerID() + " has been added.");

        } else {
            logger.getLogger().severe("Message body error in handlePlayerAdded method.");
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
           
            logger.getLogger().info("The player with id " + receivedMessage.getPlayerID() + " set his status to " + receivedMessage.getReady() + ".");

        } else {
            logger.getLogger().severe("Message body error in handlePlayerStatus method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerStatus)");

        }

    }

    private void handleSelectMap(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof SelectMap){
            SelectMap receivedMessage = (SelectMap) incomingMessage.getMessageBody();
            MapChoiceDialog mapChoiceDialog = new MapChoiceDialog();
            Platform.runLater(() -> {
                mapChoiceDialog.show(receivedMessage.getAvailableMaps());
            } );

        } else {
            logger.getLogger().severe("Message body error in handleSelectMap method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of SelectMap)");

        }
    }

    private void handleGameStarted(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof GameStarted){
            GameStarted receivedMessage = (GameStarted) incomingMessage.getMessageBody();
            GameBoard gameBoard = new GameBoard(receivedMessage.getMap());
            Platform.runLater(() -> {
                gameBoardViewModel.setGameBoard(gameBoard.getGameBoard());
            });

            Scene mainScene = new Scene(mainViewModel.getMainViewController().getMainViewPane());
            Platform.runLater(() -> {
                ViewController.getViewController().setScene(mainScene);
            });
            logger.getLogger().info("Game started.");

        } else {
            logger.getLogger().severe("Message body error in handleGameStarted method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of GameStarted)");

        }

    }

    private void handleReceivedChat(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof ReceivedChat) {

            ReceivedChat receivedMessage = (ReceivedChat) incomingMessage.getMessageBody();

            String message;

            if (receivedMessage.getPrivate()) {

                message = "[" + receivedMessage.getFrom() + "] private to you: " + receivedMessage.getMessage();
                logger.getLogger().info(this.player.getName() + " got a private message from " + receivedMessage.getFrom() + ".");
            } else {

                message = "[" + receivedMessage.getFrom() + "]: " + receivedMessage.getMessage();
                logger.getLogger().info(this.player.getName() + " got a normal message from " + receivedMessage.getFrom() + ".");
            }

            Platform.runLater(() -> {
                chatMessages.add(message);

            });

        } else {
            logger.getLogger().severe("Message body error in handleReceivedChat method.");
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
            logger.getLogger().info(connectionUpdate.getPlayerID() + " got disconnected.");
        } else {
            logger.getLogger().severe("Message body error in handleConnectionUpdate method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ConnectionUpdate)");
        }
    }

    private void handleCardPlayed(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CardPlayed) {
            CardPlayed cardPlayed = (CardPlayed) incomingMessage.getMessageBody();
            //ToDo: Implement "Card Played" behaviour
            logger.getLogger().info(cardPlayed.getCard() + " was played.");
        } else {
            logger.getLogger().severe("Message body error in handleCardPlayed method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardPlayed)");
        }
    }

    private void handleCurrentPlayer(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  CurrentPlayer) {
            CurrentPlayer currentPlayer = (CurrentPlayer) incomingMessage.getMessageBody();
            if (currentPlayer.getPlayerID() == ID) {
               if (clientGameState.getActivePhase() == 0) {
                   Platform.runLater(() -> {
                       gameBoardViewModel.getGameBoardController().initStartingPoints();
                   });
               }
               if (clientGameState.getActivePhase() == 3) {
                   Platform.runLater(() -> {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setHeaderText("PlayIt?");
                       alert.setContentText("Do you want to play that register?");
                       alert.showAndWait();
                       String playIt = messageHandler.buildMessage("PlayIt", new PlayIt());
                       outgoing.println(playIt);
                   });
               }
            }
            logger.getLogger().info("Current player id " + currentPlayer.getPlayerID() + ".");

            //Set all other players' current state to false
            for (Map.Entry<Integer, ClientPlayerState> state : playerStateList.entrySet()) {
                state.getValue().setCurrentPlayer(false);
            }
            playerStateList.get(currentPlayer.getPlayerID()).setCurrentPlayer(true);

        } else {
            logger.getLogger().severe("Message body error in handleCurrentPlayer method.");
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

            if (activePhase.getPhase() == 3) {
                Platform.runLater(() -> {
                    mainViewModel.switchScenes();
                });
            }

            logger.getLogger().info("The Active phase is " + activePhase.getPhase() + ".");

        } else {
            logger.getLogger().severe("Message body error in handleActivePhase method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ActivePhase)");
        }
    }

    private void handleStartingPointTaken(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  StartingPointTaken) {
            StartingPointTaken startingPointTaken = (StartingPointTaken) incomingMessage.getMessageBody();
            int playerID = startingPointTaken.getPlayerID();
            int chosenPoint = startingPointTaken.getPosition();
            Player currentPlayer = playerList.get(playerID);

            logger.getLogger().info(chosenPoint + " was picked by " + playerID + ".");
            
            if (playerID == ID) {
                Platform.runLater(() -> {
                    gameBoardViewModel.setStartingPosition(currentPlayer.getFigure(), chosenPoint);
                });
            } else {
                Platform.runLater(() -> {
                    gameBoardViewModel.setOtherRobotStartingPostion(currentPlayer.getFigure(), chosenPoint);
                });
            }

            //Add position info to ClientPlayerState
            playerStateList.get(playerID).setCurrentPosition(chosenPoint);

        } else {
            logger.getLogger().severe("Message body error in handleStartingPointTaken method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of StartingPointTaken)");
        }
    }

    private void handleYourCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  YourCards) {
            YourCards yourCards = (YourCards) incomingMessage.getMessageBody();
            String[] cards = yourCards.getCards();
            int cardsInPile = yourCards.getCardsInPile();
            logger.getLogger().info(this.player.getName() + " has drew his cards.");

            //Initialize ProgrammingView with new cards
            programmingViewModel = new ProgrammingViewModel();
            programmingViewModel.setCards(cards);

            //Update MainView and switch scenes (PlayerMat -> ProgrammingView)
            mainViewModel.getMainViewController().setProgrammingPane(programmingViewModel.getProgrammingController().getGridPane());
            Platform.runLater(() -> {
                mainViewModel.switchScenes();
            });

            //Add info to ClientPlayerState
            playerStateList.get(this.ID).setDeckCount(cardsInPile);

        } else {
            logger.getLogger().severe("Message body error in handleYourCards method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of YourCards)");
        }
    }

    private void handleNotYourCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  NotYourCards) {
            NotYourCards notYourCards = (NotYourCards) incomingMessage.getMessageBody();
            logger.getLogger().info("Other players have chosen their cards.");

            //Add deck count to playerstate
            playerStateList.get(notYourCards.getPlayerID()).setDeckCount(notYourCards.getCardsInPile());

        } else {
            logger.getLogger().severe("Message body error in handleNotYourCards method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of NotYourCards)");
        }
    }

    private void handleCardSelected(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  CardSelected) {
            CardSelected cardSelected = (CardSelected) incomingMessage.getMessageBody();
            int playerID = cardSelected.getPlayerID();
            int register = cardSelected.getRegister();
            if (playerID == ID) {

                logger.getLogger().info("Player with id " + playerID + " put a card in register " + register + ".");
                Platform.runLater(() -> {
                    programmingViewModel.confirmRegister(register);
                });
            } //ToDo: Here we wanted to update other PlayerMats with a card (backside)
            //      However: This message is sent if card is "null" and if card is valid
            //      --> no way to find out if the other player actually put a card in the register or removed one

        } else {
            logger.getLogger().severe("Message body error in handleCardSelected method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardSelected)");
        }
    }

    private void handleSelectionFinished(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  SelectionFinished) {
            SelectionFinished selectionFinished = (SelectionFinished) incomingMessage.getMessageBody();
            playerStateList.get(selectionFinished.getPlayerID()).setHasFinishedSelection(true);

            logger.getLogger().info(player.getName() + " finished his cards selection.");

        } else {
            logger.getLogger().severe("Message body error in handleSelectionFinished method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of SelectionFinished)");
        }
    }

    private void handleTimerStarted(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  TimerStarted) {
            TimerStarted timerStarted = (TimerStarted) incomingMessage.getMessageBody();
            logger.getLogger().info("Timer started counting down.");
            programmingViewModel.setTimer();
        } else {
            logger.getLogger().severe("Message body error in handleTimerStarted method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of TimerStarted)");
        }
    }

    private void handleTimerEnded(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof  TimerEnded) {
            TimerEnded timerEnded = (TimerEnded) incomingMessage.getMessageBody();
            String slowPlayers = "";
            for (Integer id : timerEnded.getPlayerIDs()) {
                slowPlayers += playerStateList.get(id).getUserName() + "\n";
            }
            programmingViewModel.getProgrammingController().setSlowPlayers(slowPlayers);

            Platform.runLater(()-> {
                programmingViewModel.endTimer();
            });

            logger.getLogger().info("Timer has ended.");
        } else {
            logger.getLogger().severe("Message body error in handleTimerEnded method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of TimerEnded)");
        }
    }

    private void handleDiscardHand(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof DiscardHand) {
            DiscardHand discardHand = (DiscardHand) incomingMessage.getMessageBody();
            Platform.runLater(() -> {
                programmingViewModel.getProgrammingController().discardHand();
            });

            logger.getLogger().info("Player needs to discard hand");
        } else {
            logger.getLogger().severe("Message body error in handleDiscardHand method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of DiscardHand)");
        }
    }

    private void handleCardsYouGotNow(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CardsYouGotNow) {
            CardsYouGotNow cardsYouGotNow = (CardsYouGotNow) incomingMessage.getMessageBody();
            String[] yourCards = cardsYouGotNow.getCards();
            programmingViewModel.setCardsYouGotNow(yourCards);
            Platform.runLater(() -> {
                programmingViewModel.getProgrammingController().cardsYouGotNow();
            });

            logger.getLogger().info(this.player.getName() + " got the cards: " + yourCards + ".");
        } else {
            logger.getLogger().severe("Message body error in handleCardsYouGotNow method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardsYouGotNow)");
        }
    }

    private void handleCurrentCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CurrentCards) {
            CurrentCards currentCards = (CurrentCards) incomingMessage.getMessageBody();
            ActiveCard[] activeCards = currentCards.getActiveCards();
            clientGameState.increaseDamageCardCount(activeCards);
            for (ActiveCard cards : activeCards) {
                if (cards.getPlayerID() == ID) {
                    Platform.runLater(() -> {
                        playerMatModel.getPlayerMatController().setTakenRegister(cards.getCard());
                    });
                } //ToDo: Else update otherPlayerMats
            }

            
            logger.getLogger().info("Received Current Cards.");
        } else {
            logger.getLogger().severe("Message body error in handleCurrentCards method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CurrentCards)");
        }
    }

    private void handleMovement(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Movement) {
            Movement movement = (Movement) incomingMessage.getMessageBody();
            int robotFigure = playerStateList.get(movement.getPlayerID()).getFigure();
            int currentPosition = playerStateList.get(movement.getPlayerID()).getCurrentPosition();
                Platform.runLater(() -> {
                    gameBoardViewModel.getGameBoardController().move(robotFigure, currentPosition, movement.getTo());
                });

            playerStateList.get(movement.getPlayerID()).setCurrentPosition(movement.getTo());
            
            logger.getLogger().info("Player with id " + movement.getPlayerID() + " moved his robot to field number " + movement.getTo() + ".");
        } else {
            logger.getLogger().severe("Message body error in handleMovement method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Movement)");
        }
    }

    private void handleDrawDamage(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof DrawDamage) {
            DrawDamage drawDamage = (DrawDamage) incomingMessage.getMessageBody();
            clientGameState.decreaseDamageCardCount(drawDamage.getCards());
            playerStateList.get(drawDamage.getPlayerID()).setPickedUpDamageCards(drawDamage.getCards().length);
            //ToDo: Update GUI DrawDamage?
            logger.getLogger().info("Player wiht id " + drawDamage.getPlayerID() + " has drew the damage cards: " + drawDamage.getCards() + ".");
        } else {
            logger.getLogger().severe("Message body error in handleDrawDamage method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of DrawDamage)");
        }
    }


    private void handlePickDamage(Message incomingMessage) throws IOException {
      if (incomingMessage.getMessageBody() instanceof PickDamage) {
        PickDamage pickDamage = (PickDamage) incomingMessage.getMessageBody();
        // TODO: Bind PopUp to Server-Client-Connection
          DamageChoiceDialog damageChoiceDialog = new DamageChoiceDialog();
          damageChoiceDialog.show(pickDamage.getCount(), clientGameState.getAvailableDamageCards());
        logger.getLogger().info(this.player.getName() + " has chosen " + pickDamage.getCount() + " damage cards.");
        
      } else {
        logger.getLogger().severe("Message body error in handlePickDamage method.");
        throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PickDamage)");
      }
    }

    private void handlePlayerShooting(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof PlayerShooting) {
            PlayerShooting playerShooting = (PlayerShooting) incomingMessage.getMessageBody();
            //ToDo: Update GUI PlayerShooting
            logger.getLogger().info("Player is shooting!");
        } else {
            logger.getLogger().severe("Message body error in handlePlayerShooting method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerShooting)");
        }
    }

    private void handleReboot(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Reboot) {
            Reboot reboot = (Reboot) incomingMessage.getMessageBody();
            //ToDo: Update GUI Reboot
            logger.getLogger().info("Player with id " + reboot.getPlayerID() + " has rebooted.");
        } else {
            logger.getLogger().severe("Message body error in handleReboot method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Reboot)");
        }
    }

    private void handlePlayerTurning(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof PlayerTurning) {
            PlayerTurning playerTurning = (PlayerTurning) incomingMessage.getMessageBody();
            int playerID = playerTurning.getPlayerID();
            int currentPosition = playerStateList.get(playerID).getCurrentPosition();
            String direction = playerTurning.getDirection();
            //ToDo: First Working Test for Self-Updating PlayerMat is bypassing ClientPlayerState
            Platform.runLater(()-> {
                gameBoardViewModel.getGameBoardController().playerTurning(currentPosition, direction);
            });

            playerStateList.get(playerID).setDirection(direction);
            logger.getLogger().info("Player with id " + playerTurning.getPlayerID() + " is turning his robot " + playerTurning.getDirection() + ".");
        } else {
            logger.getLogger().severe("Message body error in handlePlayerTurning method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerTurning)");
        }
    }

    private void handleEnergy(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Energy) {
            Energy energy = (Energy) incomingMessage.getMessageBody();
            //TODO: Has the player got energy or he has it already? update log according to answer.
            logger.getLogger().info("Player with id " + energy.getPlayerID() + " has " + energy.getCount() + " energy cubes.");
            Platform.runLater(() -> {
                playerMatModel.setEnergyPoints(energy.getCount());
            });

            playerStateList.get(energy.getPlayerID()).setEnergyPoints(energy.getCount());
            //ToDo: Update GUI Energy - maybe switch fields from full to empty?
        } else {
            logger.getLogger().severe("Message body error in handleEnergy method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Energy)");
        }
    }

    private void handleCheckPointReached(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CheckpointReached) {
            CheckpointReached checkpointReached = (CheckpointReached) incomingMessage.getMessageBody();
            //ToDo: Visualize reached Checkpoint? Currently only written to PlayerState
            logger.getLogger().info("Player with id " + checkpointReached.getPlayerID() + " has reached his " + checkpointReached.getNumber() + " checkpoint.");
            playerStateList.get(checkpointReached.getPlayerID()).setCheckpointsreached(checkpointReached.getNumber());
        } else {
            logger.getLogger().severe("Message body error in handleCheckPointReached method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CheckpointReached)");
        }
    }

    private void handleGameWon(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof GameWon) {
            GameWon gameWon = (GameWon) incomingMessage.getMessageBody();
            //ToDo: Update GUI GameWon - Simple PopUp?
            logger.getLogger().info("Player with id " + gameWon.getPlayerID() + " has won the game.");
        } else {
            logger.getLogger().severe("Message body error in handleGameWon method.");
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
                logger.getLogger().warning("Error with second incoming message happend.");
                throw new IOException(receivedMessage.getError());

            } else {
                logger.getLogger().severe("Second incoming message body error in establishConnection method.");
                throw new IOException("Something went wrong! Couldn't establish Connection!");

            }
            logger.getLogger().info("Connection established Successfully with the server.");
        } else {
            logger.getLogger().severe("First incoming message body error in establishConnection method.");
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

    public void sendSelectedCard(String selectedCard, int register) {
        String outgoingMessage = messageHandler.buildMessage("SelectCard", new SelectCard(selectedCard, register));
        outgoing.println(outgoingMessage);
    }

    public void sendSelectedMap(String[] userChoice) {
        String outgoingMessage = messageHandler.buildMessage("MapSelected", new MapSelected(userChoice));
        outgoing.println(outgoingMessage);
    }

    public void initializeEmptyMainView() throws IOException {
        //Init MainView-Container
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/MainView.fxml"));
        GridPane mainViewPane = mainLoader.load();
        mainViewModel = mainLoader.<MainViewController>getController().getMainViewModel();

        //Load Chat-View-FXML
        FXMLLoader chatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameViewChat.fxml"));
        Parent chatPane = chatLoader.load();
        mainViewModel.getMainViewController().setChatPane(chatPane);

        //Load PlayerMat-FXML and initialize PlayerMatModel
        FXMLLoader playerMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/PlayerMat.fxml"));
        Parent playerMatPane = playerMatLoader.load();
        playerMatModel = playerMatLoader.<PlayerMatController>getController().getPlayerMatModel();
        mainViewModel.getMainViewController().setPlayerMatPane(playerMatPane);

        //Load One Single OtherPlayers-FXML for Preview
        FXMLLoader enemyMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/EnemyMat.fxml"));
        GridPane enemyMatPane = enemyMatLoader.load();
        mainViewModel.getMainViewController().setEnemyPane(enemyMatPane);

        //Load ProgrammingView (non-FXML-based)
        programmingViewModel = new ProgrammingViewModel();
        GridPane programmingPane = programmingViewModel.getProgrammingController().getGridPane();
        mainViewModel.getMainViewController().setProgrammingPane(programmingPane);

        //Load GameBoard (non-FXML-based)
        gameBoardViewModel = new GameBoardViewModel();
        GridPane gameGrid = gameBoardViewModel.getGameBoardController().getGameGrid();
        mainViewModel.getMainViewController().setGameBoardPane(gameGrid);

    }

}
