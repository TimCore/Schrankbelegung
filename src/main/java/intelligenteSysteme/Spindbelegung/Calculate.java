package intelligenteSysteme.Spindbelegung;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tc0r3 on 25.10.2016.
 */
public class Calculate {

    private int col;
    private int row;
    private int timeTotal;
    private int timeUsed;
    private String path;
    private List<int[]> times;  //a list of int-Arrays, containing ID, time and encounters of all visitors
    private Gym gym;            //the gym,
    private int focusID;        //the id of the focused visitor
    private int currentID;      //the id of the next visitor


    /**
     * Constructor
     * @param c         number of columns
     * @param r         number of rows
     * @param time
     * @param txt
     */
    public Calculate(int c, int r, int time, String txt){
        this.col = c;
        this.row = r;
        this.timeTotal = time;
        this.timeUsed = 0;
        this.path = txt;
        this.gym = new Gym(c,r);
        this.currentID = 0;
    }

    public void run(){
        while(timeUsed < timeTotal){






            timeUsed += 10;
        }



        //alle 10 sekunden 1/10 wahrscheinlichkeit, zeit zählen
        //um 15 Uhr speziellen user hinzufügen und testen
        //list mit nutzer id, zeit und begegnungen anfertigen. begegnungen am ende hinzufügen (wenn nutzer fertig)
        //hauptperson (um 15 uhr) anhand id in liste bestimmen für ausgabe der begegnungen
        // --> focusvisitor id


    }


    /**
     *
     * @param list
     * @return
     */
    public Visitor createVisitor(List<int[]> list){                 //TODO noch bearbeiten! nicht sicher ob fertig
        //zeit holen
        int t = getTimeFromList();
        Visitor v = new Visitor(t);
        //Visitor erstellen
        //ausgeben
        return v;

    }

    /**
     * returns a free random locker
     * @return unused locker
     */
    public void choseRandom(int time, int visID){
        int r = (int) Math.random()*row;
        int c = (int) Math.random()*col;
        if(!gym.getLocker()[r][c].isUsed()){
            choseRandom(time, visID);             //if used try again
        }
        gym.choseLocker(r,c,time,visID);
    }

    /**
     * Selects a locker in an intelligent way
     */
    public void choseWisely(){

    }

    /**
     * Randomly takes a value from the list with the times a visitor stays at the gym and decrements its left amount. Removes it from the list if the amount reaches 0.
     * @return the time the actual visitor will stay at the gym
     */
    public int getTimeFromList(){
        int i = (int) Math.random()*times.size();
        int out = times.get(i)[0];
        times.get(i)[1]--;
        if(times.get(i)[1] == 0){
            times.remove(i);
        }
        return out;
    }

    /**
     * reads the .txt-file with the information about the durations of visits and saves it to a 2-dimensional int array
     * @param txt path to the "Belegungszeiten.txt"
     * @return 2-dimensional int-array with int[duration][frequency]
     */
    public List<int[]> readIn(String txt){
        List<int[]> out = new LinkedList<int[]>();
        try{
            File file = new File(txt);
            Scanner in = new Scanner(file);
            String ln = in.nextLine();      //ignore first line
            ln = in.nextLine();
            String[] str;
            int[] df = new int[2];
            while(ln != ""){
                str = ln.split(" ");
                df[0] = Integer.parseInt(str[0]);
                df[1] = Integer.parseInt(str[1]);
                out.add(df);
                ln = in.nextLine();
            }
        }catch (Exception e){
            System.out.println("File not found");
        }
        return out;
    }
}
