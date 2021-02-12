package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.utilities.GameState;
import utilities.MyLogger;

/**
 * This class represents the Worm damage card and its effect.
 * 
 */
public class Worm extends Card {

    private final MyLogger logger = new MyLogger();
  
    /**
     * Constructor for card name initialization.
     */
    public Worm() {

        this.name = "Worm";

    }

    /**
     * The method makes the player take two spam cards and then the robot of the player reboots.
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

        gameState.wormCards.add(this);

        gameState.playerMatHashMap.get(playerID).reboot(game, gameState, true);

    }

}
