package client.utilities;

import client.network.ClientThread;
import client.view.GameOverController;
import client.view.ViewController;
import client.viewmodel.EnemyMatModel;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.GameOverModel;
import client.viewmodel.PlayerMatModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import utilities.MyLogger;
import utilities.messages.Message;
import utilities.messages.ReceivedChat;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class provides some simple, string-based cheats for GUI-Manipulation.
 * It is used directly in the ChatController and only active during the ProgrammingPhase (Phase 2)
 * Whenever a user sends a message, the CheatModule parses the message for cheat commands and handles the cheat request.
 * @author Josef
 */
public class CheatModule {

    private ClientThread clientThread;
    private GameBoardViewModel gameBoardViewModel;
    private PlayerMatModel playerMatModel;
    private HashMap<Integer, EnemyMatModel> enemyList;

    /**
     * Constructor of the CheatModule initializes
     * Clienthread (needed to access the ViewModels)
     * GameBoardViewModel for manipulating the gameboard
     * PlayerMatModel for manipulating the player's stats
     */
    public CheatModule() {
        clientThread = ClientThread.getInstance();
        gameBoardViewModel = clientThread.getGameBoardViewModel();
        playerMatModel = clientThread.getPlayerMatModel();
        enemyList = clientThread.getEnemyList();
    }


    /**
     * Simple Sring-Check for special characters signaling cheats
     * @param receivedChat incoming message
     */
    public void parseCheats(ReceivedChat receivedChat) {
        boolean isPrivate = receivedChat.getPrivate();
        String message = receivedChat.getMessage();
        int idOfSender = receivedChat.getFrom();

        String cheatcode = "";
        if (message.startsWith("##")) {
            cheatcode = message.substring(2);
            switch (cheatcode) {
                case "coke":
                    increaseEnergyPoints(idOfSender, 1);
                    break;
                case "cocaine":
                    increaseEnergyPoints(idOfSender, 6);
                    break;
                case "tarnkappe":
                    tarnkappe(idOfSender);
                    break;
                case "reappear":
                    reappear(idOfSender);
                    break;
                case "whirlwind":
                    whirlwind(idOfSender);
                    break;
                case "skyisthelimit":
                    skyIsTheLimit(idOfSender);
                    break;
                case "pieceofcake":
                    pieceOfCake(idOfSender);
                default:
                    break;
            }
        }
    }

    private void increaseEnergyPoints(int playerID, int amount) {
        if (playerID == playerMatModel.getPlayerID()) {
            Platform.runLater(() -> {
                playerMatModel.setEnergyPoints(amount);
            });
        } else {
            Platform.runLater(() -> {
                enemyList.get(playerID).setEnergyPoints(amount);
            });
        }
    }


    private void whirlwind(int playerID) {
        if (playerID == playerMatModel.getPlayerID()) {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().whirlwind(playerMatModel.getCurrentPosition());
            });
        } else {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().whirlwind(enemyList.get(playerID).getCurrentPosition());
            });
        }
    }

    private void skyIsTheLimit(int playerID) {
        if (playerID == playerMatModel.getPlayerID()) {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().skyIsTheLimit(playerMatModel.getCurrentPosition());
            });
        } else {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().skyIsTheLimit(enemyList.get(playerID).getCurrentPosition());
            });
        }
    }

    private void tarnkappe(int playerID) {
        if (playerID == playerMatModel.getPlayerID()) {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().invisible(playerMatModel.getCurrentPosition());
            });
        } else {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().invisible(enemyList.get(playerID).getCurrentPosition());
            });
        }

    }

    private void reappear(int playerID) {
        if (playerID == playerMatModel.getPlayerID()) {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().reappear(playerMatModel.getCurrentPosition());
            });
        } else {
            Platform.runLater(() -> {
                gameBoardViewModel.getGameBoardController().reappear(enemyList.get(playerID).getCurrentPosition());
            });
        }
    }

    private void pieceOfCake(int playerID) {
        try {
            FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameOverScreen.fxml"));
            GridPane gameOverPane = gameOverLoader.load();
            GameOverModel gameOverModel = gameOverLoader.<GameOverController>getController().getGameOverModel();
            if (playerID == playerMatModel.getPlayerID()) {
                Platform.runLater(() -> {
                    gameOverModel.setWinnerName("Winner by old-fashioned cheating: " + playerMatModel.getUserName().getValue());
                    ViewController.getViewController().setScene(new Scene(gameOverPane));
                });
            }
        } catch (IOException e) {
            MyLogger logger = new MyLogger();
            logger.getLogger().severe(e.getMessage());
        }
    }

}
