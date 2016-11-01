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

    public Visitor(int time, int id){
        this.encounters = 0;
        this.visitTime = time;
        this.leftTime = time;
        this.id = id;
    }
    
    public void reduceTime(int timeToReduce){
    	this.leftTime -= timeToReduce;
    	if(leftTime == visitTime - 300){
    		this.locker.setCurrentlyInUse(false);
    	}else if(leftTime == 300){
    		this.locker.setCurrentlyInUse(true);
    	}else if(leftTime == 0){
    		this.locker.setCurrentlyInUse(false);
    	}
    }
    
    public Boolean isTimeLeft(){
    	return (leftTime > 0);
    }
    
    public void changeClothes(){
    	this.locker.setCurrentlyInUse(true);
    }

    public int getLeftTime(){
        return this.leftTime;
    }
    
    public int getVisitTime(){
        return this.visitTime;
    }

    public int getEncounters(){
        return this.encounters;
    }
    
    public int getID(){
    	return this.id;
    }
    
    public void setLocker(Locker locker){
    	this.locker = locker;
    }





}
