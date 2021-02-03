package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Logger Class to log the project.
 */
public class MyLogger {

  private static Logger LOGGER;

  /**
   * Constructor to initialize the logger.
   */
  public MyLogger() {

    LOGGER = Logger.getLogger("RoboRallyLogger");
    try {
      File logging = new File(
          "../RoboRally/src/main/java/utilities/logging.properties");
      InputStream fileInputStream = new FileInputStream(logging);
      LogManager.getLogManager().readConfiguration(fileInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * The Method returns the logger.
   * 
   * @return the logger
   */
  public Logger getLogger() {
    return LOGGER;
  }

}
