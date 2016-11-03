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
        this.config=config;
        this.simulator= new Simulator(Main.fullTime());
        this.gym= new Gym(config.getLOCKER_ROWS(),config.getLOCKER_COLUMNS());
        this.config=config;
        this.random= new Random();
        readIn(config.getPATH_TO_VISIORLIST());

	}
	
	

    /**
     * returns a free random locker. Tries again if occupied 
     * @return unused locker
     */
    private Locker chooseRandomLocker(){
        return this.gym.getFreeLockers().get(this.random.nextInt());
    }

    void randomAlg(){
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
            //TODO keine magic number
            time-=10;
        }



    }
    
    /**
     * Uses a random value to simulate a probability of 10% that a new visitor arrives. 
     * @return	true if value below 0.1
     */
    private boolean rndCheck(){
    	double rnd = Math.random();
        //TODO keine magic number
        return rnd <= 0.1;
    }

    /**
     * reads the .txt-file with the information about the durations of visits and saves it to a 2-dimensional int array
     * @param txt path to the "Belegungszeiten.txt"
     * @return 2-dimensional int-array with int[duration][frequency]
     */
    private void readIn(String txt){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String ln;
            reader.readLine();
            int timeId=0;
            String[] values;
            while ((ln=reader.readLine())!=null){
                timeId+=Integer.parseInt(ln.split(" ")[1]);
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
            e.printStackTrace();
        }
    }

}
