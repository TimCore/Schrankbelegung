package intelligenteSysteme.Spindbelegung;



import intelligenteSysteme.Spindbelegung.logger.Logger;
import intelligenteSysteme.Spindbelegung.logger.LoggingLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Random;

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
        if(this.gym.getFreeLockers().isEmpty()) return null;
        List<Locker> locker = this.gym.getFreeLockers();
        return locker.get(this.random.nextInt(locker.size()));
    }

    void randomAlg(){
        Logger.addLogFile(LoggingLevel.ENCOUNTER);
        Logger.log(LoggingLevel.SYSTEM,"Starte random alg");
        Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
        int time = Main.fullTime();
        Logger.log(LoggingLevel.SYSTEM,"Zeit in stunden beträgt:"+time);
        int id = 0;
        int localRandom;
        while(time>0){
            this.simulator.startSimulator();
            Logger.log(LoggingLevel.SYSTEM,"Checke random");
            if(rndCheck()){
                Logger.log(LoggingLevel.SYSTEM,"Erstelle Visitor mit id:"+id);
                localRandom= this.random.nextInt(times.length);
                Visitor visitor = new Visitor(times[localRandom], id);
                Locker locker = chooseRandomLocker();
                if(locker!=null) {
                    Logger.log(LoggingLevel.SYSTEM,"Freien Locker gefunde für id:"+id);
                    visitor.setLocker(chooseRandomLocker());
                    this.simulator.addVisitor(visitor);
                    id += 1;
                }else
                    Logger.log(LoggingLevel.SYSTEM,"Keinen freien Locker gefunden für id"+id);
            }
            //TODO keine magic number
            time-=10;
        }
        this.simulator.clearSimulator();

        Logger.log(LoggingLevel.SYSTEM,"Random fertig");
        Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
    }
    
    /**
     * Uses a random value to simulate a probability of 10% that a new visitor arrives. 
     * @return	true if value below 0.1
     */
    private boolean rndCheck(){
    	double rnd = Math.random();
        Logger.log(LoggingLevel.SYSTEM,"Random war:"+rnd);
        //TODO keine magic number
        return rnd <= 0.1;
    }

    /**
     * reads the .txt-file with the information about the durations of visits and saves it into {@link #times}
     * @param txt path to the "Belegungszeiten.txt"
     * @return 2-dimensional int-array with int[duration][frequency]
     */
    private void readIn(String txt){
        try{
            Logger.log(LoggingLevel.SYSTEM,"Beginne lesen der Zeiten");
            Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
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
            Logger.log(LoggingLevel.SYSTEM,"Zeiten eingelesen");
            Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
        }catch (Exception e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

}
