package intelligenteSysteme.Spindbelegung;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;

/**
 * Reads the .txt-file 
 * Created by Tc0r3 on 19.10.2016.
 */
public class Main {

	private static String txt;
    private static int COL = 75;
    private static int ROWS = 2;
    private static int OPEN = 10;
    private static int CLOSE = 22;
    private static int REPEATS = 1;

    public static void main(String[] args) throws Exception {
    	if(args.length<1||args.length>1){
    	    throw new Exception("To few or to many argmunets");
        }
        org.json.simple.parser.JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new FileReader(args[0]));
        int row = Integer.parseInt(object.get("locker_rows").toString());
        int col = Integer.parseInt(object.get("locker_columns").toString());
        int lockers = Integer.parseInt(object.get("total_lockers").toString());
        int openTime = Integer.parseInt(object.get("gym_open_time").toString());
        int closeTime = Integer.parseInt(object.get("gym_close_time").toString());
        int repeats = Integer.parseInt(object.get("simulation_reapeats").toString());
        String path = object.get("path_to_visitorlist").toString();
        Config config = new Config(row,col,lockers,openTime,closeTime,repeats,path);

        Calculator  calculator = new Calculator(config);
        calculator.randomAlg();

    }

    /**
     * calculates the opening time per day
     * @return opening time per day in seconds
     */
    public static int fullTime(){
        int hours = CLOSE - OPEN;
        return hours * 60 * 60;
    }
    




    //txt auslesen DONE
    //mit rnd aus liste Zeiten nehmen (gleichmäßig verteilt)
    //alle 10 sekunden 1/10 Chance auf besucher
    //begegnungen zählen
    //chance berechnen, dass zielperson bei rnd Begegnung hat

}

