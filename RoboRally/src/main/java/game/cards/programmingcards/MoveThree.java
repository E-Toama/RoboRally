package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;
import utilities.MyLogger;

public class MoveThree extends Move {
  
    private final MyLogger logger = new MyLogger();

    public MoveThree() {

        this.name = "MoveThree";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        logger.getLogger().info("The programming card Move III was played.");
        moveForward(game, gameState, playerID, false);

        if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

            moveForward(game, gameState, playerID, false);

        }

        if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

            moveForward(game, gameState, playerID, true);

        }

    }

}
