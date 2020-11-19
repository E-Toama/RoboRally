package model.game;

import model.game.cards.Card;
import model.game.cards.Countess;
import model.game.cards.King;
import model.game.cards.Prince;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The Player class holds the status and progress of each player in the game.
 * @author Josef
 */
public class Player {
    private boolean playing;
    private boolean immune;
    private ArrayList<Card> currentCards;
    private ArrayList<Card> discardedCards;
    private int score;
    private String name;

    //Constructor, takes only a player name
    public Player(String name) {
        currentCards = new ArrayList<>();
        discardedCards = new ArrayList<>();
        score = 0;
        playing = true;
        immune = false;
        this.name = name;
    }


    public void reset() {
        playing = true;
        immune = false;
        currentCards = new ArrayList<>();
        discardedCards = new ArrayList<>();
    }


    //Getters and Setters for Player Info
    public String getName() {
        return this.name;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean b) {
        immune = b;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore() {
        this.score++;
    }

    public void addDiscardedCard(Card card) {
        discardedCards.add(card);
    }


    //Getters and Setters for Cards held by the player
    public ArrayList<Card> getCurrentCards() {
        return currentCards;
    }

    public String displayCurrentCards() {
        StringBuilder sb = new StringBuilder();
        int cardIndex = 1;
        for (Card c : currentCards) {
            sb.append(cardIndex).append(" - ").append(c.toString()).append("; ");
            cardIndex++;
        }
        return sb.toString();
    }

    public void addCardToCurrent(Card card) {
        currentCards.add(card);
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
     * Checks if player holds Countess AND King / Prince at the same time
     * @return true if player holds both cards and is forced to play the Countess
     */
    public boolean checkCountessPlusX() {
        boolean countessPlusKingOrQueen = false;
        for (Card c : currentCards) {
            if (c instanceof Countess) {
                for (Card otherCard : currentCards) {
                    if (otherCard instanceof King || otherCard instanceof Prince) {
                        countessPlusKingOrQueen = true;
                        break;
                    }
                }
            }
        }
        return countessPlusKingOrQueen;
    }

    /**
     * Simple equality-check based on name-property (name is guaranteed to be unique)
     * Needed to check if player tries to choose herself to apply a card effect
     * @param player to compare to
     * @return true if player is equal to himself
     */
    public boolean equals(Player player) {
        if (this == player) return true;
        return getName().equals(player.getName());
    }

}
