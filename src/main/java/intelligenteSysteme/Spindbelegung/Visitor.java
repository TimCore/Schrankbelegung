package intelligenteSysteme.Spindbelegung;

/**
 * Created by Tc0r3 on 21.10.2016.
 */
public class Visitor {

    private short encounters;     	//Number of encounters during the session
    private int visitTime;          //The time this visitor will spend at the gym (current session)
    private int leftTime;			//The time until the visitor will leave the gym
    private int id;					//The id of the visitor
    private Locker locker;			//The locker which is used by the visitor 

    /**
     * Constructor
     * @param time	amount of time in seconds the visitor will stay at the gym
     * @param id	the id of the visitor
     */
    public Visitor(int time, int id){
        this.encounters = 0;
        this.visitTime = time;
        this.leftTime = time;
        this.id = id;
    }
    
    /**
     * Reduces the time by the given value, used to simulate time
     * @param timeToReduce	amount of time to reduce
     */
    //TODO wir müssen ändern wie gecheckt wird ob ein visitor grade eine begegnung hat und zwar wenn er am umziehen ist
    public void reduceTime(int timeToReduce){
    	this.leftTime -= timeToReduce;
    	if(leftTime == visitTime - 300){
    		changeClothes(false);
    	}else if(leftTime == 300){
    		changeClothes(true);
    	}else if(leftTime == 0){
    		changeClothes(false);
    	}
    	if(this.locker.checkEncounter()){
            this.encounters++;
        }
    }
    
    /**
     * Checks if the visitor will stay or leave
     * @return	true if time is left, false if time is over
     */
    public Boolean isTimeLeft(){
        return (leftTime > 0);
    }
    
    /**
     * Changes the status of the locker if the user starts changing or not
     * @param status
     */
    public void changeClothes(Boolean status){
    	this.locker.setCurrentlyInUse(status);
    }

    /**
     * returns the left Time of this visit in seconds
     */
    public int getLeftTime(){
        return this.leftTime;
    }
    
    /**
     * Returns the full time of the visit of the this visitor
     * @return
     */
    public int getVisitTime(){
        return this.visitTime;
    }

    /**
     * returns the number of Encounters of the visitor's actual visit
     */
    public int getEncounters(){
        return this.encounters;
    }
    
    /**
     * Returns the ID of this visitor
     */
    public int getID(){
    	return this.id;
    }
    
    /**
     * Gives this visitor a free locker to change
     */
    public void setLocker(Locker locker){
    	this.locker = locker;
    	changeClothes(true);
    }

    public Locker getLocker(){
        return this.locker;
    }
}
