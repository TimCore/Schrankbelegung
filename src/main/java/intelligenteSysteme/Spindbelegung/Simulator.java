package intelligenteSysteme.Spindbelegung;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tc0r3 on 25.10.2016.
 */
public class Simulator {

    private int col;
    private int row;
    private int timeTotal;
    private int timeUsed;
    private String path;
    private LinkedList<int[]> times;  	//a list of int-Arrays, containing ID, time and encounters of all visitors
    private Gym gym;            		//the gym,
    private int focusID;        		//the id of the focused visitor
    private int currentID;      		//the id of the next visitor
    private LinkedList<int[]> output;		//zu map aendern!
    


    /**
     * Constructor
     * @param r         number of rows
     * @param c         number of columns
     * @param time
     * @param txt
     */
    public Simulator(int r, int c, int time, String txt){
    	this.row = r;
    	this.col = c;
        this.timeTotal = time;
        this.timeUsed = 0;
        this.path = txt;
        this.gym = new Gym(c,r);
        this.currentID = 0;
        this.times = readIn(txt);
    }

    /**
     * adds 10 seconds to the used time until the gym closes. A new visitor arrives with a chance of 10% every 10 seconds.
     * 
     */
    public void run(){
        while(timeUsed < timeTotal){
        	timeUsed += 10;								
        	if(rndCheck()){
        		Visitor v = createVisitor(currentID);
        		chooseRandom(v.getTime(), v.getID());
        	}
        	if(timeUsed == 3000){						//focus person arrives at ~ 15:00 
        		this.focusID = this.currentID;			//saves the id of the focus person
        	}
        	this.output.addAll(gym.run());
        }
    }


    /**
     * Gets a time from a list and a id and creates a new visitor who stays for this time.
     * @return new Visitor
     */
    public Visitor createVisitor(int cID){
        int t = getTimeFromList();
        Visitor v = new Visitor(t, cID);
        currentID++;
        return v;
    }

    /**
     * returns a free random locker. Tries again if occupied 
     * @return unused locker
     */
    public void chooseRandom(int time, int visID){
        int r = (int) Math.random()*row;
        int c = (int) Math.random()*col;
        if(!gym.getLocker()[r][c].isUsed()){
            chooseRandom(time, visID);             //if used try again
        }
        gym.choseLocker(r,c,time,visID);
    }

    /**
     * Selects a locker in an intelligent way
     */
    public void chooseWisely(){					//TODO not implmented yet

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
     * Uses a random value to simulate a probability of 10% that a new visitor arrives. 
     * @return	true if value below 0.1
     */
    public boolean rndCheck(){
    	double rnd = Math.random();
    	if(rnd < 0.1){
    		return true;
    	}else{
    		return false;
    	}	
    }

    /**
     * reads the .txt-file with the information about the durations of visits and saves it to a 2-dimensional int array
     * @param txt path to the "Belegungszeiten.txt"
     * @return 2-dimensional int-array with int[duration][frequency]
     */
    public LinkedList<int[]> readIn(String txt){
        LinkedList<int[]> out = new LinkedList<int[]>();
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
    
    /**
     * Returns a list of all visitorIDs and their encounters.
     * @return LinkedList output, containing int-arrays
     */
    public LinkedList<int[]> getTimes(){
    	return this.output;
    }
}
