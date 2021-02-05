package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

public class Again extends Card {
  
    private final MyLogger logger = new MyLogger();

    public Again() {

        this.name = "Again";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {
        logger.getLogger().info("The programming card Again was played.");

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 2].action(game, gameState, playerID);

    }

}
