package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;
import utilities.messages.PlayerTurning;

/**
 * This class represents the Left Turn programming card and its effect on the robot.
 * 
 */
public class LeftTurn extends Card {
  
    private final MyLogger logger = new MyLogger();

    /**
     * Constructor for card name initialization.
     */
    public LeftTurn() {

        this.name = "LeftTurn";

    }

    /**
     * This method makes the robot change its looking direction to his left.
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
        
        logger.getLogger().info("The programming card Left Turn was played.");

        gameState.playerMatHashMap.get(playerID).getRobot().turnLeft();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "counterClockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
