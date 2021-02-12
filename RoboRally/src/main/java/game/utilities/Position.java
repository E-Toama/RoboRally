package game.utilities;

/**
 * This class represents the position of anything on the game board and it can calculate the distances between fields.
 * 
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructor for initializing the x and y coordinates.
     * 
     * @param x
     *         the x coordinates
     * @param y
     *         the y coordinates
     */
    public Position(int x, int y) {

        this.x = x;
        this.y = y;

    }

    /**
     * This method returns the x coordinates.
     * 
     * @return the x coordinates
     */
    public int getX() {
        return x;
    }

    /**
     * This method returns the y coordinates
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * This method calculates the distance between two different positions on the game board.
     * 
     * @param position1
     *          the first position
     * @param position2
     *          the second position
     *          
     * @return the distance between the two positions
     */
    public static int calculateDistance(Position position1, Position position2) {

        return Math.abs(position1.getX() - position2.getX()) + Math.abs(position1.getY() - position2.getY());

    }

}
