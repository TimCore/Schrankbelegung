package intelligenteSysteme.Spindbelegung;

import java.util.HashMap;
import java.util.LinkedList;
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


    /**
     * Constructor
     * @param timeLeft	The left time for this simulation
     */
    public Simulator(int timeLeft){
    	this.timeLeft = timeLeft;
    	this.encounters = new HashMap<>();
		this.visitors= new HashMap<>();
    }
    
    /**
     * Starts the simulation. Decrements the timeamount of the simulator and all visitors by the skiptime-value. Add
     */
    public void startSimulator(){
    	while(timeLeft > 0) {
			reduceTime(skipTimeValue);
		}
    	visitors.values().stream().parallel().forEach(v->{
    		encounters.put(v.getID(),v.getEncounters());
		});
		visitors.clear();
    }
    
    /**
     * reduces the time by the given value for the Simluator and every active visitor until he has left time.
     * When the vistior's time reaches 0 his id and the number of encounters are added to the HashMap encounters. 
     * Subsequently removes the visitor from the List of visitors 
     * @param value
     */
    public void reduceTime(int value){
    	this.timeLeft -= value;
    	for (Visitor v : visitors.values()){
    		v.reduceTime(value);
    		if(!v.isTimeLeft()){
    			encounters.put(v.getID(), v.getEncounters());
    			removeVisitor(v);
    		}
    	}
    }


    public  synchronized void addVisitor(Visitor visitor) {
		this.visitors.add(visitor.getID(),visitor);
	}

	private synchronized Visitor removeVisitor(Visitor visitor){
		this.visitors.remove(visitor.getID());
	}


    
}
