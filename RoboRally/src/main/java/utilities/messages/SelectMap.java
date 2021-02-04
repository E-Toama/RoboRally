package utilities.messages;

/**
 * The class SelectMap represents the maps that can be played.
 * 
 * @author
 */
public class SelectMap extends MessageBody {

  private final String[] availableMaps;

  /**
   * A constructor for initializing the available maps.
   * 
   * @param availableMaps
   *          is the array of existing maps
   */
  public SelectMap(String[] availableMaps) {

    this.availableMaps = availableMaps;

  }

  /**
   * The method returns the list of the available maps.
   * 
   * @return array of available maps
   */
  public String[] getAvailableMaps() {
    return availableMaps;
  }
}
