package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.messages.PlayerTurning;

public class LeftTurn extends Card {

    public LeftTurn() {

        this.name = "LeftTurn";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).getRobot().turnLeft();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "counterClockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
