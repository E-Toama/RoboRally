package AI.logic.utilities;

import AI.logic.AIGameState;
import AI.network.AINetworkThread;
import utilities.MessageHandler;
import utilities.messages.SelectCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AICardHandler {

    private AINetworkThread aiNetworkThread;
    private AIGameState aiGameState;
    private MessageHandler messageHandler = new MessageHandler();

    public AICardHandler(AINetworkThread aiNetworkThread, AIGameState aiGameState) {

        this.aiNetworkThread = aiNetworkThread;
        this.aiGameState = aiGameState;

    }

    public void handleCards(String[] cards) {

        List<String[]> possibleRegisters = getPossibleRegisters(cards);

        int currentBestRating = 0;
        String[] currentBestPossibleRegister = new String[0];

        for (String[] possibleRegister : possibleRegisters) {

            int registerRating = calculateDestinationRating(possibleRegister);

            if (registerRating < currentBestRating) {

                currentBestRating = registerRating;
                currentBestPossibleRegister = possibleRegister;

            }

        }

        int registerNumber = 1;

        for (String register : currentBestPossibleRegister) {

            String selectCard = messageHandler.buildMessage("SelectCard", new SelectCard(register, registerNumber));
            aiNetworkThread.sendJson(selectCard);
            registerNumber++;

        }

    }

    private List<String[]> getPossibleRegisters(String[] cards) {

        List<String[]> possibleRegisters = new ArrayList<>();

        List<String> cardsList9 = new ArrayList<>(Arrays.asList(cards));

        for (String cardOne : cardsList9) {

            List<String> cardList8 = new ArrayList<>(cardsList9);
            cardList8.remove(cardOne);

            for (String cardTwo : cardList8) {

                List<String> cardList7 = new ArrayList<>(cardList8);
                cardList7.remove(cardTwo);

                for (String cardThree : cardList7) {

                    List<String> cardList6 = new ArrayList<>(cardList7);
                    cardList6.remove(cardThree);

                    for (String cardFour : cardList6) {

                        List<String> cardList5 = new ArrayList<>(cardList6);
                        cardList5.remove(cardFour);

                        for (String cardFive : cardList5) {

                            String[] possibleRegister = {cardOne, cardTwo, cardThree, cardFour, cardFive};
                            possibleRegisters.add(possibleRegister);

                        }

                    }

                }

            }

        }

        return possibleRegisters;

    }

    private int calculateDestinationRating(String[] possibleRegister) {

        return 0;

    }

}
