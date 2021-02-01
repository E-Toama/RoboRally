package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;

public class MoveThree extends Move {

    public MoveThree() {

        this.name = "MoveThree";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        for (int i = 0; i < 3; i++) {

            moveForward(game, gameState, playerID);

        }

    }

}
