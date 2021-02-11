package game.gameboard.gameboardfieldobjects;

import game.Game;
import game.utilities.GameState;
import utilities.messages.Energy;

/**
 * This class represents the Energy Space board element and its effect once stepped on.
 * 
 * @author 
 */
public class EnergySpaceFieldObject extends GameBoardFieldObject {

    private final int count;

    /**
     * Constructor for initialization of the name and number of energy cubes the energy space board element has.
     * 
     * @param count 
     *          the number of energy cubes on the energy space field
     */
    public EnergySpaceFieldObject(int count) {
        super("EnergySpace");
        this.count = count;
    }

    /**
     * The method returns the number of energy cubes that the space energy field has.
     * 
     * @return the number of the energy cubes on the field.
     */
    public int getCount() {
        return count;
    }

    /**
     * This method handles the effect of stepping on an energy space field, which gives the player an energy cube if there is one.
     * 
     * @param game
     *          an object of the Game class
     * @param gameState
     *          an object of the GameState class
     * @param playerID
     *          the player id
     */
    @Override
    public void activate(Game game, GameState gameState, int playerID) {

        if (count == 0) {

            if (gameState.register == 5) {

                gameState.playerMatHashMap.get(playerID).setEnergyCubes(gameState.playerMatHashMap.get(playerID).getEnergyCubes() + 1);

                String energy = messageHandler.buildMessage("Energy", new Energy(playerID, 1));
                gameState.server.sendMessageToAllUsers(energy);

            }

        } else {

            gameState.playerMatHashMap.get(playerID).setEnergyCubes(gameState.playerMatHashMap.get(playerID).getEnergyCubes() + count);

            String energy = messageHandler.buildMessage("Energy", new Energy(playerID, count));
            gameState.server.sendMessageToAllUsers(energy);

        }

    }
}
