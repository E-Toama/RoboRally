package game.utilities;

import java.util.HashMap;

/**
 * This class represents a lookup table from an x, y coordinates to a number.
 * 
 */
public class PositionLookUp {

    public static HashMap<Integer, Position> positionToXY = new HashMap<>();

    public static HashMap<Position, Integer> XYToPosition = new HashMap<>();

    /**
     * The method creates a map of the game board.
     */
    public static void createMaps() {

        int position = 0;

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 13; j++) {

                Position xy = new Position(j, i);

                positionToXY.put(position, xy);
                XYToPosition.put(xy, position);
                position++;

            }

        }

    }

}
