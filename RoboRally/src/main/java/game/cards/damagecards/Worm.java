package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;

public class Worm extends Card {

    public Worm() {

        this.name = "Worm";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        gameState.wormCards.add(this);

        gameState.playerMatHashMap.get(playerID).reboot(game, gameState, true);

    }

}
