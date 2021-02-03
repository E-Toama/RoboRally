package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.messages.PlayerTurning;

public class GearFieldObject extends GameBoardFieldObject {

    private final String orientation;

    public GearFieldObject(String orientation) {
        super("Gear");
        this.orientation = orientation;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

        if (orientation.equals("right")) {

            turnClockwise(gameState, playerID);

        } else {

            turnCounterClockwise(gameState, playerID);

        }

    }

    private void turnClockwise(GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "clockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

    }

    private void turnCounterClockwise(GameState gameState, int playerID) {

        gameState.playerMatHashMap.get(playerID).getRobot().turnLeft();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "counterClockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

    }

    public String getOrientation() {
        return orientation;
    }

}
