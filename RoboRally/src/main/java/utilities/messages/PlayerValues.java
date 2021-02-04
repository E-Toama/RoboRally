package utilities.messages;

/**
 * The Class PlayerValues is for saving the player name and the figure that he
 * chose.
 * 
 * @author
 */
public class PlayerValues extends MessageBody {

  private final String name;
  private final int figure;

  /**
   * A Constructor to initialize the name and figure that the player chose.
   * 
   * @param name
   *          is the name if the player
   * @param figure
   *          is the figure that the player chose
   */
  public PlayerValues(String name, int figure) {

    this.name = name;
    this.figure = figure;

  }

  /**
   * The method returns  the player name.
   * @return the player name
   */
  public String getName() {
    return name;
  }

  /**
   * The Method returns the robot figure that the player chose.
   * @return the robot figure
   */
  public int getFigure() {
    return figure;
  }
}
