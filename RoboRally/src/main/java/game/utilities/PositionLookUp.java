package game.utilities;

import java.util.HashMap;

public class PositionLookUp {

    public static HashMap<Integer, Position> positionToXY = new HashMap<>();

    public static HashMap<Position, Integer> XYToPosition = new HashMap<>();

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
