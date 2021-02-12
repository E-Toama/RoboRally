package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;
import utilities.MyLogger;

/**
 * This class represents the MoveII programming card with its effect.
 * 
 */
public class MoveTwo extends Move {
  
    private final MyLogger logger = new MyLogger();

    /**
     * Constructor for card name initialization.
     */
    public MoveTwo() {

        this.name = "MoveTwo";

    }

    /**
     * This method moves the robot of the player two field forward with the same direction its facing.
     * 
     * @param game 
     *          an object of the Game class
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
    @Override
    public void action(Game game, GameState gameState, int playerID) {
        logger.getLogger().info("The programming card Move II was played.");
        moveForward(game, gameState, playerID, false);

        if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

            moveForward(game, gameState, playerID, true);

        }

    }

}
