package utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
  private static FileHandler fileTxt;
  private static SimpleFormatter formatterTxt;
  private final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
   
  public Logger getLogger() {
    return logger;
  }

  public MyLogger() {
    
    Logger root = Logger.getLogger("");
    
    try {
      
      fileTxt = new FileHandler("log.txt");
    } catch (SecurityException | IOException e) {
      
      e.printStackTrace();
    }
    root.setLevel(Level.ALL);
    formatterTxt = new SimpleFormatter();
    fileTxt.setFormatter(formatterTxt);
    
    root.addHandler(fileTxt);
    
  }

}
