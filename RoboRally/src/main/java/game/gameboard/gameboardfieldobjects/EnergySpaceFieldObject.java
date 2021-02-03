package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.messages.Energy;

public class EnergySpaceFieldObject extends GameBoardFieldObject {

    private final int count;

    public EnergySpaceFieldObject(int count) {
        super("EnergySpace");
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void activate(Game game, GameState gameState, int playerID) {

        if (count == 0) {

            gameState.playerMatHashMap.get(playerID).setEnergyCubes(gameState.playerMatHashMap.get(playerID).getEnergyCubes() + 1);

            String energy = messageHandler.buildMessage("Energy", new Energy(playerID, 1));
            gameState.server.sendMessageToAllUsers(energy);

        } else {

            gameState.playerMatHashMap.get(playerID).setEnergyCubes(gameState.playerMatHashMap.get(playerID).getEnergyCubes() + count);

            String energy = messageHandler.buildMessage("Energy", new Energy(playerID, count));
            gameState.server.sendMessageToAllUsers(energy);

        }

    }
}
