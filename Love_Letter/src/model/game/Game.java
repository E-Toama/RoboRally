package model.game;

import model.game.cards.*;

import java.util.*;

/**
 * Basic implementation of the game logic. All is happening inside ONE console window,
 * input of Cards to play and opponents to choose is realized with number-input from the console (via Scanner).
 * @author Josef, Ehbal
 */
/*
 * TODO:
 *  Catch errors, especially IndexOutOfBounds for the ArrayLists
 *  Create a legalMove()-method that checks which cards the player is allowed to play
 *  Generally improve code quality by removing redundancies and transferring some of the methods to Card/Player-Class
 * */
public class Game {
    //List of players who joined the game at the start
    private ArrayList<Player> players;
    //List of currently active players in the round
    private ArrayList<Player> activePlayers;
    private ArrayList<Card> deck;
    private ArrayList<Card> discardedCards;
    private ArrayList<Card> currentCards;
    private Card mysteryCard;
    private Scanner input = new Scanner(System.in);

    private int tokensNeededToWin;
    private boolean legalMove;

    public Game() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public int getPlayerCount() {
        return players.size();
    }


    public static void main(String[] args) {
        //On command "!NEWGAME"
        Game game = new Game();
        //On command "!JOIN"
        game.addPlayer(new Player("Dr. Mabuse"));
        game.addPlayer(new Player("Schwarzer Peter"));
        game.addPlayer(new Player("Schellnsau"));
        game.addPlayer(new Player("Belle"));
        //On command "!START"
        game.start(game.getPlayerCount());
    }

    // Set number of tokens needed to win according to player-count
    public void start(int playerCount) {
        switch (playerCount) {
            case 2 -> {
                tokensNeededToWin = 7;
                play();
            }
            case 3 -> {
                tokensNeededToWin = 5;
                play();
            }
            case 4 -> {
                tokensNeededToWin = 4;
                play();
            }
            default -> {
                System.out.println("Wrong number of players, currently " + playerCount);
                System.out.println("Please restart a new game if 2-4 players have joined");
            }
        }

    }

    private void play() {

        //Full game loop
        while (!gameOver()) {
            //Clear all Lists before the next round starts
            activePlayers = new ArrayList<>();
            discardedCards = new ArrayList<>();

            activePlayers.addAll(players);

            //Reset fields and status of all players (except score)
            for (Player p : activePlayers) {
                p.reset();
            }

            System.out.println("\nGame has started, shuffling deck...\n");
            initializeDeck();
            Collections.shuffle(deck);

            //Remove first card of the deck entirely
            mysteryCard = deck.remove(0);

            //Put aside 3 cards from the deck (for 2-player-game)
            if (activePlayers.size() == 2) {
                discardedCards.add(deck.remove(0));
                discardedCards.add(deck.remove(0));
                discardedCards.add(deck.remove(0));
            }

            //Deal first card to all players
            for (Player p : activePlayers) {
                drawCard(p);
            }

            while (!roundOver()) {
                ListIterator<Player> turnIterator;
                for (turnIterator = activePlayers.listIterator(); turnIterator.hasNext(); ) {
                    Player currentPlayer = turnIterator.next();
                    //Remove the player from the list if she is already out
                    if (!currentPlayer.isPlaying()) {
                        //If the kicked-out player still held a card, put it on the discarded pile
                        if (!currentPlayer.getCurrentCards().isEmpty()) {
                            discardCard(currentPlayer, currentPlayer.getCurrentCards().get(0));
                        }
                        turnIterator.remove();
                        continue;
                    }
                    System.out.println("===========");
                    showDiscardedCards();
                    System.out.println(currentPlayer.getName() + "'s turn...");
                    currentPlayer.setImmune(false);
                    drawCard(currentPlayer);
                    System.out.println("Your current cards: " + currentPlayer.displayCurrentCards());
                    //check if player has Countess + King/Prince
                    Card playedCard;
                    if (currentPlayer.checkCountessPlusX()) {
                        System.out.println("You have to play the Countess!");
                        int cardPosition = currentPlayer.getCurrentCards().get(0) instanceof Countess ? 0 : 1;
                        playedCard = currentPlayer.getCurrentCards().get(cardPosition);
                    } else {
                        System.out.println("Which card do you want to play? Enter the number");
                        int cardPosition = Integer.parseInt(input.nextLine());
                        playedCard = currentPlayer.getCurrentCards().get(cardPosition - 1);
                    }

                    System.out.println("Played card: " + playedCard.toString());
                    handleCardAction(currentPlayer, playedCard);
                    discardedCards.add(playedCard);


                    //Check again if round is over between moves
                    if (roundOver()) {
                        activePlayers.removeIf(p -> !p.isPlaying());
                        String winner = determineWinner();
                        System.out.println("Round over! The winner is: " + winner);
                        System.out.println("Current standings: ");
                        displayScoreBoard();
                        break;
                    }
                }
            }
        }
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


    //Status methods

    private boolean gameOver() {
        for (Player p : players) {
            if (p.getScore() == tokensNeededToWin) {
                System.out.println(p.getName() + " has won the game!");
                displayScoreBoard();
                System.out.println("To start a new game, enter !NEWGAME");
                return true;
            }
        }
        return false;
    }


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


    //Player action methods


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
            Player chosenPlayer = choosePlayer(player);
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
            Player chosenPlayer = choosePlayerIncludingSelf();
            if (!chosenPlayer.isImmune()) {
                Card chosenPlayerCard = chosenPlayer.getCurrentCards().remove(0);
                discardedCards.add(chosenPlayerCard);
                //If Player forced to discard Princess, player lost!
                if (chosenPlayerCard instanceof Princess) {
                    chosenPlayer.setPlaying(false);
                    System.out.println(chosenPlayer.getName() + " was forced to discard the Princess and is out!");
                } else {
                    if (deck.size() > 0) {
                        drawCard(chosenPlayer);
                        System.out.println(chosenPlayer.getName() + " drew a new Card");
                    } else {
                        //If Prince was played in the very last turn, the player draws the mysteryCard from the beginning
                        chosenPlayer.addCardToCurrent(mysteryCard);
                    }
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
            Player chosenPlayer = choosePlayer(player);
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
                } else  {
                    System.out.println("It's a tie! You both have the same card, nothing happens");
                }
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Priest) {
            System.out.println("Look at your opponent's card...");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer(player);
            if (!chosenPlayer.isImmune()) {
                System.out.println("Your opponent has: " + chosenPlayer.displayCurrentCards());
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Guard) {
            System.out.println("Guess your opponent's card...");
            discardCard(player, card);
            Player chosenPlayer = choosePlayer(player);
            if (!chosenPlayer.isImmune()) {
                guessCard(chosenPlayer);
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }
        }
    }

    //Method for chosing the other player (for trading, looking, comparing cards).
    private Player choosePlayer(Player currentPlayer) {
        System.out.println("Choose player to perform card action on... Enter number!");
        showPlayers();
        int chosenPlayerIndex = Integer.parseInt(input.nextLine());
        Player chosenPlayer = findSelectablePlayers().get(chosenPlayerIndex - 1);
        while (chosenPlayer.equals(currentPlayer)) {
            System.out.println("You cannot choose yourself for this effect.");
            System.out.println("Choose another player to perform card action on... Enter number!");
            showPlayers();
            chosenPlayerIndex = Integer.parseInt(input.nextLine());
            chosenPlayer = findSelectablePlayers().get(chosenPlayerIndex - 1);
        }
        return findSelectablePlayers().get(chosenPlayerIndex - 1);
    }

    //Method for choosing the other player including self (for Prince card)
    private Player choosePlayerIncludingSelf() {
        System.out.println("Choose player to perform card action on... Enter number!");
        showPlayers();
        int chosenPlayerIndex = Integer.parseInt(input.nextLine());
        return findSelectablePlayers().get(chosenPlayerIndex - 1);
    }

    //Method for the Guard-action
    private void guessCard(Player chosenPlayer) {
        System.out.println("Guess the card of your opponent");
        System.out.println("Enter 2 for Priest, 3 for Baron, 4 for Handmaid,...");
        int chosenCardIndex = Integer.parseInt(input.nextLine());
        while (chosenCardIndex < 2 || chosenCardIndex > 8) {
            System.out.println("Please enter a valid card value (2-8)");
            System.out.println("Enter 2 for Priest, 3 for Baron, 4 for Handmaid,...");
            chosenCardIndex = Integer.parseInt(input.nextLine());
        }
        if (chosenCardIndex == chosenPlayer.getCurrentCards().get(0).getValue()) {
            System.out.println("Correct! " + chosenPlayer.getName() + " is out!");
            chosenPlayer.setPlaying(false);
        } else {
            System.out.println("Incorrect");
        }

    }

    private void drawCard(Player player) {
        //Check if deck still has cards - otherwise error, if last card played is Prince
        if (deck.size() > 0) {
            player.addCardToCurrent(deck.remove(0));
        }
    }

    private void discardCard(Player player, Card card) {
        player.getCurrentCards().remove(card);
        player.addDiscardedCard(card);
    }


    //Display game status

    public void showDiscardedCards() {
        System.out.print("DISCARDED CARDS: ");
        for (Card c : discardedCards) {
            System.out.print(c.toString() + "; ");
        }
        System.out.println();
    }

    //Display the players for selection
    private void showPlayers() {
        int playerIndex = 1;
        for (Player p : findSelectablePlayers()) {
            System.out.print(playerIndex + ": " + p.getName());
            if (p.isImmune()) {
                System.out.print(" (immune)");
            }
            System.out.println();
            playerIndex++;
        }
    }

    public void displayScoreBoard() {
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getScore() + " Points");
        }
    }

    public ArrayList<Player> findSelectablePlayers() {
        ArrayList<Player> selectable = new ArrayList<>();
        for (Player p : players) {
            if (p.isPlaying()) {
                selectable.add(p);
            }
        }
        return selectable;
    }

    private String determineWinner() {
        //check if only one Player left:
        if (activePlayers.size() == 1) {
            Player winner = activePlayers.get(0);
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


}
