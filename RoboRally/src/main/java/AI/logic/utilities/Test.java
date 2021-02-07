package AI.logic.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        String[] cards = {"MoveOne", "MoveOne", "MoveOne", "MoveTwo", "RightTurn", "RightTurn", "LeftTurn", "LeftTurn", "MoveThree"};

        List<String[]> test = getPossibleRegisters(cards);

        System.out.println(test.size());

        for (String[] possibleRegister : test) {

            System.out.println("Next Register: ");

            for (String card : possibleRegister) {

                System.out.println(card);

            }

        }

    }

    private static List<String[]> getPossibleRegisters(String[] cards) {

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

}
