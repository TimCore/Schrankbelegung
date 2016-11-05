package intelligenteSysteme.Spindbelegung;

import intelligenteSysteme.Spindbelegung.logger.Logger;
import intelligenteSysteme.Spindbelegung.logger.LoggingLevel;

import java.util.*;

/**
 * Created by Tc0r3 on 25.10.2016.
 */
public class Simulator {

    private final int skipTimeValue;					//the value in seconds for each timeskip
    private int focusID;        				//the id of the focused visitor
    private int encounters=0;	//Contains the visitorID and
	private int focusEncounter=0;
    private final Map<Integer,Visitor> visitors;	//A List
	private final Gym gym;

    /**
     * Constructor
     * @param timeLeft	The left time for this simulation
     */
    public Simulator(int timeLeft,Gym gym){
		//TODO Magic number
		this.skipTimeValue=10;
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
		for(Visitor v : visitors.values()){
			Logger.log(LoggingLevel.ENCOUNTER,v.getID()+" "+v.getEncounters());
			this.encounters+=v.getEncounters();
		}
		this.gym.clearGym();
	}



    
    /**
     * reduces the time by the given value for the Simluator and every active visitor until he has left time.
     * When the vistior's time reaches 0 his id and the number of encounters are added to the HashMap encounters. 
     * Subsequently removes the visitor from the List of visitors 
     * @param value	The time we want to reduce
     */
	private void reduceTime(int value){
        List<Integer> noTimeLeft = new LinkedList<>();
    	for (int key : visitors.keySet()) {
			Visitor v = visitors.get(key);
			if (!v.isTimeLeft()) {
				noTimeLeft.add(v.getID());
				visitors.put(key,null);
				Logger.log(LoggingLevel.SYSTEM, "Visitor " + v.getID() + " verlässt das Gym");
				Logger.log(LoggingLevel.ENCOUNTER, v.getID() + " " + v.getEncounters());
				this.gym.freeLocker(v.getLocker());
				if (v.getID() == focusID) {
					focusEncounter += v.getEncounters();
				}
			}else{
				v.reduceTime(value);
			}

		}
        noTimeLeft.forEach(visitors::remove);
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

	public int getEncounters() {
		return encounters;
	}

	public int getFocusEncounter() {
		return focusEncounter;
	}
}
