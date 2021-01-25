package utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
  private static FileHandler fileTxt;
  private static SimpleFormatter formatterTxt;

  public static void setup() throws IOException {
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Check what setLevel does?
    fileTxt = new FileHandler("Logging.txt");

    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    logger.addHandler(fileTxt);
  }

}
