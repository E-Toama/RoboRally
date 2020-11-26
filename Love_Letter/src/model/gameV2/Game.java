package model.gameV2;

import model.gameV2.cards.*;
import model.network.ChatServer;

import java.util.LinkedList;
import java.util.List;

/**
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

    public Game(ChatServer server, Player firstPlayer) {

        this.server = server;
        addPlayer(firstPlayer);

    }

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

    public void getStatus(String username) {

        for (Player player : playerList) {

            server.sendMessageToSingleUser(username, player.userName + ": " + player.getWins());

        }

    }

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

    public void discardCard(Card card) {

        discardedCards.add(0, card);

    }

    private Card drawCardFromDeck() {

        return cards.remove( (int) (Math.random() * (cards.size() - 1)) );

    }

    public List<Player> getActivePlayerList() {
        return activePlayerList;
    }

    public Player getActivePlayerByUsername(String playerName) {

        for (Player player : activePlayerList) {

            if (player.userName.equals(playerName)) {

                return player;

            }

        }

        return null;

    }

    public List<Player> getNextRoundActivePlayerList() {
        return nextRoundActivePlayerList;
    }

    public int setRoundWinsNeeded() {

        if(playerList.size() == 2) {

            return 7;

        } else if (playerList.size() == 3) {

            return 5;

        } else {

            return 4;

        }
    }

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

    private void startNewRound() {

        if (nextRoundActivePlayerList.get(0).getWins() != roundWinsNeeded) {

            activePlayerList = nextRoundActivePlayerList;
            nextRoundActivePlayerList = new LinkedList<Player>();

            fillDeck();

            hiddenCard = drawCardFromDeck();

            for (Player player : activePlayerList) {
                player.addCard(drawCardFromDeck());
                player.setProtected(false);
            }

            if (playerList.size() == 2) {

                for(int i = 0; i < 3; i++) {

                    discardCard(drawCardFromDeck());

                }

            }

            gameMove(activePlayerList.get(0));

        } else {

            server.sendMessageToAllUsers(nextRoundActivePlayerList.get(0).userName + " has won the game!");
            server.endGame();

        }

    }

    public void gameMove(Player player) {

        if (activePlayerList.size() > 1) {

            for (Player player1 : activePlayerList) {

                if(player != player1) {

                    server.sendMessageToSingleUser(player1.userName, "It's " + player.userName + " turn!");

                }

            }
            player.requestAction(this, drawCardFromDeck());

        } else {

            server.sendMessageToAllUsers(player.userName + " has won the Round!");
            server.sendMessageToAllUsers("Starting new Round!");

            player.setWins();
            nextRoundActivePlayerList.add(0, player);

            startNewRound();

        }

    }

    public void playLeftCard(String username) {

        if (username.equals(activePlayerList.get(0).userName)) {

            playCard(activePlayerList.get(0).getCards().remove(0), activePlayerList.get(0));

        } else {

            server.sendMessageToSingleUser(username, "Wait until it's your turn!");

        }

    }

    public void playRightCard(String username) {

        if (username.equals(activePlayerList.get(0).userName)) {

            playCard(activePlayerList.get(0).getCards().remove(1), activePlayerList.get(0));

        } else {

            server.sendMessageToSingleUser(username, "Wait until it's your turn!");

        }

    }

    public void playCard(Card playedCard, Player player) {

        server.sendMessageToAllUsers(player.userName + " has played the " + playedCard.getName() + "!");
        discardCard(playedCard);
        //needed for counting all the individual player's discarded cards in case of a tie
        player.addDiscardedCard(playedCard);
        playedCard.play(this, player);

    }

    public void playCardWithoutEffect(Card playedCard, Player player) {

        discardCard(playedCard);
        //needed for counting all the individual player's discarded cards in case of a tie
        player.addDiscardedCard(playedCard);
        if (playedCard.getValue() == 8) {

            server.sendMessageToAllUsers(player.userName + "has played the " + playedCard.getName() + "!");
            playedCard.play(this, player);

        } else {

            server.sendMessageToAllUsers(player.userName + "has played the " + playedCard.getName() + "without Effect!");
            player.addCard(drawCardFromDeck());

        }

    }

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
