package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

public class TrojanHorse extends Card {
    private final MyLogger logger = new MyLogger();

    public TrojanHorse() {

        this.name = "TrojanHorse";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        logger.getLogger().info("The damage card Trojan Horse was played.");
      
        Card newProgrammingCard = gameState.playerMatHashMap.get(playerID).drawRandomCard();

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1] = newProgrammingCard;

        String[] wantedDamageCards = {"Spam", "Spam"};

        gameState.drawDamageCardHandler.drawDamageCards(playerID, wantedDamageCards);

        gameState.trojanHorseCards.add(this);

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1].action(game, gameState, playerID);

    }

}
