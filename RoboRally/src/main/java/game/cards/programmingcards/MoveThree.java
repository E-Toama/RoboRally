package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;

public class MoveThree extends Move {

    public MoveThree() {

        this.name = "MoveThree";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        moveForward(game, gameState, playerID, false);

        if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

            moveForward(game, gameState, playerID, false);

        }

        if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

            moveForward(game, gameState, playerID, true);

        }

    }

}
