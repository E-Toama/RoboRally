import server.network.TestMessages;
import tablebases.CSVHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class Permutations {
    public static void main(String[] args) {

        ArrayList<String[]> possibleCombinations = new ArrayList<>();
        String[] arr = TestMessages.testCardsForProgrammingView;
        //combinations(arr, 5, 0, new String[5]);

        int[][] array = CSVHandler.convertCSVtoArray("/home/ada/IdeaProjects/vp-neidische-narwale/RoboRally/src/test/java/tablebases/TestTable.csv");
        System.out.println(Arrays.deepToString(array));
    }

    static void combinations(String[] arr, int len, int startPosition, String[] result) {
        if (len == 0) {
            System.out.println(Arrays.toString(result));
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];

            combinations(arr, len - 1, i + 1, result);
        }
    }
}
