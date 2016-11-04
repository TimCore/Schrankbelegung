package intelligenteSysteme.Spindbelegung;

import intelligenteSysteme.Spindbelegung.logger.Logger;
import intelligenteSysteme.Spindbelegung.logger.LoggingLevel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Tc0r3 on 25.10.2016.
 */
public class Simulator {

    private int timeLeft;						//the amount of left time in seconds
    private int skipTimeValue;					//the value in seconds for each timeskip
    private int focusID;        				//the id of the focused visitor
    private int currentID;      				//the id of the next visitor
    private Map<Integer, Integer> encounters;	//Contains the visitorID and 	
    private Map<Integer,Visitor> visitors;	//A List
	private Gym gym;

    /**
     * Constructor
     * @param timeLeft	The left time for this simulation
     */
    public Simulator(int timeLeft,Gym gym){
		//TODO Magic number
		this.skipTimeValue=10;
    	this.timeLeft = timeLeft;
    	this.encounters = new HashMap<>();
		this.visitors= new HashMap<>();
		this.gym=gym;
    }


    
    /**
     * Starts the simulation. Decrements the timeamount of the simulator and all visitors by the skiptime-value. Add
     */
    public void startSimulator(){
		reduceTime(skipTimeValue);
    }

	/**
	 * Clears all entrys and set encounters
	 */
	public void clearSimulator() {
		visitors.values().forEach(v-> encounters.put(v.getID(),v.getEncounters()));
		visitors.clear();
		encounters.forEach((key,value)->{
			Logger.log(LoggingLevel.ENCOUNTER,key+" "+value);
			if(key==focusID){
				Logger.log(LoggingLevel.FOCUS,value);
			}
		});
		encounters.clear();
		this.gym.clearGym();
	}



    
    /**
     * reduces the time by the given value for the Simluator and every active visitor until he has left time.
     * When the vistior's time reaches 0 his id and the number of encounters are added to the HashMap encounters. 
     * Subsequently removes the visitor from the List of visitors 
     * @param value
     */
    public void reduceTime(int value){
    	this.timeLeft -= value;
		List<Visitor> noTimesLeft = new LinkedList<>();
    	for (Visitor v : visitors.values()){
    		if(!v.isTimeLeft()){
				noTimesLeft.add(v);
    		}
			v.reduceTime(value);
    	}
    	for (Visitor v : noTimesLeft){
			visitors.remove(v.getID());
			Logger.log(LoggingLevel.SYSTEM,"Visitor "+v.getID()+" verlässt das Gym");
			encounters.put(v.getID(), v.getEncounters());
			this.gym.freeLocker(v.getLocker());
			visitors.remove(v.getID());
		}
    }


    public  synchronized void addVisitor(Visitor visitor) {
		this.visitors.put(visitor.getID(),visitor);
	}

	private synchronized Visitor removeVisitor(Visitor visitor){
		return this.visitors.remove(visitor.getID());
	}

	public void setFocusID(int id){
		this.focusID=id;
	}

    
}
