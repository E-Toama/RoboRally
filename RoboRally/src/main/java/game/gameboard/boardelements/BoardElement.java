package game.gameboard.boardelements;

import game.gameboard.FieldElement;
import game.gameboard.MapElement;

/**
 * A BoardElement represents a single tile/field of the GameBoard.
 * It can hold multiple values, e.g. a "Wall" and a "Laser".
 */
public class BoardElement {
    /**
     * The type-variable is needed for the message protocol and holds the String representation of the class name
     */
    private String type;
    private FieldElement[] fields;

    /**
     * Default Constructor for BoardElements without additional values, e.g. "Empty" or "Pit"
     */
    public BoardElement() {
        this.type = this.getClass().getSimpleName();
    }

    /**
     * Constructor for BoardElements with multiple entries, e.g. Wall and Laser
     * @param fields Array of field elements
     */
    public BoardElement(FieldElement[] fields) {
        this.type = this.getClass().getSimpleName();
        this.fields = fields;
    }

    public FieldElement[] getFields() {
        return fields;
    }

    /**
     * Creates a MapElement that is compatible with the protocol.
     * @param position The position of the field in the concrete GameBoard, counted from top-left to bottom right.
     * @return a MapElement which can be added to the map-Array of the protocol.
     */
    public MapElement createMapElement(int position) {
        return new MapElement(position, fields);
    }
}
