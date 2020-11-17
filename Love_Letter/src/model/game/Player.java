package model.game;

import model.game.cards.Card;

import java.util.ArrayList;

public class Player {
    private boolean playing;
    private boolean immune;
    private ArrayList<Card> currentCards;
    private int score; //not used yet
    private String name;

    //Constructor, takes only a player name
    public Player(String name) {
        currentCards = new ArrayList<>();
        score = 0;
        playing = true;
        immune = false;
        this.name = name;
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

}
