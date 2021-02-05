package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;
import utilities.messages.Energy;

public class PowerUp extends Card {
  
    private final MyLogger logger = new MyLogger();

    public PowerUp() {

        this.name = "PowerUp";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        logger.getLogger().info("The programming card Power Up was played.");
        gameState.playerMatHashMap.get(playerID).setEnergyCubes(gameState.playerMatHashMap.get(playerID).getEnergyCubes() + 1);

        String energy = messageHandler.buildMessage("Energy", new Energy(playerID, 1));
        gameState.server.sendMessageToAllUsers(energy);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
