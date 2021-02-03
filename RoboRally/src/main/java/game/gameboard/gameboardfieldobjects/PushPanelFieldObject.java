package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import game.utilities.MoveHandler;
import game.utilities.Position;

public class PushPanelFieldObject extends GameBoardFieldObject{

    private final String orientation;
    private final int[] registers;

    public PushPanelFieldObject(String orientation, int[] registers) {
        super("PushPanel");
        this.orientation = orientation;
        this.registers = registers;
    }

    public String getOrientation() {
        return orientation;
    }

    public int[] getRegisters() {
        return registers;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

        MoveHandler moveHandler = new MoveHandler();

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();
        Position newPosition = moveHandler.getTargetPosition(position, orientation);

        moveHandler.move(game, gameState, playerID, position, newPosition, orientation, false, true);

    }
}
