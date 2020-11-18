package model.game;

import model.game.cards.Card;
import model.game.cards.Countess;
import model.game.cards.King;
import model.game.cards.Prince;

import java.util.ArrayList;
import java.util.Objects;

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

    public int countDiscardedScore() {
        int result = 0;
        for (Card c : discardedCards) {
            result += c.getValue();
        }
        return result;
    }

    public boolean checkCountessPlusX() {
        boolean countessPlusKingOrQueen = false;
        for (Card c : currentCards) {
            if (c instanceof Countess) {
                for (Card otherCard : currentCards) {
                    if (otherCard instanceof King || otherCard instanceof Prince) {
                        countessPlusKingOrQueen = true;
                    }
                }
            }
        }
        return countessPlusKingOrQueen;
    }

    public int getCountessIndex() {
        return currentCards.get(0) instanceof Countess ? 0 : 1;
    }

    public boolean equals(Player p) {
        if (this == p) return true;
        return getName().equals(p.getName());
    }

}
