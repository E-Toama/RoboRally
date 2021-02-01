package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.messages.Energy;

public class PowerUp extends Card {

    public PowerUp() {

        this.name = "PowerUp";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).setEnergyCubes(gameState.playerMatHashMap.get(playerID).getEnergyCubes() + 1);

        String energy = messageHandler.buildMessage("Energy", new Energy(playerID, 1));
        gameState.server.sendMessageToAllUsers(energy);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
