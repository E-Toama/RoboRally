package game.gameboard;

/**
 * Marker Interface for Message-Formattig of "GameStarted"-Message
 */
public class FieldElement {

    /**
     * The type-variable is needed for the message protocol and holds the String representation of the class name
     */
    private String type;

    public FieldElement() {
        this.type = this.getClass().getSimpleName();
    }

    public String getType() {
        return type;
    }
}
