package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;

public class Spam extends Card {

    public Spam() {

        this.name = "Spam";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {


        Card newProgrammingCard = gameState.playerMatHashMap.get(playerID).drawRandomCard();

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1] = newProgrammingCard;

        gameState.spamCards.add(this);

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1].action(game, gameState, playerID);

    }

}
