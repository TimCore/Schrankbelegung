package intelligenteSysteme.Spindbelegung.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * The Logger is used to save the data to files.
 */
public class Logger {

    /**
     * String for the Filename StringBuilder to log the strings
     */
    private static final Map<LoggingLevel,StringBuilder> loggings = new HashMap<>();

    /**
     * The Path of the logfile
     */
    private static final String LOGFILEPATH = Paths.get("","src","main","resource").toAbsolutePath().toString();



    /**
     * Adds a logfile
     * @param fileName  Name of the logfile
     */
    public static void addLogFile(LoggingLevel fileName){
        loggings.put(fileName,new StringBuilder());
    }

    /**
     * Logs the message in the cache of this file
     * @param fileName  The filelogger we want to use
     * @param message   The message we want to log
     */
    public static void log(LoggingLevel fileName,String message){
        if(fileName==LoggingLevel.SYSTEM){
            loggings.get(fileName).append(System.nanoTime()).append(" : ").append(message).append("\n");
        }else
            loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Logs the message in the cache of this file
     * @param fileName  The filelogger we want to use
     * @param message   The message we want to log
     */
    public static void log(LoggingLevel fileName,boolean message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  The filelogger we want to use
     * @param message   The message we want to log
     */
    public static void log(LoggingLevel fileName,long message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  The filelogger we want to use
     * @param message   The message we want to log
     */
    public static void log(LoggingLevel fileName,int message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  The filelogger we want to use
     * @param message   The message we want to log
     */
    public static void log(LoggingLevel fileName,char message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Writes all files in the given filenames by creating new ones
     */
    public static void writeLogs(){
        loggings.forEach((key,value)->{
            Path filePath = getPathForEnum(key);
            try (FileWriter writer = new FileWriter(filePath.toString())){
                if (!Files.exists(filePath)) {
                    Files.createFile(filePath);
                }
                writer.append(value.toString());
                value = new StringBuilder(value.length());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Set the content of the logfiles to empty also creates the logfiles if needed
     */
    public static void cleanLogFiles(){
        createLogFiles();
        Stream.of(LoggingLevel.values()).forEach(level->{
            Path filePath = getPathForEnum(level);
            try (FileWriter writer = new FileWriter(filePath.toString())) {
                writer.write(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Gets the path from an {@link LoggingLevel}
     * @param level     Enum we want the Path to the logfile
     * @return          Path representing the logfile
     */
    private static Path getPathForEnum(LoggingLevel level){
        return Paths.get(LOGFILEPATH+File.separator+level.getLogFileName());
    }

    /**
     * Creates all Logfiles from {@link LoggingLevel} if not already created
     */
    private static void createLogFiles(){
        Stream.of(LoggingLevel.values()).forEach(level->{
            Path filePath = getPathForEnum(level);
            if(Files.exists(filePath)){
                try {
                    Files.delete(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
