package utilities.messages;

/**
 * This class is responsible for saving different errors.
 * 
 *
 */
public class Error extends MessageBody {

  private final String error;

  /**
   * Constructor to initialize the error.
   * 
   * @param error
   *          the error
   */
  public Error(String error) {

    this.error = error;

  }

  /**
   * The method returns the error.
   * 
   * @return the error
   */
  public String getError() {
    return error;
  }
}
