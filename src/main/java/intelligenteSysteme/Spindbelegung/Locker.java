package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Locker {

    private boolean used = false;           // The locker is used and not available until the user leaves
    private boolean inUse = false;          // somebody currently uses the locker and changes (takes 5 minutes)
    private LinkedList<Locker> neighbours;  // List of other Lockers next to this one
    private int timeUsed = 0;
    private int timeTotal = 0;
    private int visitorCounter = 0;
    private LinkedList<Integer> encounters;
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
    public void run(){
        this.timeUsed += 10;
        if(timeUsed % 300 == 0){
            LinkedList<Integer> encos = checkEncounter();
            while(!encos.isEmpty()){
                int a = encos.removeFirst();
                if(!encounters.contains(a)){
                    encounters.add(a);
                }
            }
        }
        if(timeUsed==300){
            this.finished = true;
        }
    }

    /**
     * Adds another Locker as a neighbour
     * @param n new added locker
     */
    public void addNeighbour(Locker n){
        neighbours.add(n);
    }

    /**
     *  Checks if and how many encounters occur. The locker must be currently in use otherwise an encounter is not possible and 0 is returned
     * @return a list with the ids of other visitors if the locker and a neighbour are both "inUse"
     */
    public LinkedList<Integer> checkEncounter(){
        LinkedList<Integer> dummy = new LinkedList<Integer>();
        if(!inUse){
            return dummy;
        }else{
            for(Locker l : neighbours){
                if(l.isInUse()){
                    int enc = l.getVisitorId();
                    dummy.add(enc);
                }
            }
            return dummy;
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
        this.visitorCounter++;
        this.encounters = new LinkedList<Integer>();
    }

    /**
     * the visitor has changed his clothes and locked them in the locker
     */
    public void lock(){
        this.inUse = false;
    }

    /**
     * the visitor finished exercises and changes to leaves
     */
    public void changeLeave(){
        this.inUse = true;
    }

    /**
     * the visitor changed and takes his clothes out of the locker to leave
     */
    public void leave(){
        this.used = false;
        this.inUse = false;
    }

    //endregion

    public int getVisitorId(){
        return this.visitorId;
    }

    public LinkedList<Integer> getEncounters(){
        return this.encounters;
    }
}
