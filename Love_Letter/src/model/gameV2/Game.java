package model.gameV2;

import model.gameV2.cards.*;
import model.network.ChatServer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents the "Game".
 * Holds all necessary properties / objects for a game.
 * Provides methods for interacting with the server, and gameplay as necessary.
 *
 * @author Dennis, Josef
 */
public class Game {

    private List<Player> activePlayerList = new LinkedList<Player>();
    private List<Player> playerList = new LinkedList<Player>();
    private List<Player> nextRoundActivePlayerList = new LinkedList<Player>();
    private List<Card> cards = new LinkedList<Card>();
    private List<Card> discardedCards = new LinkedList<Card>();
    public final ChatServer server;
    private Boolean gameIsRunning = false;
    private Card hiddenCard = null;
    private int roundWinsNeeded;

    /**
     * Creates a new Game
     *
     * @param server        The server on which the game is played
     * @param firstPlayer   The player who created the game (first player)
     */
    public Game(ChatServer server, Player firstPlayer) {

        this.server = server;
        addPlayer(firstPlayer);

    }

    /**
     * Returns the discarded cards of this round.
     *
     * @return If there are discarded cards, a string which contains all discarded Cards of the round,
     * otherwise a error message
     */
    public String getDiscardedCards() {

        String returnValue;

        if (discardedCards.size() > 0) {

            StringBuilder stringBuilder = new StringBuilder();

            for (Card discardedCard : discardedCards) {

                stringBuilder.append(discardedCard.getName()).append(", ");

            }

            returnValue = stringBuilder.toString();

        } else {

            returnValue = "There was no Card discarded jet!";

        }

        return returnValue;

    }

    /**
     * Returns if the game is running.
     *
     * @return "true" when the game is running, otherwise "false"
     */
    public boolean isRunning() {
        return gameIsRunning;
    }

    /**
     * Displays the requesting player how many wins each player has.
     *
     * @param username  The requesting player's username
     */
    public void getStatus(String username) {

        for (Player player : playerList) {

            server.sendMessageToSingleUser(username, player.userName + ": " + player.getWinCounter());

        }

    }

    /**
     * Adds a new Player to the Game.
     *
     * @param newPlayer The new player
     */
    public void addPlayer(Player newPlayer) {

        if (playerList.size() < 4) {

            playerList.add(newPlayer);
            activePlayerList.add(newPlayer);
            nextRoundActivePlayerList.add(newPlayer);
            server.sendMessageToSingleUser(newPlayer.userName, "You have successfully joined the game. Wait until it start's!");

        } else {

            server.sendMessageToSingleUser(newPlayer.userName, "Game is already full :(");

        }

    }

    /**
     * Restes "cards" and "discardedCards".
     * Fills "cards".
     */
    private void fillDeck() {

        cards = new LinkedList<Card>();
        discardedCards = new LinkedList<Card>();
        cards.add(new Princess());
        cards.add(new Countess());
        cards.add(new King());
        cards.add(new Prince());
        cards.add(new Prince());
        cards.add(new Handmaid());
        cards.add(new Handmaid());
        cards.add(new Baron());
        cards.add(new Baron());
        cards.add(new Priest());
        cards.add(new Priest());
        for (int i = 0; i < 5; i++) {
            cards.add(new Guard());
        }

    }

    /**
     * Adds a card to "discardedCards".
     *
     * @param card  The discarded Card
     */
    public void discardCard(Card card) {

        discardedCards.add(0, card);

    }

    /**
     * Returns a random card from the deck. If the deck is empty AND
     * the last card actively played was a Prince (= discardedCards.get(1!)),
     * the chosenPlayer draws the hidden card from the beginning.
     * @return A random card from the deck (in case of last move Prince: hiddenCard)
     */
    private Card drawCardFromDeck() {
        if (isDeckEmpty() && discardedCards.get(1).getValue() == 5) {
            return hiddenCard;
        }
        return cards.remove( (int) (Math.random() * (cards.size() - 1)) );

    }

    /**
     * Returns the active player list.
     *
     * @return The "activePlayerList".
     */
    public List<Player> getActivePlayerList() {
        return activePlayerList;
    }

    /**
     * Checks whether there is a player with the given username,
     * if so it returns the player object,
     * otherwise returns null.
     *
     * @param playerName    The specified username.
     * @return              The player object if it exists, otherwise null.
     */
    public Player getActivePlayerByUsername(String playerName) {

        for (Player player : activePlayerList) {

            if (player.userName.equals(playerName)) {

                return player;

            }

        }

        return null;

    }

    /**
     * Returns the next round active player list.
     *
     * @return The "getNextRoundActivePlayerList".
     */
    public List<Player> getNextRoundActivePlayerList() {
        return nextRoundActivePlayerList;
    }

    /**
     * Determines the number of rounds that must be won to win the game depending on the number of players.
     *
     * @return The number of rounds that muse be won.
     */
    public int setRoundWinsNeeded() {

        if(playerList.size() == 2) {

            return 7;

        } else if (playerList.size() == 3) {

            return 5;

        } else {

            return 4;

        }
    }

    /**
     * Starts the Game.
     * Checks if the player who requested to start the game is the same one, who created the game.
     *
     * @param userName  The requesting player.
     */
    public void startGame(String userName) {

        if (userName.equals(playerList.get(0).userName)) {

            if (activePlayerList.size() >= 2 && !gameIsRunning) {

                gameIsRunning = true;
                server.sendMessageToAllUsers("Starting first Round!");
                roundWinsNeeded = setRoundWinsNeeded();
                startNewRound();

            } else {

                server.sendMessageToSingleUser(userName, "There are not enough Player to start a Game!");

            }

        } else {

            server.sendMessageToSingleUser(userName, "Only the Player who created the game can start it!");

        }

    }

    /**
     * Returns the Player with the most round wins.
     *
     * @return The player with the most round wins.
     */
    private Player getCurrentLeader() {
        Player leader = null;
        int score = -1;
        for (Player p : nextRoundActivePlayerList) {
            if (p.getWinCounter() > score) {
                score = p.getWinCounter();
                leader = p;
            }
        }
        return leader;
    }

    /**
     * Starts a new round.
     * Checks beforehand whether a player has won the game.
     */
    private void startNewRound() {

        if (getCurrentLeader().getWinCounter() != roundWinsNeeded) {

            activePlayerList = nextRoundActivePlayerList;
            nextRoundActivePlayerList = new LinkedList<Player>();

            fillDeck();

            hiddenCard = drawCardFromDeck();

            for (Player player : activePlayerList) {
                player.resetPlayer(drawCardFromDeck());
            }

            if (playerList.size() == 2) {

                for(int i = 0; i < 3; i++) {

                    discardCard(drawCardFromDeck());

                }

            }

            gameMove(activePlayerList.get(0));

        } else {

            server.sendMessageToAllUsers(getCurrentLeader().userName + " has won the game!");
            server.endGame();

        }

    }

    /**
     * Starts a players game move.
     * Checks if the deck is empty and whether it is the requesting player's turn.
     * If the deck is empty it determines the round winner, and starts a new round.
     *
     * @param player    The requesting Player.
     */
    public void gameMove(Player player) {
        //Check conditions for ending the round: 1. Only one player left. 2. No cards left in the deck.
        if (activePlayerList.size() > 1 && !isDeckEmpty()) {

            for (Player player1 : activePlayerList) {

                if(player != player1) {

                    server.sendMessageToSingleUser(player1.userName, "It's " + player.userName + " turn!");

                }

            }
            player.requestAction(this, drawCardFromDeck());

        //If deck is empty or only one player left, get the list of round winners
        } else {
            //Add all remaining players to next round
            nextRoundActivePlayerList.addAll(activePlayerList);

            List<Player> winners = determineWinners();
            for (Player p : winners) {
                server.sendMessageToAllUsers(p.userName + " has won the Round!");

                p.setWinCounter();
                //adjust starting order according to game rules
                nextRoundActivePlayerList.remove(p);
                nextRoundActivePlayerList.add(0, p);
            }
            server.sendMessageToAllUsers("Starting new Round!");
            startNewRound();
        }

    }

    private ArrayList<Player> determineWinners() {
        ArrayList<Player> winners = new ArrayList<>();
        Player first = activePlayerList.get(0);
        //check if only one Player left:
        if (activePlayerList.size() == 1) {
            winners.add(first);
            return winners;
        }

        //If more than one player is left

        //Sort list according to game rules
        activePlayerList.sort(Comparator.reverseOrder());
        first = activePlayerList.get(0);
        winners.add(first);
        for (int i = 1; i < activePlayerList.size() ; i++) {
            Player second = activePlayerList.get(i);
            if (first.compareTo(second) == 0) {
                winners.add(second);
            }
        }
        return winners;

    }

    /**
     * Checks whether it's the requesting player's turn.
     * If so calls / plays the left card on the players hand (cards(0)).
     * Removes the discarded Card from the players hand.
     *
     * @param username The requesting Player.
     */
    public void playLeftCard(String username) {

        if (username.equals(activePlayerList.get(0).userName)) {

            playCard(activePlayerList.get(0).getCards().remove(0), activePlayerList.get(0));

        } else {

            server.sendMessageToSingleUser(username, "Wait until it's your turn!");

        }

    }

    /**
     * Checks whether it's the requesting player's turn.
     * If so calls / plays the right card on the players hand (cards(1)).
     * Removes the discarded Card from the players hand.
     *
     * @param username The requesting Player.
     */
    public void playRightCard(String username) {

        if (username.equals(activePlayerList.get(0).userName)) {

            playCard(activePlayerList.get(0).getCards().remove(1), activePlayerList.get(0));

        } else {

            server.sendMessageToSingleUser(username, "Wait until it's your turn!");

        }

    }

    /**
     * Notifies all players that the active player has played the playedCard.
     * Ads the playedCard to the discardedCards List.
     * Calls the play method of the playedCard.
     *
     * @param playedCard    The played card.
     * @param player        The active player.
     */
    public void playCard(Card playedCard, Player player) {

        server.sendMessageToAllUsers(player.userName + " has played the " + playedCard.getName() + "!");
        discardCard(playedCard);
        //needed for counting all the individual player's discarded cards in case of a tie
        player.addDiscardedCard(playedCard);
        playedCard.play(this, player);

    }

    /**
     * Notifies all players that the player has discarded a Card without an Effect.
     * Ads the playedCard to the discardedCards List.
     *
     * @param playedCard The played / discarded card.
     * @param player     The active player.
     */
    public void playCardWithoutEffect(Card playedCard, Player player) {

        discardCard(playedCard);
        //needed for counting all the individual player's discarded cards in case of a tie
        player.addDiscardedCard(playedCard);
        if (playedCard.getValue() == 8) {

            server.sendMessageToAllUsers(player.userName + " has played the " + playedCard.getName() + "!");
            playedCard.play(this, player);

        } else {

            server.sendMessageToAllUsers(player.userName + " has played the " + playedCard.getName() + " without effect!");
            player.addCard(drawCardFromDeck());

        }

    }

    /**
     * Returns if the deck is empty.
     *
     * @return "true" if the deck is empty, "false" if the deck isn't.
     */
    public boolean isDeckEmpty() {
        return cards.size() == 0;
    }


    /**
     * Checks whether it's the requesting player's turn.
     * Checks if the chosen player is not the requesting player.
     * If both are correct, it calls the correct completePlay function.
     *
     * @param username      The requesting player.
     * @param chosenName    The chosen player.
     */
    public void chooseAnotherPlayer(String username, String chosenName) {

        if (username.equals(activePlayerList.get(0).userName)) {

            if (!username.equals(chosenName)) {

                Player chosenPlayer = getActivePlayerByUsername(chosenName);

                if (chosenPlayer != null) {

                    switch (discardedCards.get(0).getValue()) {
                        case 1 -> new Guard().completePlay(this, activePlayerList.get(0), chosenPlayer);
                        case 2 -> new Priest().completePlay(this, activePlayerList.get(0), chosenPlayer);
                        case 3 -> new Baron().completePlay(this, activePlayerList.get(0), chosenPlayer);
                        case 6 -> new King().completePlay(this, activePlayerList.get(0), chosenPlayer);
                    }

                } else {

                    server.sendMessageToSingleUser(username, "There is no active player named," + chosenName + " please try again!");

                }

            } else {

                server.sendMessageToSingleUser(username, "You are not allowed to choose yourself! Try Again!");

            }


        } else {

            server.sendMessageToSingleUser(username, "You are not allowed to use this command at the moment!");

        }

    }

    /**
     * Checks whether it's the requesting player's turn.
     * If so, it calls the Princes completePlay method.
     *
     * @param username      The requesting player.
     * @param chosenName    The chosen player.
     */
    public void chooseAnyPlayer(String username, String chosenName) {

        if (username.equals(activePlayerList.get(0).userName) && discardedCards.get(0).getValue() == 5) {

            Player chosenPlayer = getActivePlayerByUsername(chosenName);

            if (chosenPlayer != null) {

                new Prince().completePlay(this, activePlayerList.get(0), chosenPlayer);

            } else {

                server.sendMessageToSingleUser(username, "There is no active player named," + chosenName + " please try again!");

            }

        } else {

            server.sendMessageToSingleUser(username, "You are not allowed to use this command at the moment!");

        }

    }

    /**
     * Checks whether it's the requesting player's turn.
     * Checks if the Guess is valid.
     * If both are correct, it calls the Guards guessCard method.
     *
     * @param username  The requesting player.
     * @param value     The guessed value.
     */
    public void guessCard(String username, String value) {

        if (username.equals(activePlayerList.get(0).userName)) {

            int intValue = stringToValidNumber(value);

            if (intValue == 0 || intValue == 1) {

                server.sendMessageToSingleUser(username, "You're guess is not valid, please try again!");

            } else {

                new Guard().guessCard(this, activePlayerList.get(0), intValue);

            }

        } else {

            server.sendMessageToSingleUser(username, "You are not allowed to use this command at the moment!");

        }

    }

    /**
     * Is used to get the guessed Value from String to int.
     *
     * @param value The Value as String.
     * @return      The Value as Int.
     */
    public int stringToValidNumber(String value) {

        return switch (value) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            case "4" -> 4;
            case "5" -> 5;
            case "6" -> 6;
            case "7" -> 7;
            case "8" -> 8;
            default -> 0;
        };

    }

}
