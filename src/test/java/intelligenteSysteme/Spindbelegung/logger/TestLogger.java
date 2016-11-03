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
        Logger logger = new Logger();
        logger.addLogFile("test.txt");
        logger.log("test.txt","Hallo welt");
        logger.writeLogs();
    }
}
