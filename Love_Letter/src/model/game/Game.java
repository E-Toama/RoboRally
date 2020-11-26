package model.game;

import model.game.cards.*;

import java.util.*;

/*TODO (Optional):
 *  Catch NumberFormatExceptions if user inputs a String instead of a parsable Integer value
 *      (not needed anymore if we are working with String input instead of numbers)
 *  Improvement ideas for the determineWinner()-method
 *  Maybe add a removePlayer-method if one of the players wants to leave the game early
 * */


/**
 * Basic implementation of the game logic. All is happening inside ONE console window,
 * input of Cards to play and opponents to choose is realized with number-input from the console (via Scanner).
 *
 * @author Josef, Ehbal
 */
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


    /**
     * Main method represents the way to initialize, join and start a game. All the steps can later be
     * invoked by commands via ChatMessage
     */
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

    /**
     * Sets the tokens needed to win according to player count,
     * invokes the play-method if exactly 2-4 players are present
     *
     * @param playerCount determines the tokens needed to win
     */
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

    /**
     * Starts the actual game, implements a simple tun-based approach
     * using while-loops to check whether game or round is over
     * and an iterator to realize each individual turn
     */
    private void play() {

        //Full game loop
        while (!gameOver()) {
            initializeNewRound();
            System.out.println("\nGame has started, shuffling deck, dealing first card...\n");

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
                        int cardPosition = parseUserChoiceofCard();
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

    /**
     * Resets all game and player fields to default; creates and shuffles the deck;
     * prepares game setup according to player count (e.g. discard 3 cards at the beginning for 2-player-game)
     */
    private void initializeNewRound() {
        //Clear all Lists before the next round starts
        activePlayers = new ArrayList<>();
        discardedCards = new ArrayList<>();
        //Add all players to the new round
        activePlayers.addAll(players);

        //Reset fields and status of all players (except score)
        for (Player p : activePlayers) {
            p.reset();
        }
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

    /**
     * Compare current standings with tokensNeededToWin
     *
     * @return true if one of the players reached the tokens needed to win
     */
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


    /**
     * Checking two things: 1. Is the deck empty? 2. Is only one player left?
     *
     * @return true if one of the conditions is satisfied
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
                        // If Prince was played in the very last turn,
                        // the player draws the mysteryCard put aside at the beginning
                        chosenPlayer.addCardToCurrent(mysteryCard);
                    }
                }
            } else {
                System.out.println(chosenPlayer.getName() + " is protected by the Handmaid");
            }

        } else if (card instanceof Handmaid) {
            System.out.println("You are protected from all effects until it is your turn again.");
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
                } else {
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

    /**
     * Choose a player to apply card effect (for trading, looking, comparing cards).
     *
     * @param currentPlayer needed to check if player tries to choose herself (not allowed)
     * @return the chosen player
     */
    private Player choosePlayer(Player currentPlayer) {
        System.out.println("Choose player to perform card action on... Enter number!");
        showPlayers();
        int chosenPlayerIndex = parseUserChoiceofPlayer();
        Player chosenPlayer = findSelectablePlayers().get(chosenPlayerIndex - 1);
        while (chosenPlayer.equals(currentPlayer)) {
            System.out.println("You cannot choose yourself for this effect.");
            System.out.println("Choose another player to perform card action on... Enter number!");
            showPlayers();
            chosenPlayerIndex = parseUserChoiceofPlayer();
            chosenPlayer = findSelectablePlayers().get(chosenPlayerIndex - 1);
        }
        return findSelectablePlayers().get(chosenPlayerIndex - 1);
    }

    //Method for choosing the other player including self (for Prince card)
    private Player choosePlayerIncludingSelf() {
        System.out.println("Choose player to perform card action on... Enter number!");
        showPlayers();
        int chosenPlayerIndex = parseUserChoiceofPlayer();
        return findSelectablePlayers().get(chosenPlayerIndex - 1);
    }

    /**
     * Helper-method to correctly parse a valid player index
     *
     * @return valid player index of chosen player
     */
    private int parseUserChoiceofPlayer() {
        int chosenPlayerIndex = Integer.parseInt(input.nextLine());
        int currentPlayerCount = findSelectablePlayers().size();
        while (chosenPlayerIndex < 1 || chosenPlayerIndex > currentPlayerCount) {
            System.out.println("Please choose a correct player index between 1 and " + currentPlayerCount);
            chosenPlayerIndex = Integer.parseInt(input.nextLine());
        }
        return chosenPlayerIndex;
    }

    /**
     * Helper-method to return correct card index
     *
     * @return valid card index (1 or 2)
     */
    private int parseUserChoiceofCard() {
        int chosenCardIndex = Integer.parseInt(input.nextLine());
        while (chosenCardIndex < 1 || chosenCardIndex > 2) {
            System.out.println("Please choose a correct card index between 1 and 2");
            chosenCardIndex = Integer.parseInt(input.nextLine());
        }
        return chosenCardIndex;
    }

    /**
     * Helper-method for the GUARD-card-action
     *
     * @param chosenPlayer the target player for guessing
     */
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
        StringBuilder sb = new StringBuilder();
        sb.append("DISCARDED CARDS: ");
        for (Card c : discardedCards) {
            sb.append(c.toString()).append("; ");
        }
        sb.append("\n");
        System.out.println(sb.toString());
    }

    //Display the players for selection
    private void showPlayers() {
        StringBuilder sb = new StringBuilder();
        int playerIndex = 1;
        for (Player p : findSelectablePlayers()) {
            sb.append(playerIndex).append(": ").append(p.getName());
            if (p.isImmune()) {
                sb.append(" (immune)");
            }
            sb.append("\n");
            playerIndex++;
        }
        System.out.println(sb.toString());
    }

    public void displayScoreBoard() {
        for (Player p : players) {
            System.out.println(p.getName() + ": " + p.getScore() + " Points");
        }
    }

    /**
     * Find players that can be chosen for card effect
     *
     * @return list of all players who are still in the round
     */
    public ArrayList<Player> findSelectablePlayers() {
        ArrayList<Player> selectable = new ArrayList<>();
        for (Player p : players) {
            if (p.isPlaying()) {
                selectable.add(p);
            }
        }
        return selectable;
    }

    /**
     * Determine round winner and increases player score according to the rules:
     * 1. Only one player left alive in the round?
     * 2. Else: Compare the card values, highest wins
     * 3. Else: Compare total sum of discarded cards of each player
     * 4. Else: Multiple winners, both/all get score-points
     *
     * @return winner(s) name(s) as String
     */
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
