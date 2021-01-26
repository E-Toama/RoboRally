package game;

import game.gameboard.GameBoard;
import game.player.Player;
import game.player.PlayerMat;
import game.utilities.GameState;
import server.network.Server;
import utilities.MessageHandler;
import utilities.messages.ActivePhase;
import utilities.messages.CurrentPlayer;

import java.util.List;

public class Game {

    public Server server;
    private final MessageHandler messageHandler = new MessageHandler();

    private final GameBoard gameBoard = new GameBoard("ExtraCrispy");
    private final GameState gameState;

    public Game(Server server, List<Player> playerList) {

        this.server = server;
        this.gameState = new GameState(playerList, server);

    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void startGame() {

        setActivePhase(0);

        startStaringPointSelection();

    }

    public void setActivePhase(int newActivePhase) {

        gameState.activePhase = newActivePhase;

        String activePhaseMessage = messageHandler.buildMessage("ActivePhase", new ActivePhase(gameState.activePhase));
        server.sendMessageToAllUsers(activePhaseMessage);

    }

    public void startStaringPointSelection() {

        String currentPlayerMessage = messageHandler.buildMessage("CurrentPlayer", new CurrentPlayer(server.getPlayerList().get(0).getPlayerID()));
        server.sendMessageToAllUsers(currentPlayerMessage);

    }

    public void continueStartingPointSelection(Player player, int position) {

        gameState.playerMatList.get(player.getPlayerID()).getRobot().setRobotPosition(position);

        if (server.getPlayerList().get(server.getPlayerList().size() - 1).getPlayerID() == player.getPlayerID()) {

            firstProgrammingPhase();

        } else {

            int newPlayerIDIndex = server.getPlayerList().indexOf(player) + 1;
            String currentPlayerMessage = messageHandler.buildMessage("CurrentPlayer", new CurrentPlayer(server.getPlayerList().get(newPlayerIDIndex).getPlayerID()));
            server.sendMessageToAllUsers(currentPlayerMessage);

        }

    }

    public void firstProgrammingPhase() {

        setActivePhase(2);

        for (PlayerMat playerMat : gameState.playerMatList) {
            playerMat.initializeDeck();
            server.handOutCards(playerMat.getPlayer().getPlayerID(), playerMat.drawNineCards(), playerMat.getDeckCount());
        }

    }

    public void programmingPhase() {

        setActivePhase(2);

        for (PlayerMat playerMat : gameState.playerMatList) {
            server.handOutCards(playerMat.getPlayer().getPlayerID(), playerMat.drawNineCards(), playerMat.getDeckCount());
        }

    }

}
