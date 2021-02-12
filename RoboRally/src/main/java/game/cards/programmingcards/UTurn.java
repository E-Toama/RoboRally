package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;
import utilities.messages.PlayerTurning;

/**
 * This class represents the U-Turn programming card and its effect when played.
 * 
 */
public class UTurn extends Card {

    private final MyLogger logger = new MyLogger();
  
    /**
     * Constructor for card name initialization.
     */
    public UTurn() {

        this.name = "U-Turn";

    }

    /**
     * This method makes the robot turn 180 degrees from the direction its facing without leaving his position.
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
        logger.getLogger().info("The programming card U-Turn was played.");
        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();
        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "clockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);
        gameState.server.sendMessageToAllUsers(playerTurning);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
