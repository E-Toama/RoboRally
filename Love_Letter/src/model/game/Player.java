package model.game;

import model.game.cards.Card;

import java.util.ArrayList;

public class Player {
    private boolean playing;
    private boolean immune;
    private ArrayList<Card> currentCards;
    private int score;
    private String name;

    public Player(String name) {
        currentCards = new ArrayList<>();
        score = 0;
        playing = true;
        immune = false;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Card> getCurrentCards() {
        return currentCards;
    }

    public boolean isImmune() {
        return immune;
    }

    public String displayCurrentCards() {
        StringBuilder sb = new StringBuilder();
        int cardIndex = 1;
        for (Card c : currentCards) {
            sb.append(cardIndex).append(": ").append(c.toString()).append("; ");
            cardIndex++;
        }
        return sb.toString();
    }

    public void addCurrentCard(Card card) {
        currentCards.add(card);
    }

    public void setPlaying(boolean b) {
        playing = b;
    }
}
