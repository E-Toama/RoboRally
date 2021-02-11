package game.cards.programmingcards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;
import utilities.messages.PlayerTurning;

/**
 * This class represents the Right Turn programming card with its implementation.
 * 
 * @author 
 */
public class RightTurn extends Card {

    private final MyLogger logger = new MyLogger();
  
    /**
     * Constructor for card name initialization.
     */
    public RightTurn() {

        this.name = "RightTurn";

    }

    /**
     * This method turns the robot of the player to the right direction from where its facing.
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
        
        logger.getLogger().info("The programming card Right turn was played.");
        gameState.playerMatHashMap.get(playerID).getRobot().turnRight();

        String playerTurning = messageHandler.buildMessage("PlayerTurning", new PlayerTurning(playerID, "clockwise"));
        gameState.server.sendMessageToAllUsers(playerTurning);

        gameState.nextRegisterList.add(gameState.registerList.remove(0));
        game.nextPlayersTurn();

    }

}
