package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Locker {

    private boolean currentlyInUse = false;         // somebody currently uses the locker and changes (takes 5 minutes)
    private LinkedList<Locker> neighbours;  		// List of other Lockers next to this one
    private int columnNumber;
    private int rowNumber;
    private int visits;


    /**
     * Constructor
     */
    public Locker(int columnNumber, int rowNumber){
        this.neighbours = new LinkedList<Locker>();
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
    }

    /**
     * Resets the value of the visits-counter
     */
    public void reset(){
    	this.visits = 0;
    }

    /**
     * Adds another Locker as a neighbour
     * @param n new added locker
     */
    public void addNeighbour(Locker n){
        neighbours.add(n);
    }

    /**
     *  Checks if a neighbour-locker is currently used. Returns true if an encounter occures, else false if no neighbour is currently in use
     *  .
     */
    public Boolean checkEncounter(){
    	for(Locker l : neighbours){
            if(l.getCurrentlyInUse()){
                return true;
            }
        }
    	return false;
    }

    public void incVisits(){
    	this.visits++;
    }
    
    public Boolean getCurrentlyInUse(){
    	return currentlyInUse; 
    }
    
    public void setCurrentlyInUse(Boolean status){
    	this.currentlyInUse = status;
    }

    public int getColumnNumber(){
        return this.columnNumber;
    }

    public int getRowNumber(){
        return this.rowNumber;
    }
}
