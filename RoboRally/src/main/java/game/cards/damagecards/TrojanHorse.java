package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

/**
 * This class extends the Card class and it represents the Trojan Horse damage card.
 * 
 * @author 
 */
public class TrojanHorse extends Card {
    private final MyLogger logger = new MyLogger();

    /**
     * Constructor for card name initialization.
     */
    public TrojanHorse() {

        this.name = "TrojanHorse";

    }

    /**
     * This method lets the player take two spam damage cards and put the first card in his 
     * programming deck in the same register of the Trojan Horse card.
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

        logger.getLogger().info("The damage card Trojan Horse was played.");
      
        Card newProgrammingCard = gameState.playerMatHashMap.get(playerID).drawRandomCardForDamageCardAction(gameState.register);

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1] = newProgrammingCard;

        String[] wantedDamageCards = {"Spam", "Spam"};

        gameState.drawDamageCardHandler.drawDamageCards(playerID, wantedDamageCards);

        gameState.trojanHorseCards.add(this);

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1].action(game, gameState, playerID);

    }

}
