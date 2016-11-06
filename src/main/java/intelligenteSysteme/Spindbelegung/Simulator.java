package intelligenteSysteme.Spindbelegung;

import intelligenteSysteme.Spindbelegung.logger.Logger;
import intelligenteSysteme.Spindbelegung.logger.LoggingLevel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Simulator is used to run the simulation and manage the important values for it.  
 */
public class Simulator {

    /**
     * The value in seconds for each timeskip
     */
    private final int skipTimeValue;
    
    /**
     * The id of the focused visitor
     */
    private int focusID;  
    
    /**
     * The value of encounters since the simulation started
     */
    private int encounters=0;
    
    /**
     * Encounters of the focused visitor
     */
	private int focusEncounter=0;
	
	/**
	 * A list of visitors and their id
	 */
    private final Map<Integer,Visitor> visitors;
    
    /**
     * The gym, contains all lockers
     */
	private final Gym gym;
	
	/**
	 * Counting all visitors
	 */
	private int visitorCounter=0;


    /**
     * Constructor
	 */
    public Simulator(int skipTimeValue, Gym gym){
		this.skipTimeValue=skipTimeValue;
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
	 * Clears all entries and sets the encounters
	 */
	public void clearSimulator() {
		for(Visitor v : visitors.values()){
			Logger.log(LoggingLevel.ENCOUNTER,v.getID()+" "+v.getEncounters());
			if(v.getID()==focusID){
				System.out.println(v.getID());
				Logger.log(LoggingLevel.FOCUS,v.getID()+" "+v.getEncounters());
				this.focusEncounter+=v.getEncounters();
			}
			this.encounters+=v.getEncounters();
		}
		this.gym.clearGym();
	}

    /**
     * Reduces the time by the given value for the Simluator and every active visitor until he has left time.
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
					Logger.log(LoggingLevel.FOCUS,v.getID()+" "+v.getEncounters());
				}
			}else{
				v.reduceTime(value);
			}
		}
        noTimeLeft.forEach(visitors::remove);
	}

	/**
	 * Adds a visitor to the list of visitors
	 * @param visitor	The Visitor to be added
	 */
    public   void addVisitor(Visitor visitor) {
		this.visitors.put(visitor.getID(),visitor);
		this.visitorCounter+=1;
	}

	/**
	 * Get the amount of visitors
	 * @return	amount of visitors
	 */
	public int getVisitorCounter() {
		return visitorCounter;
	}

	/**
     * Used to remove a visitor from the list of visitors
     * @param visitor	visitor to be removed
     * @return	the removed Visitor
     */
	private  Visitor removeVisitor(Visitor visitor){	//TODO Gucken ob Rueckgabetyp Visitor notwendig
		return this.visitors.remove(visitor.getID());
	}

	/**
	 * Saves the id of the focused visitor in the variable focusID
	 * @param id	the id of the focus visitor
	 */
	public void setFocusID(int id){
		this.focusID=id;
	}

	/**
	 * Returns the number of encounters
	 * @return	number of encounters
	 */
	public int getEncounters() {
		return encounters;
	}

	/**
	 * Returns the amount of encounters of the focused visitor
	 * @return	encounters of focus visitor
	 */
	public int getFocusEncounter() {
		return focusEncounter;
	}
}
