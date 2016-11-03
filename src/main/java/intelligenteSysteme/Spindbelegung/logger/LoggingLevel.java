package intelligenteSysteme.Spindbelegung.logger;

import java.nio.file.Path;

/**
 * Created by sven on 03.11.16.
 */
public enum LoggingLevel {

    /**
     * System relevant actions
     */
    SYSTEM("SYSTEM","system.txt"),
    /**
     * Encounters for each visitor
     */
    ENCOUNTER("ENCOUNTER","encounters.txt"),
    /**
     * Encounters Focusperson
     */
    FOCUS("FOCUS","focus.txt"),
    /**
     * For testcases
     */
    TEST("TEST","test.txt");

    /**
     * Name of the LoggingLevel
     */
    private String name;
    /**
     * Name of the Logging file
     */
    private String logFileName;

    LoggingLevel(String name, String logFilePath) {
        this.name = name;
        this.logFileName=logFilePath;
    }

    public String getName() {
        return name;
    }

    public String getLogFileName() {
        return this.logFileName;
    }
}
