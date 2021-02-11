package client.utilities;

import client.network.ClientThread;
import client.view.GameOverController;
import client.view.ViewController;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.GameOverModel;
import client.viewmodel.PlayerMatModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import utilities.MyLogger;

import java.io.IOException;

/**
 * This class provides some simple, string-based cheats for GUI-Manipulation.
 * It is used directly in the ChatController and only active during the ProgrammingPhase (Phase 2)
 * Whenever a user sends a message, the CheatModule parses the message for cheat commands and handles the cheat request.
 */
public class CheatModule {

    private ClientThread clientThread;
    private GameBoardViewModel gameBoardViewModel;
    private PlayerMatModel playerMatModel;

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
    }


    /**
     * Simple Sring-Check for special characters signaling cheats
     *
     * @param message The message the user wants to send via chat
     */
    public void parseCheats(String message) {
        String cheatcode = "";
        if (message.startsWith("##")) {
            cheatcode = message.substring(2);
            switch (cheatcode) {
                case "coke":
                    playerMatModel.setEnergyPoints(1);
                    break;
                case "cocaine":
                    playerMatModel.setEnergyPoints(6);
                    break;
                case "tarnkappe":
                    tarnkappe();
                    break;
                case "reappear":
                    reappear();
                    break;
                case "whirlwind":
                    whirlwind();
                    break;
                case "skyisthelimit":
                    skyIsTheLimit();
                    break;
                case "pieceofcake":
                    pieceOfCake();
                default:
                    break;
            }
        }
    }


    private void whirlwind() {
        Platform.runLater(() -> {
            gameBoardViewModel.getGameBoardController().whirlwind(playerMatModel.getCurrentPosition());
        });
    }

    private void skyIsTheLimit() {
        Platform.runLater(() -> {
            gameBoardViewModel.getGameBoardController().skyIsTheLimit(playerMatModel.getCurrentPosition());
        });
    }

    private void tarnkappe() {
        Platform.runLater(() -> {
            gameBoardViewModel.getGameBoardController().invisible(playerMatModel.getCurrentPosition());
        });

    }

    private void reappear() {
        Platform.runLater(() -> {
            gameBoardViewModel.getGameBoardController().reappear(playerMatModel.getCurrentPosition());
        });
    }

    private void pieceOfCake() {

        try {
            FXMLLoader gameOverLoader = new FXMLLoader(getClass().getResource("/FXMLFiles/GameOverScreen.fxml"));
            GridPane gameOverPane = gameOverLoader.load();
            GameOverModel gameOverModel = gameOverLoader.<GameOverController>getController().getGameOverModel();

            Platform.runLater(() -> {
                gameOverModel.setWinnerName("Winner by old-fashioned cheating: " + playerMatModel.getUserName().getValue());
                ViewController.getViewController().setScene(new Scene(gameOverPane));
            });
        } catch (IOException e) {
            MyLogger logger = new MyLogger();
            logger.getLogger().severe(e.getMessage());
        }
    }

}
