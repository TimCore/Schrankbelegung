package intelligenteSysteme.Spindbelegung;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class Calculator {
	
	private Gym gym;
	private  Simulator simulator;
    private Config config;
    private Random random;
    /**
     * The times from the txt fle
     */
    private Map<Integer,Integer> times;

	public Calculator(Config config){
	    //TODO Feste werte durch config ändern
        this.simulator= new Simulator(1000);
        this.gym= new Gym(2,75);
        this.config=config;
        this.times= new HashMap<>();
        this.random= new Random();
	}
	
	
	/*
    /**
     * returns a free random locker. Tries again if occupied 
     * @return unused locker
     */
    public void chooseRandom(int time, int visID){
        int r = this.random.nextInt(0,this.config);
        int c = this.random.nextInt();
        if(){
            chooseRandom(time, visID);             //if used try again
        }
        gym.choseLocker(r,c);
    }

    public void randomAlg(){
        while()



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
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String ln="";      //ignore first line
            int timeId=0;
            int time=0;
            String[] values;
            while ((ln=reader.readLine())!=null){
                values=ln.split(" ");
                timeId=Integer.parseInt(values[0]);
                time=Integer.parseInt(values[1]);
                times.put(timeId,time);
            }
        }catch (Exception e){
            System.out.println("File not found");
        }
        return out;
    }

}
