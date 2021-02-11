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
      String localDir = System.getProperty("user.dir");
      File logging = new File(localDir + "/RoboRally/src/main/java/utilities/logging.properties");
//      File logging = new File("logging.properties");
      InputStream fileInputStream = new FileInputStream(logging);
//      InputStream fileInputStream = getClass().getResourceAsStream("/logging.properties");
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
