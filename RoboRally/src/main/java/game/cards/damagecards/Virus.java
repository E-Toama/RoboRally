package game.cards.damagecards;

import game.Game;
import game.cards.Card;
import game.player.PlayerMat;
import game.utilities.GameState;
import game.utilities.Position;

public class Virus extends Card {

    public Virus() {

        this.name = "Virus";

    }

    @Override
    public void action(Game game, GameState gameState, int playerID) {

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
