package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Locker {

    private boolean used = false;           // The locker is used and not available until the user leaves
    private boolean inUse = false;          // somebody currently uses the locker and changes (takes 5 minutes)
    private boolean changeBefore = false;	// visitor is changing before sports
    private boolean changeAfter = false;	// visitor is changing after sports
    private boolean encounterEntry;			//encounter when changing after arrive
    private boolean encounterLeave;			//encounter when changing before leaving
    private LinkedList<Locker> neighbours;  // List of other Lockers next to this one
    private int timeUsed = 0;
    private int timeTotal = 0;
    private boolean finished = false;
    private int visitorId;


    /**
     * Constructor
     */
    public Locker(){
        neighbours = new LinkedList<Locker>();
    }

    /**
     * is used every 10 seconds. Checks for encounters and adds them to encounters.
     * Avoids duplicates by searching for encounters only every 5 minutes (300 seconds).
     */
    public boolean run(){
        this.timeUsed += 10;
        if(timeUsed == 300){
        	lock();
        }else if(timeUsed == timeTotal-300){
        	changeLeave();
        }else if(timeUsed == timeTotal){
        	leave();
        	checkEncounter();			//klaeren ob notwendig		
        	return false;
        }
        checkEncounter();
        return true;
    }

    /**
     * Adds another Locker as a neighbour
     * @param n new added locker
     */
    public void addNeighbour(Locker n){
        neighbours.add(n);
    }

    /**
     *  Checks if an encounter occured. The locker must be currently in use otherwise an encounter is not possible and 0 is returned.
     *  Checks for only one encounter each time the visitor changes his clothes.
     */
    public void checkEncounter(){
    	if(inUse){
    		if(changeBefore && !encounterEntry){
                for(Locker l : neighbours){
                    if(l.isInUse()){
                        encounterEntry = true;
                    }
                }
    		}else if(changeAfter && !encounterLeave){
    			for(Locker l : neighbours){
    				if(l.isInUse()){
    					encounterLeave = true;
                    }
                }
            }
    	}  
    }

    //region SET STATUS

    /**
     * Checks if the user of the locker currently changes
     * @return true or false
     */
    public boolean isInUse(){
        return inUse;
    }

    /**
     * Checks if the locker is currently in use or available for a new customer.
     * @return true or false
     */
    public boolean isUsed(){
        return used;
    }

    //endregion

    //region INTERACTION WITH VISITOR

    /**
     * the visitor has arrived and changes his clothes. Resets all values (but visitorCounter) and sets the time this locker will be in use and the visitor ID
     */
    public void changeArrive(int time, int visId){
        this.used = true;
        this.inUse = true;
        this.timeTotal = time;
        this.timeUsed = 0;
        this.visitorId = visId;
        this.changeBefore = true;
    }

    /**
     * the visitor has changed his clothes and locked them in the locker
     */
    public void lock(){
        this.inUse = false;
        this.changeBefore = false;
    }

    /**
     * the visitor finished exercises and changes to leaves
     */
    public void changeLeave(){
        this.inUse = true;
        this.changeAfter = true;
    }

    /**
     * the visitor changed and takes his clothes out of the locker to leave
     */
    public void leave(){
        this.used = false;
        this.inUse = false;
        this.changeAfter = false;
        
    }

    //endregion

    /**
     * returns the id of the actual visitor
     * @return
     */
    public int getVisitorId(){
        return this.visitorId;
    }

    /**
     * used when the visitor leaves, counts the encounters. 
     * @return number of encounters, max 2
     */
    public int getEncounters(){
    	int enc = 0;
    	if(encounterEntry)enc++;
        if(encounterLeave)enc++;
        return enc;
    }
}
