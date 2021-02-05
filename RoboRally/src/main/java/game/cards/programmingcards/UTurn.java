package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;
import utilities.messages.PlayerTurning;

public class UTurn extends Card {

    private final MyLogger logger = new MyLogger();
  
    public UTurn() {

        this.name = "U-Turn";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {
        logger.getLogger().info("The programming card U-Turn was played.");
        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();
        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "clockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);
        gameState.server.sendMessageToAllUsers(playerTurning);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
