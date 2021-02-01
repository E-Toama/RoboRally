package game.utilities;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int calculateDistance(Position position1, Position position2) {

        return Math.abs(position1.getX() - position1.x) + Math.abs(position1.getY() - position2.y);

    }

}
