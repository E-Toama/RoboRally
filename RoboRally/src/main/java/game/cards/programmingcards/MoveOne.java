package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;

public class MoveOne extends Move {

    public MoveOne() {

        this.name = "MoveOne";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        moveForward(game, gameState, playerID, true);

    }

}
