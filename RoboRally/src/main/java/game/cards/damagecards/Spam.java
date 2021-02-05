package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

/**
 * This class extends the Card class and it represents the spam damage card.
 * 
 * @author 
 */
public class Spam extends Card {

    private final MyLogger logger = new MyLogger();
    
    /**
     * Constructor for initialization.
     */
    public Spam() {

        this.name = "Spam";

    }

    /**
     * This method puts the top card of the programming deck to the register where the spam card was.
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
        logger.getLogger().info("The card Spam was played.");

        Card newProgrammingCard = gameState.playerMatHashMap.get(playerID).drawRandomCard();

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1] = newProgrammingCard;

        gameState.spamCards.add(this);

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1].action(game, gameState, playerID);

    }

}
