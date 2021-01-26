package game.utilities;

import java.util.HashMap;

public class PositionLookUp {

    public static final HashMap<Integer, Position> positionToXY = createPositionToXY();

    public static final HashMap<Position, Integer> XYToPosition = createXYToPosition();

    private static HashMap<Integer, Position> createPositionToXY() {

        HashMap<Integer, Position> returnValue = new HashMap<>();
        int position = 0;

        for (int i = 0; i < 10; i++) {

            for (int j = 0; i < 13; j++) {

                returnValue.put(position, new Position(i, j));
                position++;

            }

        }

        return returnValue;

    }

    private static HashMap<Position, Integer> createXYToPosition() {

        HashMap<Position, Integer> returnValue = new HashMap<>();
        int position = 0;

        for (int i = 0; i < 10; i++) {

            for (int j = 0; i < 13; j++) {

                returnValue.put(new Position(i, j), position);
                position++;

            }

        }

        return returnValue;

    }

}
