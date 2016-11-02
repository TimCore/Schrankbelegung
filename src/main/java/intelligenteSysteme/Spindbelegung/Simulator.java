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
    private LinkedList<Visitor> visitors;		//A List  


    /**
     * Constructor
     * @param timeLeft	The left time for this simulation
     */
    public Simulator(int timeLeft){
    	this.timeLeft = timeLeft;
    	this.encounters = new HashMap<Integer, Integer>();
    }
    
    /**
     * Starts the simulation. Decrements the timeamount of the simulator and all visitors by the skiptime-value. Add
     */
    public void StartSimulator(){
    	while(timeLeft > 0){
    		reduceTime(skipTimeValue);
    	}
    	for (Visitor v : visitors){
    		encounters.put(v.getID(), v.getEncounters());
			visitors.remove(v);
    	}
    }
    
    /**
     * reduces the time by the given value for the Simluator and every active visitor until he has left time.
     * When the vistior's time reaches 0 his id and the number of encounters are added to the HashMap encounters. 
     * Subsequently removes the visitor from the List of visitors 
     * @param value
     */
    public void reduceTime(int value){
    	this.timeLeft -= value;
    	for (Visitor v : visitors){
    		v.reduceTime(value);
    		if(!v.isTimeLeft()){
    			encounters.put(v.getID(), v.getEncounters());
    			visitors.remove(v);
    		}
    	}
    }

    


    
}
