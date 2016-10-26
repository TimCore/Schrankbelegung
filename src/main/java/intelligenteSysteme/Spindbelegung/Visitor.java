package intelligenteSysteme.Spindbelegung;

/**
 * Created by Tc0r3 on 21.10.2016.
 */
public class Visitor {

    private int encounters;     //Number of encounters during the session
    //private int timeSpend = 0;      //The time this visitor has already spend at the gym (this session)
    private int time;           //The time this visitor will spend at the gym (current session)

    public Visitor(int time){
        this.encounters = 0;
        this.time = time;
    }

    public int getTime(){
        return this.time;
    }

    public int getEncounters(){
        return this.encounters;
    }





}
