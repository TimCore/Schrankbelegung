package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Locker {

    private boolean currentlyInUse = false;         // somebody currently uses the locker and changes (takes 5 minutes)
    private final LinkedList<Locker> neighbours;  		// List of other Lockers next to this one
    private final int columnNumber;
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


    
    private Boolean getCurrentlyInUse(){
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
