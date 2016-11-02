package intelligenteSysteme.Spindbelegung;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Calculator {
	
	private Gym gym;
	
	public Calculator(){
		//this.gym = new Gym()
	}
	
	
	/*
    /**
     * returns a free random locker. Tries again if occupied 
     * @return unused locker
     */
    public void chooseRandom(int time, int visID){
        int r = (int) Math.random()*row;
        int c = (int) Math.random()*col;
        if(!gym.getLocker()[r][c].isUsed()){
            chooseRandom(time, visID);             //if used try again
        }
        gym.choseLocker(r,c,time,visID);
    }
/*
    
    
    /**
     * Uses a random value to simulate a probability of 10% that a new visitor arrives. 
     * @return	true if value below 0.1
     */
    public boolean rndCheck(){
    	double rnd = Math.random();
    	if(rnd < 0.1){
    		return true;
    	}else{
    		return false;
    	}	
    }

    /**
     * reads the .txt-file with the information about the durations of visits and saves it to a 2-dimensional int array
     * @param txt path to the "Belegungszeiten.txt"
     * @return 2-dimensional int-array with int[duration][frequency]
     */
    public LinkedList<int[]> readIn(String txt){
        LinkedList<int[]> out = new LinkedList<int[]>();
        try{
            File file = new File(txt);
            Scanner in = new Scanner(file);
            String ln = in.nextLine();      //ignore first line
            ln = in.nextLine();
            String[] str;
            int[] df = new int[2];
            while(ln != ""){
                str = ln.split(" ");
                df[0] = Integer.parseInt(str[0]);
                df[1] = Integer.parseInt(str[1]);
                out.add(df);
                ln = in.nextLine();
            }
        }catch (Exception e){
            System.out.println("File not found");
        }
        return out;
    }

}
