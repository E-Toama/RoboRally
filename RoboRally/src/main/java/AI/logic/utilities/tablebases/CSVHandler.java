package AI.logic.utilities.tablebases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVHandler {

    public static int[][] convertCSVtoArray(String fileName) {

        int[][] result;
        int columns;
        int rows;
        ArrayList<String> lines = new ArrayList<>();

        BufferedReader br;
        String singleLine;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((singleLine = br.readLine()) != null) {
                lines.add(singleLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        columns = lines.size();
        rows = lines.get(0).split(";").length;
        result = new int[columns][rows];

        for (int i = 0; i < columns; i++) {
            String[] cells = lines.get(i).split(";");
            for (int j = 0; j < rows; j++) {
                result[i][j] = Integer.parseInt(cells[j]);
            }
        }

        return result;
    }

}
