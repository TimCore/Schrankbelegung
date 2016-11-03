package intelligenteSysteme.Spindbelegung.logger;

import org.junit.Test;

/**
 * Created by sven on 03.11.16.
 */
public class TestLogger {

    /**
     *
     */
    @Test
    public void testWriteLogger(){
        Logger.addLogFile(LoggingLevel.TEST);
        Logger.log(LoggingLevel.TEST,"Hallo welt");
        Logger.writeLogs();
    }
}
