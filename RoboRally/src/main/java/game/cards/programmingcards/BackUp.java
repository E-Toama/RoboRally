package game.cards.programmingcards;

import game.Game;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;

public class BackUp extends Move {

    public BackUp() {

        this.name = "BackUp";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

        MoveHandler moveHandler = new MoveHandler();

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();
        String orientation = gameState.playerMatHashMap.get(playerID).getRobot().getOrientation();

        switch (orientation) {
            case "up" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX(), position.getY() + 1), "down", true);
            case "left" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX() + 1, position.getY()), "right", true);
            case "down" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX(), position.getY() - 1), "up", true);
            case "right" -> moveHandler.move(game, gameState, playerID, position, new Position(position.getX() - 1, position.getY()), "left", true);
        }

    }

}
