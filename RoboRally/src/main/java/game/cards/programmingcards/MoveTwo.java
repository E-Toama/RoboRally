package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;

public class MoveTwo extends Move {

    public MoveTwo() {

        this.name = "MoveTwo";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        moveForward(game, gameState, playerID, false);

        if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

            moveForward(game, gameState, playerID, true);

        }

    }

}
