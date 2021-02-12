package utilities.messages;

/**
 * The class MapSelected represents the selection of the player of the map.
 * 
 */
public class MapSelected extends MessageBody {

  private final String[] map;

  /**
   * A constructor for initializing the chosen map.
   * 
   * @param map
   *          the chosen map
   */
  public MapSelected(String[] map) {

    this.map = map;

  }

  /**
   * The method returns the map that the player chose.
   * 
   * @return the player choice of the map
   */
  public String[] getMap() {
    return map;
  }

}
