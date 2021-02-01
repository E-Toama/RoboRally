package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;

public abstract class Move extends Card {
    
    private MoveHandler moveHandler = new MoveHandler();

    public void moveForward(Game game, GameState gameState, int playerID) {

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();
        String orientation = gameState.playerMatHashMap.get(playerID).getRobot().getOrientation();

        switch (orientation) {
            case "up" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position, "up"), "up", true);
            case "left" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position,"left"), "left", true);
            case "down" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position, "down"), "down", true);
            case "right" -> moveHandler.move(game, gameState, playerID, position, moveHandler.getTargetPosition(position, "right"), "right", true);
        }

    }

}