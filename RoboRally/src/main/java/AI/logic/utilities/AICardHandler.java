package AI.logic.utilities;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;
import AI.network.AINetworkThread;
import utilities.MessageHandler;
import utilities.messages.SelectCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AICardHandler {

    private final AINetworkThread aiNetworkThread;
    private final AIGameState aiGameState;
    private final MessageHandler messageHandler = new MessageHandler();

    public AICardHandler(AINetworkThread aiNetworkThread, AIGameState aiGameState) {

        this.aiNetworkThread = aiNetworkThread;
        this.aiGameState = aiGameState;

    }

    public void handleCards(String[] cards) {

        System.out.println(Arrays.toString(cards));

        List<String[]> possibleRegisters = getPossibleRegisters(cards);

        int currentBestRating = Integer.MAX_VALUE;
        String[] currentBestPossibleRegister = new String[0];

        for (String[] possibleRegister : possibleRegisters) {

            int registerRating = calculateDestinationRating(possibleRegister);

            if (registerRating < currentBestRating) {

                currentBestRating = registerRating;
                currentBestPossibleRegister = possibleRegister;

            }

        }

        System.out.println(currentBestRating);
        System.out.println(Arrays.toString(currentBestPossibleRegister));

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

    private int calculateDestinationRating(String[] possibleRegisters) {

        if (possibleRegisters[0].equals("Again")) {

            return Integer.MAX_VALUE;

        }

        SimpleBeltHandlerV2 simpleBeltHandlerV2 = new SimpleBeltHandlerV2(aiGameState);
        SimpleGearsHandler simpleGearsHandler = new SimpleGearsHandler(aiGameState);
        SimpleCheckPointHandler simpleCheckPointHandler = new SimpleCheckPointHandler(aiGameState);

        initializeIntermediateVariables();

        CardS[] register = new CardS[possibleRegisters.length];

        for (int i = 0; i < possibleRegisters.length; i++) {

            register[i] = CardS.getCardByString(possibleRegisters[i]);

        }

        aiGameState.setIntermediateRegister(register);

        int currentRegisterNumber = 0;

        for (CardS card : register) {

            if (!aiGameState.getWasRebooted()) {

                card.action(aiGameState, currentRegisterNumber);

                if (aiGameState.getIntermediateBoardElement().isBlueConveyorBelt() || aiGameState.getIntermediateBoardElement().isBlueRotatingConveyorBelt()) {
                    simpleBeltHandlerV2.simulateBlueBelts();
                }

                if (aiGameState.getIntermediateBoardElement().isGreenConveyorBelt() || aiGameState.getIntermediateBoardElement().isGreenRotatingConveyorBelt()) {
                    simpleBeltHandlerV2.simulateGreenBelts();
                }

                if (aiGameState.getIntermediateBoardElement().isGear()) {
                    simpleGearsHandler.simulateGears();
                }

                if (aiGameState.getIntermediateBoardElement().isControlPoint() > 0) {
                    return 0;
                }

                currentRegisterNumber++;

            }

        }

        return switch (aiGameState.getIntermediateOrientation()) {

            case "up" -> aiGameState.getIntermediateOrientationUpRating()[aiGameState.getIntermediatePosition().getY()][aiGameState.getIntermediatePosition().getX()];
            case "left" -> aiGameState.getIntermediateOrientationLeftRating()[aiGameState.getIntermediatePosition().getY()][aiGameState.getIntermediatePosition().getX()];
            case "down" -> aiGameState.getIntermediateOrientationDownRating()[aiGameState.getIntermediatePosition().getY()][aiGameState.getIntermediatePosition().getX()];
            case "right" -> aiGameState.getIntermediateOrientationRightRating()[aiGameState.getIntermediatePosition().getY()][aiGameState.getIntermediatePosition().getX()];
            default -> Integer.MAX_VALUE;

        };

    }

    private void initializeIntermediateVariables() {

        aiGameState.setIntermediatePosition(aiGameState.getCurrentPosition());
        aiGameState.setIntermediateBoardElement(aiGameState.getCurrentBoardElement());
        aiGameState.setIntermediateOrientation(aiGameState.getCurrentOrientation());
        aiGameState.setIntermediateOrientationUpRating(aiGameState.getOrientationUpRating());
        aiGameState.setIntermediateOrientationLeftRating(aiGameState.getOrientationLeftRating());
        aiGameState.setIntermediateOrientationDownRating(aiGameState.getOrientationDownRating());
        aiGameState.setIntermediateOrientationRightRating(aiGameState.getOrientationRightRating());
        aiGameState.setWasRebooted(false);

    }

}
