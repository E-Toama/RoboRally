package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;
import utilities.MyLogger;

public class MoveOne extends Move {

    private final MyLogger logger = new MyLogger();
  
    public MoveOne() {

        this.name = "MoveOne";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {
        logger.getLogger().info("The programming card Move I was played.");

        moveForward(game, gameState, playerID, true);

    }

}
