package model.network;

/**
 * Simple implementation of Edit-Distance to provide suggestions to the users regarding the game commands
 * Used as static method in UserThread to generate a more customized default answer from server to unknown command.
 * @author Josef
 */
public class EditDistance {

    //List of all game commands
    private static final String[] gameCommands = {
            "!CREATEGAME",
            "!JOINGAME",
            "!STARTGAME",
            "?STATS",
            "?DISCARDED",
            "!CHOOSEANYPLAYER",
            "!CHOOSEANOTHERPLAYER",
            "!GUESSCARD",
            "@ALL ",
            "@USERNAME ",
            "!BYE",
            "!PLAYLEFTCARD",
            "!PLAYRIGHTCARD"
    };


    /**
     * Returns a suggested game command in case the user misspelled or mistyped a command
     * @param s user input
     * @return the game command supposedly closest to the user input
     */
    public synchronized static String getClosestString(String s) {
        if (s.isEmpty()) {
            return "Could not detect your command, please try again";
        }
        // Set minimum to worst case edit distance:
        int min = s.length();
        String result = "";
        for (String str : gameCommands) {
            //Changing user String to Uppercase increases matching chance (since all commands are uppercase)
            int distance = calculate(str, s.toUpperCase());
            if (distance < min) {
                min = distance;
                result = str;
            }
        }
        return "Sorry, wrong command. Did you mean: " + result;
    }

    /**
     * Basic iterative implementation of Levenshtein Distance with Matrix.
     * No further optimizations carried out, since number of Strings to compare is very small.
     * @param s first String to compare
     * @param t second String to compare
     * @return edit distance between two strings as Integer (the smaller the closer)
     */
    static int calculate(String s, String t) {
        //Create matrix of |s+1| X |t+1|
        int[][] matrix = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                //Fill first row/column with lengths of strings
                if (i == 0) {
                    matrix[i][j] = j;
                } else if (j == 0) {
                    matrix[i][j] = i;
                } else {
                    matrix[i][j] =
                            minimumOfThree(
                                    // If diagonal characters are equal: add nothing, else add 1
                                    matrix[i - 1][j - 1] + (s.charAt(i - 1) == t.charAt(j - 1) ? 0 : 1),  // diagonal value
                                    matrix[i - 1][j] + 1,                                                 // top value + 1
                                    matrix[i][j - 1] + 1);                                                // left value + 1
                }
            }
        }
        //Return value at bottom right of matrix
        return matrix[s.length()][t.length()];
    }

    private static int minimumOfThree(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
