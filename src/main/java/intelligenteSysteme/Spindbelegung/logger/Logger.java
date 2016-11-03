package intelligenteSysteme.Spindbelegung.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sven on 03.11.16.
 */
public class Logger {

    /**
     * String for the Filename StrinBuilder to log the strings
     */
    private static Map<LoggingLevel,StringBuilder> loggings = new HashMap<>();

    private static final String LOGFILEPATH= Paths.get("","src","main","resource").toAbsolutePath().toString();



    /**
     * Adds an logfile
     * @param fileName  Name of the logfile
     */
    public static void addLogFile(LoggingLevel fileName){
        loggings.put(fileName,new StringBuilder());
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  Which filelogger we want to use
     * @param message   Which message we want to log
     */
    public static void log(LoggingLevel fileName,String message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  Which filelogger we want to use
     * @param message   Which message we want to log
     */
    public static void log(LoggingLevel fileName,boolean message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  Which filelogger we want to use
     * @param message   Which message we want to log
     */
    public static void log(LoggingLevel fileName,long message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  Which filelogger we want to use
     * @param message   Which message we want to log
     */
    public static void log(LoggingLevel fileName,int message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Log the message in the cache of this file
     * @param fileName  Which filelogger we want to use
     * @param message   Which message we want to log
     */
    public static void log(LoggingLevel fileName,char message){
        loggings.get(fileName).append(message).append("\n");
    }

    /**
     * Write all files in the given filenames by creating new ones
     */
    public static void writeLogs(){
        loggings.forEach((key,value)->{
            Path filePath = Paths.get(LOGFILEPATH+File.separator+key.getLogFileName());
            try (FileWriter writer = new FileWriter(filePath.toString())){
                if (!Files.exists(filePath)) {
                    Files.createFile(filePath);
                }
                System.out.println(value.toString());
                writer.write(value.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
