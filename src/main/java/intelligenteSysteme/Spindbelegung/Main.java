package intelligenteSysteme.Spindbelegung;

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

    public static void main(String[] args){
    	txt = Paths.get("").toAbsolutePath().toString()+File.separator+"doc"+File.separator+"Belegungszeiten.txt";
        Calculate cal = new Calculate(ROWS,COL,fullTime(), txt);
        cal.run();
        for(int[] i : cal.getTimes()){
        	System.out.println(i[0] + " : " + i[1]);
        }
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

