package model.gameV2;

import model.gameV2.cards.Card;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Dennis, Josef, Ehbal
 */
public class Player implements Comparable<Player> {

    public final String userName;
    private List<Card> cards = new LinkedList<Card>();
    private List<Card> discardedCards = new LinkedList<Card>();
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

    /**
     * Checks if player holds Countess AND King / Prince at the same time
     * @return false if player holds both cards and is forced to play the Countess
     */
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

    public void addDiscardedCard(Card card) {
        discardedCards.add(card);
    }

    /**
     * "In case of a tie, players add the numbers on the cards in their discard pile. The highest total wins."
     * @return sum of values of the player's discarded cards
     */
    public int countDiscardedScore() {
        int result = 0;
        for (Card c : discardedCards) {
            result += c.getValue();
        }
        return result;
    }

    /**
     * Comparing players according to the game rules:
     * 1. Compare last card on hand. If tied:
     * 2. Compare sum total of discarded cards by the player
     * @param o other Player to compare to
     * @return negative int for smaller, zero for equal, positive int for bigger
     */
    @Override
    public int compareTo(Player o) {
        return Comparator.comparing((Player p)->p.cards.get(0).getValue())
                .thenComparing(Player::countDiscardedScore)
                .compare(this, o);
    }
}
