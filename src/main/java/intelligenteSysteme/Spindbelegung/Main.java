package intelligenteSysteme.Spindbelegung;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import intelligenteSysteme.Spindbelegung.logger.Logger;
import intelligenteSysteme.Spindbelegung.logger.LoggingLevel;

import java.io.FileReader;

/**
 * Main-class, initializes the Config-Class from the config.json and starts the Calculator.
 */
public class Main {

	/**
	 * config-file, contains informations for the simulation like the opening times or the path to the visitorlist
	 */
    private static Config config;

    public static void main(String[] args) throws Exception {
    	if(args.length<1||args.length>1){
    	    throw new Exception("To few or to many argmunets");
        }
        Logger.cleanLogFiles();
        Logger.addLogFile(LoggingLevel.SYSTEM);
        Logger.addLogFile(LoggingLevel.FOCUS);
        Logger.log(LoggingLevel.SYSTEM,"Starte Programm");
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(new FileReader(args[0]));
        int row = object.get("locker_rows").getAsInt();
        int col = object.get("locker_columns").getAsInt();
        int lockers = object.get("total_lockers").getAsInt();
        int openTime = object.get("gym_open_time").getAsInt();
        int closeTime = object.get("gym_close_time").getAsInt();
        int repeats = object.get("simulation_reapeats").getAsInt();
        int timeSkipValue = object.get("time_skip_value").getAsInt();
        String path = object.get("path_to_visitorlist").getAsString();
        config = new Config(row,col,lockers,closeTime,openTime,repeats,timeSkipValue,path);
        Logger.log(LoggingLevel.SYSTEM,"Config gelesen");
        Calculator  calculator = new Calculator(config);
        calculator.randomAlg();

    }
    
    /**
     * calculates the opening time per day
     * @return opening time per day in seconds
     */
    static int fullTime(){
        int hours = config.getGYM_CLOSE_TIME() - config.getGYM_OPEN_TIME();
        return hours * 60 * 60;
    }

}

