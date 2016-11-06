package intelligenteSysteme.Spindbelegung;

import intelligenteSysteme.Spindbelegung.logger.Logger;
import intelligenteSysteme.Spindbelegung.logger.LoggingLevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Random;

public class Calculator {
	
	/**
	 * The gym contains all lockers 
	 */
	private final Gym gym;
	
	/**
	 * The Simulator, used to start the simulation
	 */
	private final Simulator simulator;
	
	/**
	 * The Config file, contains information about opening times, the number of repeats, 
	 * the size of the lockers and the path of the txt-file containing data about the times 
	 */
    private Config config;
    
    /**
     * A Random to get random positions
     */
    private final Random random;

    /**
     * Contains the times of the file "Belegungszeiten.txt"
     */
    private  int[] times;

    /**
     * Constructor
     * @param config	config-file contains the data for the simulation
     */
	public Calculator(Config config){
        this.config = config;
        Gym gym = new Gym(config.getLOCKER_ROWS(),config.getLOCKER_COLUMNS());
        this.simulator = new Simulator(Main.fullTime(),gym);
        this.gym = gym;
        this.config = config;
        this.random = new Random();
        this.times = readIn(config.getPATH_TO_VISIORLIST());
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
        this.gym.addUsedLocker(locker);
        return locker;
    }
    
    /**
     * Chooses a locker in the first row with Modulo 2 if possible. 
     * If not, takes a locker Modulo 2 of the second row. 
     * If the Columns (Modulo2) in both rows are occupied, a free locker will be choosen with random.  
     * @return the new Locker choosen by the algorithm
     */
    private Locker chooseWithHoldingDistance(){							//TODO letzte Wahl der Spalte speichern um Laufzeit zu sparen?
    	if(this.gym.getFreeLockers().isEmpty()) return null;
    	for(int i = 0; i < config.getLOCKER_COLUMNS()-1; i+=2){
    		if(this.gym.getFreeLockers().contains(this.gym.getLocker()[0][i])){
    			Locker l = this.gym.getFreeLockers().remove(this.gym.getFreeLockers().indexOf(this.gym.getLocker()[0][i]));
    			this.gym.getUsedLockers().add(l);
    			l.setCurrentlyInUse(true);
    			return l;
        	}
    	}
    	for(int i = 0; i < config.getLOCKER_COLUMNS()-1; i+=2){
    		if(this.gym.getFreeLockers().contains(this.gym.getLocker()[1][i])){
    			Locker l = this.gym.getFreeLockers().remove(this.gym.getFreeLockers().indexOf(this.gym.getLocker()[1][i]));
    			this.gym.getUsedLockers().add(l);
    			l.setCurrentlyInUse(true);
    			return l;
    		}
    	}
    	return chooseRandomLocker();								
    }

    /**
     * 
     */
    void randomAlg(){								//TODO Gucken ob anderer name besser passt, seit ncoht random sondern chooseWithHoldingDistance() aufgerufen wird
        Logger.addLogFile(LoggingLevel.ENCOUNTER);
        Logger.log(LoggingLevel.SYSTEM,"Starte random alg");
        int time;
        int id;
        int localRandom;
        boolean focusPersonInUse=false;
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
                    //Locker locker = chooseWithHoldingDistance();

                    Locker locker = chooseRandomLocker();
                    //Focusperson um 15:00 uhr
                    if(time<=Main.fullTime()-18000&&!focusPersonInUse) {
                        System.out.println("Fokus: "+id);
                        this.simulator.setFocusID(id);
                        Logger.log(LoggingLevel.SYSTEM, "Fokusperson ist: " + id);
                        focusPersonInUse=true;
                    }
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
            focusPersonInUse=false;
            this.simulator.clearSimulator();
            Logger.writeLogs();
        }
    }
    
    /**
     * Uses a random value to simulate a probability of 10% that a new visitor arrives. 
     * @return	true if value below 0.1
     */
    private boolean rndCheck(){
    	return  Math.random()<=0.1;
    }

    /**
     * reads the .txt-file with the information about the durations of visits and saves it into {@link #times}
     * @param txt path to the "Belegungszeiten.txt"
     * @return the times we need
     */
    private int[] readIn(String txt){
        try{
            Logger.log(LoggingLevel.SYSTEM,"Beginne lesen der Zeiten");
            BufferedReader reader = new BufferedReader(new FileReader(txt));
            String ln;
            reader.readLine();
            int timeId=0;
            int multiTime;
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
                    localTimes[timeId]=Integer.parseInt(ln.split(" ")[0])*60;
                    timeId++;
                }
            }
            reader.close();
            Logger.log(LoggingLevel.SYSTEM,"Zeiten eingelesen");
            return localTimes;
        }catch (Exception e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        return null;
    }

}
