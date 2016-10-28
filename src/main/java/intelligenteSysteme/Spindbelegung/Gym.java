package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Gym {

    private int columns;         			//Number of lockers in a row
    private int rows;           			//Number of rows with lockers
    private int total;          		 	//Total number of lockers
    private Locker[][] lockers;  			//All lockers
    private LinkedList<int[]> lockersInUse; //contains [0]id, [1]row, [2]column 


    /**
     * Constructor, initializes lockers[rows][columns]
     * @param col   number of columns
     * @param row   number of rows
     */
    public Gym(int row, int col){
        this.columns = col;
        this.rows = row;
        this.total = col*row;
        lockers = new Locker[rows][columns];
        setNeighbours();
    }

    /**
     * increments the time of each locker that is actual in use and gets the number of encounters when the time is up
     * @return a list with visitorID [0] and the number of encounters [1]
     */
    public LinkedList<int[]> run(){
    	LinkedList<int[]> output = new LinkedList<int[]>();
    	for(int[] i : lockersInUse){
    		if(!lockers[i[1]][i[2]].run()){		//if false, return encounters
    			int[] lockerData = new int[2];
    			lockerData[0] = i[0];
    			lockerData[1] = lockers[i[1]][i[2]].getEncounters();
    			output.add(lockerData);
    			// TODO noch aus Liste loeschen!
    		}
    	}
    	return output;
    }

    /**
     * Receives the coordinates of a chosen locker from the class Calculate and
     * starts its use
     * @param row   value of the row
     * @param col   value of the column
     * @param time  the time the user will stay
     * @param visID the visitor id
     */
    public void choseLocker(int row, int col, int time, int visID){
        lockers[row][col].changeArrive(time, visID);
        int[] i = {visID, row, col};
        this.lockersInUse.add(i);
    }

    /**
     * Sets the neighbours for each Locker TODO only for two rows yet
     */
    public void setNeighbours(){
        for(int i = 0; i<columns; i++){
            for(int j = 0; j<rows; j++) {
                if(i==0 && j==0){                                        //upper left corner
                    lockers[i][j+1].addNeighbour(lockers[i][j+1]);
                    lockers[i+1][j].addNeighbour(lockers[i][j+1]);
                    lockers[i+1][j+1].addNeighbour(lockers[i][j+1]);
                }else if(i==columns-1 && j==0){                          //lower left corner
                    lockers[i-1][j].addNeighbour(lockers[i][j+1]);
                    lockers[i-1][j+1].addNeighbour(lockers[i][j+1]);
                    lockers[i][j+1].addNeighbour(lockers[i][j+1]);
                }else if(i==0 && j==rows-1){                             //upper right corner
                    lockers[i][j-1].addNeighbour(lockers[i][j+1]);
                    lockers[i+1][j].addNeighbour(lockers[i][j+1]);
                    lockers[i+1][j-1].addNeighbour(lockers[i][j+1]);
                }else if(i==columns-1 && j==rows-1){                     //lower right corner
                    lockers[i-1][j].addNeighbour(lockers[i][j+1]);
                    lockers[i-1][j-1].addNeighbour(lockers[i][j+1]);
                    lockers[i][j-1].addNeighbour(lockers[i][j+1]);
                }else if(i==0 && j>0 && j<rows-1){                       //upper row
                    lockers[i][j].addNeighbour(lockers[i][j-1]);
                    lockers[i][j].addNeighbour(lockers[i][j+1]);
                    lockers[i][j].addNeighbour(lockers[i+1][j-1]);
                    lockers[i][j].addNeighbour(lockers[i+1][j]);
                    lockers[i][j].addNeighbour(lockers[i+1][j+1]);
                }else if(i==columns-1 && j>0 && j<rows-1){               //lower row
                    lockers[i][j].addNeighbour(lockers[i][j-1]);
                    lockers[i][j].addNeighbour(lockers[i][j+1]);
                    lockers[i][j].addNeighbour(lockers[i+1][j-1]);
                    lockers[i][j].addNeighbour(lockers[i+1][j]);
                    lockers[i][j].addNeighbour(lockers[i+1][j+1]);
                }
            }
        }
    }

    /**
     * Returns the total number of lockers
     */
    public int getTotal(){
        return this.total;
    }

    /**
     * returns the amount of rows
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * returns the amount of columns
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * Returns the lockers as a array [row][column]
     */
    public Locker[][] getLocker(){ return this.lockers; }

}
