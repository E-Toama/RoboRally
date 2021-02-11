package client.utilities;

import client.network.ClientThread;
import client.viewmodel.GameBoardViewModel;
import client.viewmodel.PlayerMatModel;
import javafx.application.Platform;

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
                    playerMatModel.setEnergyPoints(5);
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

}
