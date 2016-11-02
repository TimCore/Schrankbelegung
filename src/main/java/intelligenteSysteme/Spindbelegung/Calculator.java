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
    private int[] times;

	public Calculator(Config config){
	    //TODO Feste werte durch config ändern
        this.simulator= new Simulator(1000);
        this.gym= new Gym(2,75);
        this.config=config;
        this.random= new Random();
        readIn(config.getPATH_TO_VISIORLIST());

	}
	
	

    /**
     * returns a free random locker. Tries again if occupied 
     * @return unused locker
     */
    private Locker chooseRandomLocker(){
        Locker locker = this.gym.getFreeLockers().get(this.random.nextInt());
        return locker;
    }

    public void randomAlg(){
        int time = Main.fullTime();
        int id = 0;
        int localRandom;
        while(time>0){
            this.simulator.startSimulator();
            if(rndCheck()){
                localRandom= this.random.nextInt(times.length);
                Visitor visitor = new Visitor(times[localRandom], id);
                visitor.setLocker(chooseRandomLocker());
                this.simulator.addVisitor(visitor);
                id +=1;
            }
            time-=10;
        }



    }
    
    /**
     * Uses a random value to simulate a probability of 10% that a new visitor arrives. 
     * @return	true if value below 0.1
     */
    private boolean rndCheck(){
    	double rnd = Math.random();
    	if(rnd <= 0.1){
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
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String ln;
            reader.readLine();
            int timeId=0;
            String[] values;
            while ((ln=reader.readLine())!=null){
                timeId++;
            }
            reader.close();
            reader = new BufferedReader(new FileReader(txt));
            this.times= new int[timeId];
            timeId=0;
            reader.readLine();
            while ((ln=reader.readLine())!=null){
                this.times[timeId]=Integer.parseInt(ln.split(" ")[1]);
            }
            reader.close();
        }catch (Exception e){
            System.out.println("File not found");
        }
        return out;
    }

}
