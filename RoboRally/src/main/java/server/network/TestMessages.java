package server.network;

import game.cards.ActiveCards;
import game.cards.Card;
import utilities.messages.Movement;

public class TestMessages {

    public static String[] testCardsForProgrammingView = new String[]{"MoveI", "MoveII", "MoveIII", "TurnLeft", "TurnRight", "UTurn", "BackUp", "PowerUp", "Again"};
    public static String[] cardsYouGotNow = new String[]{"MoveII", "TurnRight", "UTurn", "MoveI", "Again"};

    public static ActiveCards[] currentCardsReg1 = {new ActiveCards(1,"Move1")};



}
