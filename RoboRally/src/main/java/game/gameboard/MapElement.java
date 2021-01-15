package game.gameboard;

/**
 * This class represents an element of the map transmitted via protocol.
 * It holds the position and a list of fieldtypes for a single tile on the game board.
 */
public class MapElement {
    private int position;
    private FieldElement[] field;

    /**
     * Constructor for MapElements
     * @param position The position of this tile in a concrete GameBoard, counted from top-left to bottom right.
     * @param fields A list of BoardElements found on this tile.
     */
    public MapElement(int position, FieldElement[] fields) {
        this.position = position;
        this.field = fields;
    }

    public int getPosition() {
        return position;
    }

    public FieldElement[] getField() {
        return field;
    }

}
