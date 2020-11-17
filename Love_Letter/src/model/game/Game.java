package model.game;

import model.game.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    ArrayList<Player> players;
    ArrayList<Card> deck;
    ArrayList<Card> discardedCards;
    boolean isWon;
    Player playerOne;
    Player playerTwo;

    public Game() {
        //Create Deck
        deck = new ArrayList<>();
        discardedCards = new ArrayList<>();
        initializeDeck();
        Collections.shuffle(deck);
        System.out.println("All Cards: ");
        for (Card c : deck) {
            System.out.println(c.toString());
        }
        //Create Players
        players = new ArrayList<>();
        playerOne = new Player("Dr. Mabuse");
        playerTwo = new Player("Phil Hellmuth");
        players.add(playerOne);
        players.add(playerTwo);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }

    private void start() {
        //Put aside 3 cards from the deck
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));

        //Deal first card to all players
        drawCard(playerOne);
        drawCard(playerTwo);
        System.out.println("Player ONE CARDS");
        System.out.println(playerOne.displayCurrentCards());
        System.out.println("Player TWO CARDS");
        System.out.println(playerTwo.displayCurrentCards());

        //Play game
        play();

    }

    private void play() {
        Scanner input = new Scanner(System.in);
        while (!isWon) {
            drawCard(playerOne);
            System.out.println(playerOne.displayCurrentCards());
            System.out.println("Which card do you want to play? Enter the number");
            int cardPosition = Integer.parseInt(input.nextLine());
            Card playedCard = playerOne.getCurrentCards().get(cardPosition-1);
            System.out.println("Played card: " + playedCard.toString());
            handleCardAction(playerOne, playedCard);
            discardedCards.add(playedCard);

        }
    }

    private void handleCardAction(Player player, Card card) {
        if (card instanceof Princess) {
            player.setPlaying(false);
            isWon = true;
        } else if (card instanceof Countess) {
            //do nothing
            System.out.println("A Countess was played");
        }else if (card instanceof King) {
            System.out.println("Choose player to trade cards with");
            showPlayers();
            Scanner input = new Scanner(System.in);
            int chosenPlayerIndex = Integer.parseInt(input.nextLine());
            Player chosenPlayer = players.get(chosenPlayerIndex-1);
            Card chosenPlayersCard = chosenPlayer.getCurrentCards().remove(0);
            player.getCurrentCards().add(chosenPlayersCard);
            chosenPlayersCard = player.getCurrentCards().remove(0);
            chosenPlayer.getCurrentCards().add(chosenPlayersCard);

        } else if (card instanceof Prince) {
            //do something
            System.out.println("A Prince was played");
        } else if (card instanceof Handmaid) {
            //do something
            System.out.println("A Handmaid was played");
        } else if (card instanceof Baron) {
            //do something
            System.out.println("A Baron was played");
        } else if (card instanceof Priest) {
            //do something
            System.out.println("A Priest was played");
        } else if (card instanceof Guard) {
            //do something
            System.out.println("A Guard was played");
        }

    }

    private void showPlayers() {
        int playerIndex = 1;
        for (Player p : players) {
            System.out.print(playerIndex + ": " + p.getName());
            if (p.isImmune()) {
                System.out.println(" (immune)");
            }
            System.out.println();
            playerIndex++;
        }
    }


    private void drawCard(Player player) {
        player.addCurrentCard(deck.remove(0));
    }

    public void showDiscardedCards() {
        System.out.println("DISCARDED CARDS");
        for (Card c : discardedCards) {
            System.out.println(c.toString());
        }
    }


    private void initializeDeck() {
        deck = new ArrayList<>();
        deck.add(new Princess());
        deck.add(new Countess());
        deck.add(new King());
        deck.add(new Prince());
        deck.add(new Prince());
        deck.add(new Handmaid());
        deck.add(new Handmaid());
        deck.add(new Baron());
        deck.add(new Baron());
        deck.add(new Priest());
        deck.add(new Priest());
        for (int i = 0; i < 5; i++) {
            deck.add(new Guard());
        }
    }
    
}
