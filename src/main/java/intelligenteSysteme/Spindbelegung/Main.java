package intelligenteSysteme.Spindbelegung;

import jdk.nashorn.api.scripting.JSObject;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

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
        File configFile = new File(args[0]);
        
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

