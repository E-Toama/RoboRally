package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

public class Worm extends Card {

    private final MyLogger logger = new MyLogger();
  
    public Worm() {

        this.name = "Worm";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        logger.getLogger().info("The damage card Worm was played.");
        
        String[] wantedDamageCards = {"Spam", "Spam"};

        gameState.drawDamageCardHandler.drawDamageCards(playerID, wantedDamageCards);

        gameState.wormCards.add(this);

        gameState.playerMatHashMap.get(playerID).reboot(game, gameState, true);

    }

}
