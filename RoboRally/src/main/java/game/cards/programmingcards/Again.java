package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

/**
 * This class represents the Again programming card and its effect.
 *  
 * @author 
 */
public class Again extends Card {
  
    private final MyLogger logger = new MyLogger();

    /**
     * Constructor for card name initialization.
     */
    public Again() {

        this.name = "Again";

    }

    /**
     * This method plays effect of the card in the previous register again.
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
        logger.getLogger().info("The programming card Again was played.");

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 2].action(game, gameState, playerID);

    }

}
