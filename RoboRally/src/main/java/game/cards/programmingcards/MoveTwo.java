package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;

public class MoveTwo extends Move {

    public MoveTwo() {

        this.name = "MoveTwo";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        for (int i = 0; i < 2; i++) {

            moveForward(game, gameState, playerID);

        }

    }

}
