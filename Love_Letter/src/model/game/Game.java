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
 * Only one round is played.
 * All the actions involving a second player (compare, swap, look at cards) can currently be performed with oneself, too.
 * No error-cases are caught! Especially IndexOutOfBounds for the ArrayLists.
 * The effect of having Countess + King/Prince is not implemented yet.
 * Bonus features: Bugs, Flaws, Unexpected Behaviour!
 */
public class Game {
    //List of players who joined the game at the start
    private ArrayList<Player> players;
    //List of currently active players in the round
    private ArrayList<Player> activePlayers;
    private ArrayList<Card> deck;
    private ArrayList<Card> discardedCards;

    private int tokensNeededToWin;

    public Game() {
        //Create Deck
        deck = new ArrayList<>();
        discardedCards = new ArrayList<>();
        initializeDeck();

        //Create PlayerLists
        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getPlayerCount() {
        return players.size();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Dr. Mabuse"));
        game.addPlayer(new Player("Schwarzer Peter"));
        game.start(game.getPlayerCount());
    }

    public void start(int playerCount) {
        switch (playerCount) {
            case 2:
                initializeTwoPlayerGame();
                break;
            case 3:
                //initializeThreePlayerGame();
                break;
            case 4:
                //initializeFourPlayerGame();
                break;
            default:
                System.out.println("Wrong number of players, currently " + playerCount);
                System.out.println("Please restart a new game if 2-4 players have joined");
        }

    }

    private void initializeTwoPlayerGame() {
        tokensNeededToWin = 7;
        for (Player p : players) {
            activePlayers.add(p);
        }
        System.out.println("Game has started, shuffling deck...\n");
        Collections.shuffle(deck);
        //Remove first card of the deck entirely
        deck.remove(0);

        //Put aside 3 cards from the deck (for 2-player-game)
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));

        //Play game
        play();

    }

/*    private void initializeMultiPlayerGame() {
        System.out.println("Game has started, shuffling deck...\n");
        //Remove first card of the deck entirely
        deck.remove(0);

        //Put aside 3 cards from the deck (for 2-player-game)
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));
        discardedCards.add(deck.remove(0));

        //Play game
        play();

    }*/

    private void play() {
        Scanner input = new Scanner(System.in);
        //Deal first card to all players
        for (Player p : activePlayers) {
            drawCard(p);
        }
        while (!roundOver()) {
            for (Player currentPlayer : activePlayers) {
                System.out.println("===========");
                showDiscardedCards();
                System.out.println(currentPlayer.getName() + "'s turn...");
                currentPlayer.setImmune(false);
                drawCard(currentPlayer);
                System.out.println("Your current cards: " + currentPlayer.displayCurrentCards());
                System.out.println("Which card do you want to play? Enter the number");
                int cardPosition = Integer.parseInt(input.nextLine());
                Card playedCard = currentPlayer.getCurrentCards().get(cardPosition-1);
                System.out.println("Played card: " + playedCard.toString());
                handleCardAction(currentPlayer, playedCard);
                discardedCards.add(playedCard);

                //Check again if round is over between moves
                if (roundOver()) {
                    String winner = determineWinner();
                    System.out.println("Game over! The winner is: " + winner);
                    System.out.println("Current standings: ");
                    displayScoreBoard();
                    break;
                }
            }
        }
    }

    private String determineWinner() {
        //check if only one Player left:
        if (activePlayers.size() == 1) {
            Player winner = activePlayers.remove(0);
            winner.incrementScore();
            return winner.getName();
        }
        //If more than one player left:
        int valueOfHighestCard = 0;
        for (Player p : activePlayers) {
            int currentPlayerCardValue = p.getCurrentCards().get(0).getValue();
            if (currentPlayerCardValue > valueOfHighestCard) {
                valueOfHighestCard = currentPlayerCardValue;
            }
        }
        //Add all players with highestCard to potentialWinner
        ArrayList<Player> potentialWinners = new ArrayList<>();
        for (Player p : activePlayers) {
            if (p.getCurrentCards().get(0).getValue() == valueOfHighestCard) {
                potentialWinners.add(p);
            }
        }
        //Check if there is only one potential winner:
        if (potentialWinners.size() == 1) {
            Player winner = potentialWinners.remove(0);
            winner.incrementScore();
            return winner.getName();
        }
        //If more than one player has highest card, compare their discardedCardValues
        int highestValue = 0;
        for (Player p : potentialWinners) {
            int currentPlayerCardValue = p.countDiscardedScore();
            if (currentPlayerCardValue > highestValue) {
                highestValue = currentPlayerCardValue;
            }
        }

        //Add all players with highestDiscardedCards to potentialWinner
        ArrayList<Player> tiedPlayers = new ArrayList<>();
        for (Player p : potentialWinners) {
            if (p.countDiscardedScore() == highestValue) {
                tiedPlayers.add(p);
            }
        }
        //Check if there is only one potential winner:
        if (tiedPlayers.size() == 1) {
            Player winner = tiedPlayers.remove(0);
            winner.incrementScore();
            return winner.getName();
        } else {
            //Else, it's a tie. Get winner list:
            StringBuilder sb = new StringBuilder();
            for (Player p : tiedPlayers) {
                p.incrementScore();
                sb.append(p.getName()).append("; ");
            }
            return sb.toString();
        }
    }

    public void displayScoreBoard() {
        for (Player p : players) {
            System.out.println(p.getName() + ": " +p.getScore() + " Points");
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
                Card cardFromOpponent = chosenPlayer.getCurrentCards().remove(0);
                player.getCurrentCards().add(cardFromOpponent);
                Card cardForOpponent = player.getCurrentCards().remove(0);
                chosenPlayer.getCurrentCards().add(cardForOpponent);
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
        return  activePlayers.get(chosenPlayerIndex-1);
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
        for (Player p : activePlayers) {
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
        player.addDiscardedCard(card);
    }

    //Used later for displaying all cards that have already been played
    public void showDiscardedCards() {
        System.out.print("DISCARDED CARDS: ");
        for (Card c : discardedCards) {
            System.out.print(c.toString() + "; ");
        }
        System.out.println();
    }

    /**
     * Method checks two things:
     * 1. Is at least one card left in the deck?
     * 2. Is more than one player still in the game?
     * @return No Cards left OR No players left --> roundOver = true
     */
    private boolean roundOver() {

        //Check deck size
        boolean noCardsLeft = deck.size() < 1;
        if (noCardsLeft) {
            System.out.println("No cards left in the deck, game over");
        }

        //Count active players
        boolean allPlayersOut = false;
        int active = 0;
        for (Player p : activePlayers) {
            if (p.isPlaying()) {
                active++;
            }
        }
        if (active < 2) {
            allPlayersOut = true;
        }

        return noCardsLeft || allPlayersOut;
    }

   /* private boolean gameOver() {
        int highestScore = 0;
        for (Player p : players) {
            if (p.getScore() == )
        }
    }*/

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
