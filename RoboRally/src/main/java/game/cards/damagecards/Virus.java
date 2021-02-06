package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.player.PlayerMat;
import game.utilities.GameState;
import game.utilities.Position;
import utilities.MyLogger;

/**
 * This class represents the Virus damage card with its effect.
 * 
 * @author 
 */
public class Virus extends Card {

    private final MyLogger logger = new MyLogger();
    
    /**
     * Constructor for card name initialization.
     */
    public Virus() {

        this.name = "Virus";

    }

    /**
     * The method implements the effect of the Virus damage card which is, all the players within a radius of 6
     * from the player who played the virus card must also draw a virus card, then the player draws the top card 
     * of his programming deck and puts it on the register of the Virus card.
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
        logger.getLogger().info("The Virus damage card was played.");

        Position position = gameState.playerMatHashMap.get(playerID).getRobot().getRobotXY();

        for (PlayerMat playerMat : gameState.playerMatList) {

            if (Position.calculateDistance(position, playerMat.getRobot().getRobotXY()) <= 6 && playerMat.getPlayer().getPlayerID() != playerID) {

                String[] wantedDamageCards = {"Virus"};

                gameState.drawDamageCardHandler.drawDamageCards(playerID, wantedDamageCards);

            }

        }

        Card newProgrammingCard = gameState.playerMatHashMap.get(playerID).drawRandomCard();

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1] = newProgrammingCard;

        gameState.virusCards.add(this);

        gameState.playerMatHashMap.get(playerID).getRegister()[gameState.register - 1].action(game, gameState, playerID);

    }

}
