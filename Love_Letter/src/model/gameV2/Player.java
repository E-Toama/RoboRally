package model.gameV2;

import model.gameV2.cards.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Dennis, Josef, Ehbal
 */
public class Player {

    public final String userName;
    private List<Card> cards = new LinkedList<Card>();
    private int wins = 0;
    private boolean isProtected = false;

    public Player(String userName) {

        this.userName = userName;

    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        this.wins = wins + 1;
    }

    public void addCard(Card card) {

        cards.add(card);

    }

    public List<Card> getCards() {
        return cards;
    }

    public void requestAction(Game game, Card card) {

        setProtected(false);
        cards.add(card);

        if (checkCountessCondition(game)) {

            game.server.sendMessageToSingleUser(userName, "Your Cards are: ");
            game.server.sendMessageToSingleUser(userName, "Left Card: " + cards.get(0).getName());
            game.server.sendMessageToSingleUser(userName, "Right Card: " + cards.get(1).getName());
            game.server.sendMessageToSingleUser(userName, "What card do you want to play ?");
            game.server.sendMessageToSingleUser(userName, "Use !PLAYLEFTCARD or !PLAYRIGHTCARD !");

        }

    }

    private boolean checkCountessCondition(Game game) {

        if (cards.get(0).getValue() == 7) {

            if (cards.get(1).getValue() == 6 || cards.get(1).getValue() == 5) {

                playCountess(game,0);
                return false;

            } else {

                return true;

            }

        } else if (cards.get(1).getValue() == 7){

           if (cards.get(0).getValue() == 6 || cards.get(0).getValue() == 5) {

               playCountess(game, 1);
               return false;

           } else {

               return true;

           }

        } else {

            return true;

        }

    }

    private void playCountess(Game game, int index) {

        game.server.sendMessageToSingleUser(userName, "Your Cards are: ");
        game.server.sendMessageToSingleUser(userName, "Left Card: " + cards.get(0).getName());
        game.server.sendMessageToSingleUser(userName, "Right Card: " + cards.get(1).getName());
        game.server.sendMessageToSingleUser(userName, "You are forced to play the Countess!");
        game.playCard(cards.remove(index), this);

    }

    public boolean getIsProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public boolean isProtected() {
        return isProtected;
    }
}
