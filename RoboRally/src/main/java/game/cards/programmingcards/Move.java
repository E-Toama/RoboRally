package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;

/**
 * The abstract class Move extends the Card class and its a parent class for all the different types of move cards.
 * 
 */
public abstract class Move extends Card {
    
    private MoveHandler moveHandler = new MoveHandler();

    /**
     * This method moves the robot one time to the next field in the direction that he is facing.
     * 
     * @param game
     *          an object of the Game class
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     * @param isLastMovePart
     *          if the move is the last move
     */
    public void moveForward(Game game, GameState gameState, int playerID, boolean isLastMovePart) {

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();
        String orientation = gameState.playerMatHashMap.get(playerID).getRobot().getOrientation();

        switch (orientation) {
            case "up" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position, "up"), "up", true, isLastMovePart);
            case "left" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position,"left"), "left", true, isLastMovePart);
            case "down" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position, "down"), "down", true, isLastMovePart);
            case "right" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position, "right"), "right", true, isLastMovePart);
        }

    }

}