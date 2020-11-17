package model.game;

import model.game.cards.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Very basic implementation of the game logic for exactly two players.
 * All is happening inside ONE console window, input of Cards to play and opponents to choose
 * is realized with number-input from the console (via Scanner).
 *
 * Only one round is played, scoring is not implemented yet.
 * All the actions involving a second player (compare, swap, look at cards) can currently be performed with oneself, too.
 * No error-cases are caught! Especially IndexOutOfBounds for the ArrayLists.
 * The effect of having Countess + King/Prince is not implemented yet.
 * Bonus features: Bugs, Flaws, Unexpected Behaviour!
 */
public class Game {
    ArrayList<Player> players;
    ArrayList<Card> deck;
    ArrayList<Card> discardedCards;
    Player playerOne;
    Player playerTwo;

    public Game() {
        //Create Deck
        deck = new ArrayList<>();
        discardedCards = new ArrayList<>();
        initializeDeck();
        Collections.shuffle(deck);

        //Create Players
        players = new ArrayList<>();
        playerOne = new Player("Dr. Mabuse");
        playerTwo = new Player("Schwarzer Peter");
        players.add(playerOne);
        players.add(playerTwo);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    private void start() {
        System.out.println("Game has started, shuffling deck...\n");
        //Remove first card of the deck entirely
        deck.remove(0);

        //Put aside 3 cards from the deck (for 2-player-game)
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));

        //Deal first card to all players
        drawCard(playerOne);
        drawCard(playerTwo);
        System.out.println();

        //Play game
        play();

    }

    private void play() {
        Scanner input = new Scanner(System.in);
        while (!gameOver()) {

            //Player One's turn
            System.out.println("===========");
            System.out.println(playerOne.getName() + "'s turn...");
            playerOne.setImmune(false);
            drawCard(playerOne);
            System.out.println("Your current cards: " + playerOne.displayCurrentCards());
            System.out.println("Which card do you want to play? Enter the number");
            int cardPosition = Integer.parseInt(input.nextLine());
            Card playedCard = playerOne.getCurrentCards().get(cardPosition-1);
            System.out.println("Played card: " + playedCard.toString());
            handleCardAction(playerOne, playedCard);
            discardedCards.add(playedCard);

            //Check again if game is over between moves
            if (gameOver()) {
                System.out.println("Game over! Somebody won, but we don't know who (yet).");
                break;
            }

            //Player Two's turn
            System.out.println("===========");
            System.out.println(playerTwo.getName() + "'s turn...");
            playerTwo.setImmune(false);
            drawCard(playerTwo);
            System.out.println("Your current cards: " + playerTwo.displayCurrentCards());
            System.out.println("Which card do you want to play? Enter the number");
            int cardPosition2 = Integer.parseInt(input.nextLine());
            Card playedCard2 = playerTwo.getCurrentCards().get(cardPosition2-1);
            System.out.println("Played card: " + playedCard2.toString());
            handleCardAction(playerTwo, playedCard2);
            discardedCards.add(playedCard2);

        }
    }

    private void handleCardAction(Player player, Card card) {

        if (card instanceof Princess) {
            System.out.println("You discarded the princess, you lost!");
            discardCard(player, card);
            player.setPlaying(false);

        } else if (card instanceof Countess) {
            System.out.println("Countess was discarded without any effects.");
            discardCard(player, card);

        } else if (card instanceof King) {
            System.out.println("Trade cards with another player.");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer();
            if (!chosenPlayer.isImmune()) {
                //Swap cards
                Card chosenPlayersCard = chosenPlayer.getCurrentCards().remove(0);
                player.getCurrentCards().add(chosenPlayersCard);
                chosenPlayersCard = player.getCurrentCards().remove(0);
                chosenPlayer.getCurrentCards().add(chosenPlayersCard);
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Prince) {
            System.out.println("Force a player to discard her hand and draw a new one");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer();
            if (!chosenPlayer.isImmune()) {
                Card chosenPlayerCard = chosenPlayer.getCurrentCards().remove(0);
                discardedCards.add(chosenPlayerCard);
                //If Player forced to discard Princess, player lost!
                if (chosenPlayerCard instanceof Princess) {
                    chosenPlayer.setPlaying(false);
                } else {
                    drawCard(chosenPlayer);
                    System.out.println(chosenPlayer.getName() + " drew a new Card");
                }
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Handmaid) {
            System.out.println("You are protected from all effects for the next round...");
            discardCard(player, card);
            player.setImmune(true);

        } else if (card instanceof Baron) {
            System.out.println("Compare your card with opponent...");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer();
            if (!chosenPlayer.isImmune()) {
                //Compare Cards
                int thisPlayersCardValue = player.getCurrentCards().get(0).getValue();
                int otherPlayersCardValue = chosenPlayer.getCurrentCards().get(0).getValue();
                if (thisPlayersCardValue > otherPlayersCardValue) {
                    System.out.println("Your card value: " + thisPlayersCardValue);
                    System.out.println("Opponent's card value: " + otherPlayersCardValue);
                    System.out.println("Your card was higher, other player is out.");
                    chosenPlayer.setPlaying(false);
                } else if (thisPlayersCardValue < otherPlayersCardValue) {
                    System.out.println("Your card value: " + thisPlayersCardValue);
                    System.out.println("Opponent's card value: " + otherPlayersCardValue);
                    System.out.println("Your card was lower, you are out.");
                    player.setPlaying(false);
                }
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Priest) {
            System.out.println("Look at your opponent's card...");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer();
            if (!chosenPlayer.isImmune()) {
                System.out.println("Your opponent has: " + chosenPlayer.displayCurrentCards());
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Guard) {
            System.out.println("Guess your opponent's card...");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer();
            if (!chosenPlayer.isImmune()) {
                guessCard(chosenPlayer);
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }
        }
    }
    //Method for chosing the other player (for trading, looking, comparing cards).
    private Player choosePlayer() {
        System.out.println("Choose player to perform card action on... Enter number!");
        showPlayers();
        Scanner input = new Scanner(System.in);
        int chosenPlayerIndex = Integer.parseInt(input.nextLine());
        Player chosenPlayer = players.get(chosenPlayerIndex-1);
        return chosenPlayer;
    }

    //Method for the Guard-action
    private void guessCard(Player chosenPlayer) {
        System.out.println("Guess the card of your opponent");
        System.out.println("Enter 1 for Guard, 2 for Priest, 3 for Baron, ...");
        Scanner input = new Scanner(System.in);
        int chosenCardIndex = Integer.parseInt(input.nextLine());
        if (chosenCardIndex == chosenPlayer.getCurrentCards().get(0).getValue()) {
            System.out.println("Correct! " + chosenPlayer.getName() + " is out!");
            chosenPlayer.setPlaying(false);
        } else {
            System.out.println("Incorrect");
        }

    }

    //Display the players for selection
    private void showPlayers() {
        int playerIndex = 1;
        for (Player p : players) {
            System.out.print(playerIndex + ": " + p.getName());
            if (p.isImmune()) {
                System.out.print(" (immune)");
            }
            System.out.println();
            playerIndex++;
        }
    }


    private void drawCard(Player player) {
        player.addCardToCurrent(deck.remove(0));
    }

    private void discardCard(Player player, Card card) {
        player.getCurrentCards().remove(card);
    }

    //Used later for displaying all cards that have already been played
    public void showDiscardedCards() {
        System.out.println("DISCARDED CARDS");
        for (Card c : discardedCards) {
            System.out.println(c.toString());
        }
    }

    /**
     * Method checks two things:
     * 1. Is at least one card left in the deck?
     * 2. Is more than one player still in the game?
     * @return No Cards left OR No players left --> gameOver = true
     */
    private boolean gameOver() {

        //Check deck size
        boolean noCardsLeft = deck.size() < 1;
        if (noCardsLeft) {
            System.out.println("No cards left in the deck, game over");
        }

        //Count active players
        boolean allPlayersOut = false;
        int activePlayers = 0;
        for (Player p : players) {
            if (p.isPlaying()) {
                activePlayers++;
            }
        }
        if (activePlayers < 2) {
            allPlayersOut = true;
        }

        return noCardsLeft || allPlayersOut;
    }

    //Somebody got a better idea for adding the 16 cards to the empty deck?
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
