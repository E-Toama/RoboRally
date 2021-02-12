package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;
import utilities.messages.Energy;

/**
 * This class represents the Powerup programming card and its effect.
 *  
 */
public class PowerUp extends Card {
  
    private final MyLogger logger = new MyLogger();

    /**
     * Constructor for card name initialization.
     */
    public PowerUp() {

        this.name = "PowerUp";

    }

    /**
     * This method gives the player one energy cube.
     * 
     * @param game 
     *          an object of the Game class
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
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
