package client.network;

import client.utilities.ClientGameState;
import client.view.EnemyMatController;
import client.view.GameOverController;
import client.view.MainViewController;
import client.view.PlayerMatController;
import client.view.PopupController;
import client.view.ViewController;
import client.viewmodel.ChatViewModel;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.GameOverModel;
import client.viewmodel.MainViewModel;
import client.viewmodel.PlayerMatModel;
import client.viewmodel.ProgrammingViewModel;
import client.viewmodel.WelcomeViewModel;
import game.robots.Robot;
import game.cards.ActiveCard;
import game.gameboard.GameBoard;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import game.player.Player;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    private final double protocolVersion = 1.0;
    private final String group = "NeidischeNarwale";
    private final HashMap<Integer, Player> playerList = new HashMap<>();
    private final ArrayList<Integer> enemyIDList = new ArrayList<>();
    private final HashMap<Integer, EnemyMatModel> enemyList = new HashMap<>();
    public ObservableList<String> chatMessages = FXCollections.observableArrayList();
    public ObservableList<Integer> takenRobotList = FXCollections.observableArrayList();
    public HashMap<String, Integer> messageMatchMap = new HashMap<>();
    public ObservableList<String> observablePlayerList = FXCollections.observableArrayList();
    public ObservableList<String> observablePlayerListWithDefault = FXCollections.observableArrayList();
    private Player player;
    private int ID;
    private Boolean isAI;
    private WelcomeViewModel welcomeViewModel;
    private ChatViewModel chatViewModel;
    //ViewModels for MainView
    private MainViewModel mainViewModel;
    private GameBoardViewModel gameBoardViewModel;
    private ProgrammingViewModel programmingViewModel;
    private PlayerMatModel playerMatModel;
    private EnemyMatModel enemyMatModel;

    public ClientThread() throws IOException {

        this.socket = new Socket("localhost", 9090);
        this.incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.outgoing = new PrintWriter(socket.getOutputStream(), true);
        observablePlayerListWithDefault.add("Message To (Default: To All)");
        messageMatchMap.put("Message To (Default: To All)", -1);

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

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    public GameBoardViewModel getGameBoardViewModel() {
        return gameBoardViewModel;
    }

    public PlayerMatModel getPlayerMatModel() {
        return playerMatModel;
    }

    public int getActivePhase() {
        return clientGameState.getActivePhase();
    }

    public void setMainViewModel(MainViewModel mainViewModel) {
        this.mainViewModel = mainViewModel;
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

        try {

            establishConnection();

            initializeEmptyMainView();

            handleIncomingMessages();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

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

                    case "MapSelected":
                        handleMapSelected(incomingMessage);
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
                        handleShuffleCoding(incomingMessage);
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

                    case "CheckpointReached":
                        handleCheckpointReached(incomingMessage);
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
            playerList.put(receivedMessage.getPlayer().getPlayerID(), receivedMessage.getPlayer());

            if (receivedMessage.getPlayer().getPlayerID() == this.ID) {

                this.player = receivedMessage.getPlayer();

                Platform.runLater(() -> {
                    observablePlayerList.add(player.getName() + ", " + Robot.getRobotName(player.getFigure()));
                    observablePlayerListWithDefault.add(player.getName() + ", " + Robot.getRobotName(player.getFigure()));
                    messageMatchMap.put(player.getName() + ", " + Robot.getRobotName(player.getFigure()), player.getPlayerID());
                });

                welcomeViewModel.playerSuccesfullyAdded();

                playerMatModel.setPlayerValues(player);


            } else {

                enemyIDList.add(receivedMessage.getPlayer().getPlayerID());
                welcomeViewModel.disableRobotButton(receivedMessage.getPlayer().getFigure());
                String notificationName = "Player " + receivedMessage.getPlayer().getName() + " has joined!";
                String notificationRobot = "He chose Robot No. " + receivedMessage.getPlayer().getFigure();

                Platform.runLater(() -> {
                    takenRobotList.add(receivedMessage.getPlayer().getFigure());
                    observablePlayerList.add(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()));
                    observablePlayerListWithDefault.add(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()));
                    messageMatchMap.put(receivedMessage.getPlayer().getName() + ", " + Robot.getRobotName(receivedMessage.getPlayer().getFigure()), receivedMessage.getPlayer().getPlayerID());
                    chatMessages.add(notificationName);
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
        if (incomingMessage.getMessageBody() instanceof SelectMap) {
            SelectMap receivedMessage = (SelectMap) incomingMessage.getMessageBody();
            String[] availableMaps = receivedMessage.getAvailableMaps();

            Platform.runLater(() -> {
                PopupController popupController = new PopupController();
                popupController.showMapChoice(availableMaps);
            });

        } else {
            logger.getLogger().severe("Message body error in handleSelectMap method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of SelectMap)");

        }
    }

    private void handleMapSelected(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof MapSelected) {
            MapSelected receivedMessage = (MapSelected) incomingMessage.getMessageBody();
            String selectedMap = receivedMessage.getMap()[0];
            clientGameState.setSelectedMap(selectedMap);
            GameBoard gameBoard = new GameBoard(selectedMap);
            Platform.runLater(() -> {
                gameBoardViewModel.setGameBoard(gameBoard);
            });

        } else {
            logger.getLogger().severe("Message body error in handleMapSelected method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of MapSelected)");

        }
    }

    private void handleGameStarted(Message incomingMessage) throws IOException {

        if (incomingMessage.getMessageBody() instanceof GameStarted) {
            GameStarted receivedMessage = (GameStarted) incomingMessage.getMessageBody();

            //Fallback-Construction of GameBoard if MapSelection failed
            if (clientGameState.getSelectedMap().isEmpty()) {
                GameBoard gameBoard = new GameBoard(receivedMessage.getMap());
                gameBoardViewModel.setGameBoard(gameBoard);
            }

            //Create all EnemyMats here, because this is the first moment in the game
            // when the final number of players is known
            buildEnemyViews();

            Scene mainScene = new Scene(mainViewModel.getMainViewController().getMainViewPane());
            Platform.runLater(() -> {
                ViewController.getViewController().setScene(mainScene);
                chatMessages.add("[GAME] \n" +
                        "The game has started!");
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
        if (incomingMessage.getMessageBody() instanceof Error) {
            Error error = (Error) incomingMessage.getMessageBody();
            Platform.runLater(() -> {
                PopupController popupController = new PopupController();
                popupController.showErrorMessage(error.getError());
            });

        } else {
            logger.getLogger().severe("Message body error in ErrorMessage method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ErrorMessage)");
        }

    }

    /**
     * Removes the playerID mentioned in the message from all playerlists and
     * removes the figure from the Board
     *
     * @param incomingMessage contains playerID, status and action
     * @throws IOException if messageType is incorrect
     */
    private void handleConnectionUpdate(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof ConnectionUpdate) {
            ConnectionUpdate connectionUpdate = (ConnectionUpdate) incomingMessage.getMessageBody();
            int playerID = connectionUpdate.getPlayerID();
            String robotName = Robot.getRobotName(playerList.get(playerID).getFigure());
            int positionToRemove = enemyList.get(playerID).getCurrentPosition();

            enemyList.remove(playerID);
            playerList.remove(playerID);

            Platform.runLater(() -> {
                chatMessages.add(robotName + " has lost connection and left the game");
                gameBoardViewModel.getGameBoardController().removeRobot(positionToRemove);
            });

            logger.getLogger().info(connectionUpdate.getPlayerID() + " got disconnected.");
        } else {
            logger.getLogger().severe("Message body error in handleConnectionUpdate method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ConnectionUpdate)");
        }
    }

    /**
     * Unused method stub for future implementation of Upgradephase
     * @param incomingMessage contains playerID and played card
     * @throws IOException
     */
    private void handleCardPlayed(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CardPlayed) {
            CardPlayed cardPlayed = (CardPlayed) incomingMessage.getMessageBody();
            logger.getLogger().info(cardPlayed.getCard() + " was played.");
        } else {
            logger.getLogger().severe("Message body error in handleCardPlayed method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardPlayed)");
        }
    }

    private void handleCurrentPlayer(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CurrentPlayer) {
            CurrentPlayer currentPlayer = (CurrentPlayer) incomingMessage.getMessageBody();
            int playerID = currentPlayer.getPlayerID();
            if (playerID == ID) {

                //Update CurrentPlayer-Status for all players
                playerMatModel.setCurrentPlayer(true);
                for (Map.Entry<Integer, EnemyMatModel> enemy : enemyList.entrySet()) {
                    enemy.getValue().setCurrentPlayer(false);
                }

                if (clientGameState.getActivePhase() == 0) {
                    String robotName = playerMatModel.getRobotName().getValue();
                    String gameMessage = "[GAME] \n" +"It´s" + robotName + "´s turn to choose a starting point!";

                    Platform.runLater(() -> {
                        chatMessages.add(gameMessage);
                        gameBoardViewModel.getGameBoardController().initStartingPoints();
                    });
                }
                if (clientGameState.getActivePhase() == 3) {
                    playerMatModel.getPlayerMatController().activateRegisterButton();
                    Platform.runLater(() -> {
                        chatMessages.add("[GAME] \n" +
                                "Click on the cards on your register to \n" +
                                "command your robot!");
                    });
                }
            } else {
                //Update CurrentPlayer-Status
                enemyList.get(playerID).setCurrentPlayer(true);
                playerMatModel.setCurrentPlayer(false);
            }


            logger.getLogger().info("Current player id is " + currentPlayer.getPlayerID() + ".");

        } else {
            logger.getLogger().severe("Message body error in handleCurrentPlayer method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CurrentPlayer)");
        }
    }

    private void handleActivePhase(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof ActivePhase) {
            ActivePhase activePhase = (ActivePhase) incomingMessage.getMessageBody();

            clientGameState.setActivePhase(activePhase.getPhase());

            if (activePhase.getPhase() == 2) {
                Platform.runLater(() -> {
                    playerMatModel.updateDiscardedCount();
                    chatMessages.add("[Game] \n" +
                            "The programming phase has started. \n"+
                            "select the cards on your register to program \n" +
                            "your robot."
                    );
                });
            }
            if (activePhase.getPhase() == 3) {
                playerMatModel.getPlayerMatController().resetRegisterCounts();
                for (Map.Entry<Integer, EnemyMatModel> entry : enemyList.entrySet()) {
                    Platform.runLater(() -> {
                        entry.getValue().getEnemyMatController().resetRegisterCounts();
                    });
                }
                String slowPlayers = programmingViewModel.getSlowPlayers();
                String[] cardsInRegister = programmingViewModel.getCardsYouGotNow();
                Platform.runLater(() -> {
                    PopupController popupController = new PopupController();
                    popupController.showEndOfProgrammingPhase(slowPlayers, cardsInRegister);
                    mainViewModel.switchScenes();
                    chatMessages.add("[GAME \n"
                        + "The activation phase has started. \n"
                            + "click on the cards on your register to move \n " +
                              "your robot.");

                });
            }


            logger.getLogger().info("The Active phase is " + activePhase.getPhase() + ".");

        } else {
            logger.getLogger().severe("Message body error in handleActivePhase method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ActivePhase)");
        }
    }

    private void handleStartingPointTaken(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof StartingPointTaken) {
            StartingPointTaken startingPointTaken = (StartingPointTaken) incomingMessage.getMessageBody();
            int playerID = startingPointTaken.getPlayerID();
            int chosenPoint = startingPointTaken.getPosition();
            Player currentPlayer = playerList.get(playerID);

            logger.getLogger().info("position " + chosenPoint + " was picked by " + playerID + ".");

            if (playerID == ID) {
                playerMatModel.setCurrentPosition(chosenPoint);
                Platform.runLater(() -> {
                    gameBoardViewModel.setStartingPosition(currentPlayer.getFigure(), chosenPoint);
                });
            } else {
                enemyList.get(playerID).setCurrentPosition(chosenPoint);

                Platform.runLater(() -> {
                    gameBoardViewModel.setOtherRobotStartingPostion(currentPlayer.getFigure(), chosenPoint);
                });
            }

        } else {
            logger.getLogger().severe("Message body error in handleStartingPointTaken method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of StartingPointTaken)");
        }
    }

    private void handleYourCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof YourCards) {
            YourCards yourCards = (YourCards) incomingMessage.getMessageBody();
            String[] cards = yourCards.getCards();
            int cardsInPile = yourCards.getCardsInPile();
            logger.getLogger().info(this.player.getName() + " has drew his cards.");

            //Initialize ProgrammingView with new cards
            programmingViewModel = new ProgrammingViewModel();
            programmingViewModel.setCards(cards);
            programmingViewModel.getProgrammingController().setPlayerValues(playerMatModel.getUserName().getValue(),player.getFigure());

            //Update MainView and switch scenes (PlayerMat -> ProgrammingView)
            mainViewModel.getMainViewController().setProgrammingPane(programmingViewModel.getProgrammingController().getGridPane());
            Platform.runLater(() -> {
                mainViewModel.switchScenes();
            });

            //Add info to PlayerModel
            Platform.runLater(() -> {
                playerMatModel.setDeckCount(cardsInPile);
            });

        } else {
            logger.getLogger().severe("Message body error in handleYourCards method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of YourCards)");
        }
    }

    private void handleNotYourCards(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof NotYourCards) {
            NotYourCards notYourCards = (NotYourCards) incomingMessage.getMessageBody();
            logger.getLogger().info("Other players have chosen their cards.");
        } else {
            logger.getLogger().severe("Message body error in handleNotYourCards method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of NotYourCards)");
        }
    }

    private void handleShuffleCoding(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof ShuffleCoding) {
            ShuffleCoding shuffleCoding = (ShuffleCoding) incomingMessage.getMessageBody();
            int playerID = shuffleCoding.getPlayerID();
            if (playerID == ID) {
                Platform.runLater(() -> {
                    playerMatModel.setDiscardedCount("0");
                });
            }
            logger.getLogger().info("Player shuffled deck, ID: " + playerID);
        } else {
            logger.getLogger().severe("Message body error in shuffleCoding method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of ShuffleCoding)");
        }
    }


    private void handleCardSelected(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CardSelected) {
            CardSelected cardSelected = (CardSelected) incomingMessage.getMessageBody();
            int playerID = cardSelected.getPlayerID();
            int register = cardSelected.getRegister();
            if (playerID == ID) {

                Platform.runLater(() -> {
                    programmingViewModel.confirmRegister(register);
                });

                logger.getLogger().info("Player with id " + playerID + " put a card in register " + register + ".");
            }

        } else {
            logger.getLogger().severe("Message body error in handleCardSelected method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CardSelected)");
        }
    }

    private void handleSelectionFinished(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof SelectionFinished) {
            SelectionFinished selectionFinished = (SelectionFinished) incomingMessage.getMessageBody();
            if (selectionFinished.getPlayerID() == ID) {
                playerMatModel.setFinishedSelection(true);
            }
            logger.getLogger().info(player.getName() + " finished his cards selection.");

        } else {
            logger.getLogger().severe("Message body error in handleSelectionFinished method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of SelectionFinished)");
        }
    }

    private void handleTimerStarted(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof TimerStarted) {

            programmingViewModel.setTimer();

            logger.getLogger().info("Timer started counting down.");
        } else {
            logger.getLogger().severe("Message body error in handleTimerStarted method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of TimerStarted)");
        }
    }

    private void handleTimerEnded(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof TimerEnded) {
            TimerEnded timerEnded = (TimerEnded) incomingMessage.getMessageBody();
            StringBuilder slowPlayers = new StringBuilder();
            for (Integer id : timerEnded.getPlayerIDs()) {
                if (id == ID) {
                    String robotFigure = Robot.getRobotName(playerMatModel.getFigure());
                    slowPlayers.append(robotFigure).append("\n");
                } else {
                    String robotFigure = Robot.getRobotName(enemyList.get(id).getFigure());
                    slowPlayers.append(robotFigure).append("\n");
                }
            }
            programmingViewModel.setSlowPlayers(slowPlayers.toString());

            Platform.runLater(() -> {
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
                        if (cards.getCard().equals("Spam") || cards.getCard().equals("Virus") || cards.getCard().equals("Worm") || cards.getCard().equals("TrojanHorse")) {
                            playerMatModel.decreaseDiscardedCount();
                        }
                    });
                } else {
                    Platform.runLater(() -> {
                        enemyList.get(cards.getPlayerID()).getEnemyMatController().setTakenRegister(cards.getCard());
                    });
                }
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
            int playerID = movement.getPlayerID();
            int destination = movement.getTo();
            int currentPosition;
            if (playerID == ID) {
                currentPosition = playerMatModel.getCurrentPosition();
                playerMatModel.setCurrentPosition(destination);
            } else {
                currentPosition = enemyList.get(playerID).getCurrentPosition();
                enemyList.get(playerID).setCurrentPosition(destination);
            }

            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().move(currentPosition, destination);
            });

            logger.getLogger().info("Player with id " + movement.getPlayerID() + " moved his robot to field number " + movement.getTo() + ".");
        } else {
            logger.getLogger().severe("Message body error in handleMovement method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Movement)");
        }
    }

    private void handleDrawDamage(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof DrawDamage) {
            DrawDamage drawDamage = (DrawDamage) incomingMessage.getMessageBody();
            int playerID = drawDamage.getPlayerID();
            String[] damageCards = drawDamage.getCards();

            clientGameState.decreaseDamageCardCount(damageCards);

            if (playerID == ID) {
                Platform.runLater(() -> {
                    playerMatModel.setPickedUpDamageCards(damageCards.length);
                });

            } else {
                Platform.runLater(() -> {
                enemyList.get(playerID).setPickedUpDamageCards(damageCards.length);
                });
            }

            logger.getLogger().info("Player with id " + drawDamage.getPlayerID() + " has drew damage cards.");
        } else {
            logger.getLogger().severe("Message body error in handleDrawDamage method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of DrawDamage)");
        }
    }


    private void handlePickDamage(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof PickDamage) {
            PickDamage pickDamage = (PickDamage) incomingMessage.getMessageBody();
            int count = pickDamage.getCount();
            LinkedList<String> availableCards = clientGameState.getAvailableDamageCards();
            Platform.runLater(() -> {
                PopupController popupController = new PopupController();
                popupController.showPickDamage(count, availableCards);
            });

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
            Platform.runLater(() -> {
                chatMessages.add("Robots are shooting!");
            });
            //      Append Chat with "Robots are shooting now"
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
            String direction = playerTurning.getDirection();
            int currentPosition;
            if (playerID == ID) {
                currentPosition = playerMatModel.getCurrentPosition();
                playerMatModel.setDirection(direction);
            } else {
                currentPosition = enemyList.get(playerID).getCurrentPosition();
            }

            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().playerTurning(currentPosition, direction);
            });

            logger.getLogger().info("Player with id " + playerTurning.getPlayerID() + " is turning his robot " + playerTurning.getDirection() + ".");
        } else {
            logger.getLogger().severe("Message body error in handlePlayerTurning method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of PlayerTurning)");
        }
    }

    private void handleEnergy(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof Energy) {
            Energy energy = (Energy) incomingMessage.getMessageBody();
            int playerID = energy.getPlayerID();
            int energyCount = energy.getCount();
            if (playerID == ID) {
                Platform.runLater(() -> {
                    playerMatModel.setEnergyPoints(energyCount);
                });
            } else {
                Platform.runLater(() -> {
                enemyList.get(playerID).setEnergyPoints(energyCount);
                });
            }

            logger.getLogger().info("Player with id " + energy.getPlayerID() + " has " + energy.getCount() + " energy cubes.");

            //ToDo: Update GUI Energy - maybe switch fields from full to empty?


        } else {
            logger.getLogger().severe("Message body error in handleEnergy method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of Energy)");
        }
    }

    private void handleCheckpointReached(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof CheckpointReached) {
            CheckpointReached checkpointReached = (CheckpointReached) incomingMessage.getMessageBody();
            int playerID = checkpointReached.getPlayerID();
            //todo Is this a number or a counter?
            int checkPoint = checkpointReached.getNumber();
            if (playerID == ID) {
                Platform.runLater(() -> {
                    playerMatModel.setCheckpointsreached(String.valueOf(checkPoint));
                });
            } else {
                Platform.runLater(() -> {
                    enemyList.get(playerID).setCheckpointsreached(String.valueOf(checkPoint));
                });
            }

            //ToDo: Visualize reached Checkpoint? Currently only written to PlayerModel

            logger.getLogger().info("Player with id " + checkpointReached.getPlayerID() + " has reached his " + checkpointReached.getNumber() + " checkpoint.");
        } else {
            logger.getLogger().severe("Message body error in handleCheckPointReached method.");
            throw new IOException("Something went wrong! Invalid Message Body! (Not instance of CheckpointReached)");
        }
    }

    /**
     * If a player has won the game, a final GameOverScreen with the winner's username is shown and the Game terminates
     * @param incomingMessage contains winner's player ID
     * @throws IOException if messageType is incorrect
     */
    private void handleGameWon(Message incomingMessage) throws IOException {
        if (incomingMessage.getMessageBody() instanceof GameWon) {
            GameWon gameWon = (GameWon) incomingMessage.getMessageBody();
            String winnerName = "The winner is: " + playerList.get(gameWon.getPlayerID()).getName();
            int winnerRobot  = playerList.get(gameWon.getPlayerID()).getFigure();
            //ToDo: Test and improve GameWon-Screen
            FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameOverScreen.fxml"));
            GridPane gameOverPane = gameOverLoader.load();
            GameOverModel gameOverModel = gameOverLoader.<GameOverController>getController().getGameOverModel();

            Platform.runLater(() -> {
                gameOverModel.setWinnerName(winnerName);
                gameOverModel.setWinnerRobot(winnerRobot);
                ViewController.getViewController().setScene(new Scene(gameOverPane));
            });

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

            if (secondIncomingMessage.getMessageType().equals("Welcome") && secondIncomingMessage.getMessageBody() instanceof Welcome) {

                Welcome receivedMessage = (Welcome) secondIncomingMessage.getMessageBody();

                this.ID = receivedMessage.getPlayerID();
                Platform.runLater(() -> {
                    chatMessages.add("Welcome to RoboRally by NeidischeNarwale! \n" +
                            "press the \"I am ready\"-Button to join the game.");
                });


            } else if (secondIncomingMessage.getMessageType().equals("Error") && secondIncomingMessage.getMessageBody() instanceof Error) {

                handleError(secondIncomingMessage);
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

    /**
     * Sends the chosen startingposition
     * @param position integer value (linear count of protocol positions) of chosen starting position
     */
    public void sendStartingPosition(int position) {
        String outgoingMessage = messageHandler.buildMessage("SetStartingPoint", new SetStartingPoint(position));
        outgoing.println(outgoingMessage);
    }

    /**
     * Sends each card that is put into a register during programming phase.
     * @param selectedCard the card put in the register (is "null" if register is undone)
     * @param register the respective register of the cardchoice
     */
    public void sendSelectedCard(String selectedCard, int register) {
        String outgoingMessage = messageHandler.buildMessage("SelectCard", new SelectCard(selectedCard, register));
        outgoing.println(outgoingMessage);
    }

    /**
     * Sends the user's chosen map to the server
     * @param userChoice String-Array (accoring to protocol) with length 1 containing the chosen map
     */
    public void sendSelectedMap(String[] userChoice) {
        String outgoingMessage = messageHandler.buildMessage("MapSelected", new MapSelected(userChoice));
        outgoing.println(outgoingMessage);
    }

    /**
     * Sends the user's choice of available damage cards (Worm, Virus or Trojan) to the server.
     * @param selectedDamageCards String-Array including the user's card choice(s)
     */
    public void sendSelectedDamage(String[] selectedDamageCards) {
        String outgoingMessage = messageHandler.buildMessage("SelectDamage", new SelectDamage(selectedDamageCards));
        outgoing.println(outgoingMessage);
    }

    /**
     * Transmits a "PlayIt"-message every time the user presses a register button during Activationphase
     * Called from PlayerMatController
     */
    public void sendPlayIt() {
        String outgoingMessage = messageHandler.buildMessage("PlayIt", new PlayIt());
        outgoing.println(outgoingMessage);
    }

    /**
     * Builds a GridPane for stats and cards of all other players and adds it to the MainView
     * Is called in handleGameStarted()-method since only then the final number of players is known.
     * @throws IOException if the FXML-File for the EnemyMat-View is not found
     */
    public void buildEnemyViews() throws IOException {
        GridPane enemyContainer = new GridPane();
        enemyContainer.setVgap(10);
        enemyContainer.setPadding(new Insets(10));
        int enemyCount = enemyIDList.size();

        for (int i = 0; i < enemyCount; i++) {

            FXMLLoader enemyMatLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/EnemyMat.fxml"));
            GridPane enemyMatPane = enemyMatLoader.load();
            int playerID = enemyIDList.get(i);

            EnemyMatModel enemyMatModel = enemyMatLoader.<EnemyMatController>getController().getEnemyMatModel();
            Player enemy = playerList.get(playerID);
            enemyMatModel.setPlayerValues(enemy);

            enemyList.put(playerID, enemyMatModel);
            enemyContainer.add(enemyMatPane, 0, i);
        }

        mainViewModel.getMainViewController().setEnemyPane(enemyContainer);
    }

    /**
     * Prepares and initializes the Main View of the Game
     * Loads the outer container as well as the ViewModels for the subviews
     * Does not initialize EnemyMats, since final number of players is not known yet
     *      -> Initialization of EnemyMats is performed in handleGameStarted()-method
     * @throws IOException if one of the used FXML-Files is not found
     */
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
