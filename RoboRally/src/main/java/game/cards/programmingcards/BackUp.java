package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;
import utilities.MyLogger;

/**
 * This class represents the Back up programming card and its effect.
 * 
 */
public class BackUp extends Move {

    private final MyLogger logger = new MyLogger();
  
    /**
     * Constructor for card name initialization.
     */
    public BackUp() {

        this.name = "BackUp";

    }

    /**
     * This method makes the robot of the player goes one step back without changing the direction its facing.
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

        logger.getLogger().info("The programming card Backup was played.");
        
        MoveHandler moveHandler = new MoveHandler();

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();
        String orientation = gameState.playerMatHashMap.get(playerID).getRobot().getOrientation();

        switch (orientation) {
            case "up" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX(), position.getY() + 1), "down", true, true);
            case "left" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX() + 1, position.getY()), "right", true, true);
            case "down" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX(), position.getY() - 1), "up", true, true);
            case "right" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX() - 1, position.getY()), "left", true, true);
        }

    }

}
