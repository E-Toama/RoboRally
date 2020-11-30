package model.gameV2;

import model.gameV2.cards.Card;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Player.
 * A Object of this Class stores the Player's username, his actual Hand, his discarded Cards, the number of his Wins an if he's protected.
 * Provides functionality for gameplay and access.
 *
 * @author Dennis, Josef, Ehbal
 */
public class Player implements Comparable<Player> {

    public final String userName;
    private List<Card> cards = new LinkedList<Card>();
    private List<Card> discardedCards = new LinkedList<Card>();
    private int winCounter = 0;
    private boolean isProtected = false;

    public Player(String userName) {

        this.userName = userName;

    }

    public int getWinCounter() {
        return winCounter;
    }

    public void setWinCounter() {
        this.winCounter = winCounter + 1;
    }

    public void addCard(Card card) {

        cards.add(card);

    }

    public void resetPlayer(Card card) {

        cards = new LinkedList<Card>();
        discardedCards = new LinkedList<Card>();
        cards.add(card);
        setProtected(false);

    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * Asks the Player what Card he want's to play.
     * Is always called when a new move starts.
     *
     * @param game  Current Game State
     * @param card  New Card that is drawn from the Deck
     */
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

    /**
     * Is called when the player holds the countess and King / Prince
     * Informs the Player what his Cards are, and that he is forced to play the Countess.
     *
     * @param game      Current Game State
     * @param index     Index of the Countess in the "cards" list
     */
    private void playCountess(Game game, int index) {

        game.server.sendMessageToSingleUser(userName, "Your Cards are: ");
        game.server.sendMessageToSingleUser(userName, "Left Card: " + cards.get(0).getName());
        game.server.sendMessageToSingleUser(userName, "Right Card: " + cards.get(1).getName());
        game.server.sendMessageToSingleUser(userName, "You are forced to play the Countess!");
        game.playCard(cards.remove(index), this);

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
