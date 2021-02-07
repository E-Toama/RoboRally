package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;

public class Again extends Card {

    public Again() {

        this.name = "Again";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        if (gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 2].getName().equals("Again")) {

            gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 3].action(game, gameState, playerID);

        } else {

            gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 2].action(game, gameState, playerID);

        }

    }

}
