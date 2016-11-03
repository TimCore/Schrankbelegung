package intelligenteSysteme.Spindbelegung;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

/**
 * Reads the .txt-file 
 * Created by Tc0r3 on 19.10.2016.
 */
public class Main {

    private static Config config;

    public static void main(String[] args) throws Exception {
    	if(args.length<1||args.length>1){
    	    throw new Exception("To few or to many argmunets");
        }
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(new FileReader(args[0]));
        int row = object.get("locker_rows").getAsInt();
        int col = object.get("locker_columns").getAsInt();
        int lockers = object.get("total_lockers").getAsInt();
        int openTime = object.get("gym_open_time").getAsInt();
        int closeTime = object.get("gym_close_time").getAsInt();
        int repeats = object.get("simulation_reapeats").getAsInt();
        String path = object.get("path_to_visitorlist").getAsString();
        config = new Config(row,col,lockers,openTime,closeTime,repeats,path);

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
    




    //txt auslesen DONE
    //mit rnd aus liste Zeiten nehmen (gleichmäßig verteilt)
    //alle 10 sekunden 1/10 Chance auf besucher
    //begegnungen zählen
    //chance berechnen, dass zielperson bei rnd Begegnung hat

}

