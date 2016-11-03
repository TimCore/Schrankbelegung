package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Gym {

    private int columns;         				//Number of lockers in a row
    private int rows;           				//Number of rows with lockers
    private int total;          		 		//Total number of lockers
    private Locker[][] lockers;  				//All lockers
    private LinkedList<Locker> usedLockers; 	//A Map containing references to all used Lockers
    private LinkedList<Locker> freeLockers;	//A Map containing references to all free Lockers


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
        for(int i = 0; i < rows; i++){
        	for(int j = 0; j < columns; j++){
        		
        	}
        }
    }

    /**
     * Removes the locker with the given coordinates from the list of free lockers, adds it to the list of lockers in use and returns it
     * @param row   value of the row
     * @param col   value of the column
     */
    public Locker choseLocker(int row, int col){
    	freeLockers.remove(lockers[row][col]);
    	usedLockers.add(lockers[row][col]);
    	lockers[row][col].incVisits();
        return lockers[row][col];
    }
    
    /**
     * Removes the locker with the given coordinates from the list of used lockers and adds it to the list of free lockers
     * @param row   value of the row
     * @param col   value of the column
     */
    public void freeLocker(int row, int col){
    	freeLockers.remove(lockers[row][col]);
    	usedLockers.add(lockers[row][col]);
    	lockers[row][col].setCurrentlyInUse(false);
    }

    /**
     * Sets the neighbours for each Locker TODO only for two rows yet
     */
    public void setNeighbours(){
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<columns; j++) {
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
                }else if(i==0 && j>0 && j<rows-1){//upper row
                    //TODO dieses und der nächste block sind gleich soll das so sein?
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
     * Resets all lockers for the next simulation
     */
    public void reset(){
    	for(int i = 0; i<rows; i++){
            for(int j = 0; j<columns; j++) {
            	lockers[i][j].reset();
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
     * Returns the amount of rows
     */
    public int getRows(){
        return this.rows;
    }

    /**
     * Returns the amount of columns
     */
    public int getColumns(){
        return this.columns;
    }

    /**
     * Returns the lockers as a array [row][column]
     */
    public Locker[][] getLocker(){ return this.lockers; }
    
    /**
     * Returns a list of all free lockers
     */
    public LinkedList<Locker> getFreeLockers(){
    	return this.freeLockers;
    }
    
    /**
     * Returns a list of all used lockers
     */
    public LinkedList<Locker> getUsedLockers(){
    	return this.usedLockers;
    }
    
    
    

}
