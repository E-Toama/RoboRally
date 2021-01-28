package utilities;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * Logger Class to log the project.
 *
 */
public class MyLogger {
  private static FileHandler fileTxt;
  private static SimpleFormatter formatterTxt;
  private Logger logger = Logger.getLogger("");
   

  public MyLogger(String loggerName) {    
    
    try {
      fileTxt = new FileHandler(loggerName + ".log");
    } catch (SecurityException | IOException e) {
      
      e.printStackTrace();
    }
    logger.setLevel(Level.INFO);
    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    
    logger.addHandler(fileTxt);
    
  }
  
  public Logger getLogger() {
    return logger;
  }

}
