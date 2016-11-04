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
        Gym gym = new Gym(config.getLOCKER_ROWS(),config.getLOCKER_COLUMNS());
        this.simulator= new Simulator(Main.fullTime(),gym);
        this.gym= gym;
        this.config=config;
        this.random= new Random();
        this.times=readIn(config.getPATH_TO_VISIORLIST());
    }
	
	

    /**
     * returns a free random locker. Tries again if occupied 
     * @return unused locker
     */
    private Locker chooseRandomLocker(){
        if(this.gym.getFreeLockers().isEmpty()) return null;
        List<Locker> lockerList = this.gym.getFreeLockers();
        Locker locker= lockerList.remove(this.random.nextInt(lockerList.size()));
        locker.setCurrentlyInUse(true);
        return locker;
    }

    void randomAlg(){
        Logger.addLogFile(LoggingLevel.ENCOUNTER);
        Logger.log(LoggingLevel.SYSTEM,"Starte random alg");
        Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
        int time=0;
        int id;
        int localRandom;
        for(int i=config.getSIMLUATION_REPEATS();i>0;i--) {
            time=Main.fullTime();
            Logger.log(LoggingLevel.SYSTEM,"Zeit in stunden beträgt: "+time);
            id=0;
            Logger.log(LoggingLevel.SYSTEM,"Tag: "+i);
            Logger.log(LoggingLevel.SYSTEM,"--------------------------------");
            while (time > 0) {
                if (rndCheck()) {

                    Logger.log(LoggingLevel.SYSTEM, "Erstelle Visitor mit id: " + id);
                    localRandom = this.random.nextInt(this.times.length);
                    Visitor visitor = new Visitor(this.times[localRandom], id);
                    Logger.log(LoggingLevel.SYSTEM,"Visitor hat die zeit: "+this.times[localRandom]);
                    Locker locker = chooseRandomLocker();
                    //Focusperson um 15:00 uhr
                    if(time>=Main.fullTime()-18000)
                        this.simulator.setFocusID(id);
                    if (locker != null) {
                        Logger.log(LoggingLevel.SYSTEM, "Freien Locker gefunde für id: " + id);
                        visitor.setLocker(locker);
                        this.simulator.addVisitor(visitor);
                        id += 1;
                    } else
                        Logger.log(LoggingLevel.SYSTEM, "Keinen freien Locker gefunden für id: " + id);
                }
                //Hier unten angesetzt weil wir ja beim ersten umziehen auch checken müssen
                this.simulator.startSimulator();
                //TODO keine magic number
                time -= 10;
            }
            this.simulator.clearSimulator();
            Logger.writeLogs();
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
     * reads the .txt-file with the information about the durations of visits and saves it into {@link #times}
     * @param txt path to the "Belegungszeiten.txt"
     * @return the times we need
     */
    private int[] readIn(String txt){
        try{
            Logger.log(LoggingLevel.SYSTEM,"Beginne lesen der Zeiten");
            Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String ln;
            reader.readLine();
            int timeId=0;
            int multiTime=0;
            while ((ln=reader.readLine())!=null){
                timeId+=Integer.parseInt(ln.split(" ")[1]);
            }
            reader.close();
            reader = new BufferedReader(new FileReader(txt));
            int[] localTimes= new int[timeId];
            timeId=0;
            reader.readLine();
            while ((ln=reader.readLine())!=null){
                multiTime=Integer.parseInt(ln.split(" ")[1]);
                for(int i=0;i<multiTime;i++){
                    localTimes[timeId]=Integer.parseInt(ln.split(" ")[0]);
                    timeId++;
                }

            }
            reader.close();
            Logger.log(LoggingLevel.SYSTEM,"Zeiten eingelesen");
            Logger.log(LoggingLevel.SYSTEM,System.nanoTime());
            return localTimes;
        }catch (Exception e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return null;
    }

}
