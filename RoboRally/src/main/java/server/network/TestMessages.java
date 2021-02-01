package server.network;

import game.cards.ActiveCards;
import game.cards.Card;
import utilities.messages.Movement;

import java.util.Arrays;
import java.util.LinkedList;

public class TestMessages {

    public static String[] testCardsForProgrammingView = new String[]{"Trojan", "Virus", "Worm", "Spam", "MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};
    public static String[] cardsYouGotNow = new String[]{"MoveII", "TurnRight", "UTurn", "MoveI", "Again"};

    public static ActiveCards[] currentCardsReg1 = {new ActiveCards(1,"Move1")};
    public static LinkedList<String> availableDamageCards = new LinkedList<>(Arrays.asList("Virus", "Worm"));



}
