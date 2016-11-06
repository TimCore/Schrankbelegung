package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Locker {

	/**
	 * somebody currently uses the locker and changes (takes 5 minutes)
	 */
    private boolean currentlyInUse = false;
    
    /**
     * shows if the locker is actually occupied
     */
    private boolean occupied = false;
    
    /**
     * List of other Lockers next to this one
     */
    private final LinkedList<Locker> neighbours;
    
    /**
     * The position (column) of the locker in the gym
     */
    private final int columnNumber;
    
    /**
     * The position (row) of the locker in gym
     */
    private final int rowNumber;

    /**
     * Constructor
     */
    public Locker(int columnNumber, int rowNumber){
        this.neighbours = new LinkedList<>();
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
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
     */
    public boolean checkEncounter(){
    	for(Locker l : neighbours){
            if(l.getCurrentlyInUse()){
                return true;
            }
        }
    	return false;
    }

    /**
     * Checks if this locker is currently in use
     * @return	true if in use, else false
     */
    public Boolean getCurrentlyInUse(){
    	return currentlyInUse; 
    }
    
    /**
     * Sets the status of the locker to "in use" or not 
     * @param status	in use or not
     */
    public void setCurrentlyInUse(Boolean status){
    	this.currentlyInUse = status;
    }
    
    /**
     * Sets the status if this locker is actually occupied and not free for new visitors
     * @param status	occupied or not
     */
    public void setOccupied(Boolean status){
    	this.occupied = status;
    }
    
    /**
     * Tells if the locker is occupied or not
     * @return true if occupied, else false
     */
    public Boolean getOccupied(){
    	return this.occupied;
    }

    /**
     * Shows the position (column) of this locker in the gym
     * @return	value of position in columns
     */
    public int getColumnNumber(){
        return this.columnNumber;
    }

    /**
     * Shows the position (row) of this locker in the gym
     * @return	value of position in rows
     */
    public int getRowNumber(){
        return this.rowNumber;
    }
}
