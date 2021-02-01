package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.messages.PlayerTurning;

public class UTurn extends Card {

    public UTurn() {

        this.name = "U-Turn";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();
        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "clockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);
        gameState.server.sendMessageToAllUsers(playerTurning);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
