package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * The Gym contains the lockers and is used to manage them. 
 */
public class Gym {

	/**
	 * Number of lockers in a row
	 */
    private final int columns;
    
    /**
     * Number of rows with lockers
     */
    private final int rows;     
    
    /**
     * Total number of lockers
     */
    private final int total;          	
    
    /**
     * All lockers
     */
    private final Locker[][] lockers;  
    
    /**
     * A Map containing references to all used Lockers
     */
    private final LinkedList<Locker> usedLockers;
    
    /**
     * A Map containing references to all free Lockers
     */
    private final LinkedList<Locker> freeLockers;


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
        this.freeLockers= new LinkedList<>();
        this.usedLockers= new LinkedList<>();
        createLockers();
        setNeighbours();
    }

    /**
     * Removes the locker with the given coordinates from the list of free lockers, adds it to the list of lockers in use and returns it
     * @param row   value of the row
     * @param col   value of the column
     */
    public Locker choseLocker(int row, int col){
        Locker locker = lockers[row][col];
        locker.setCurrentlyInUse(true);
        locker.setOccupied(true);
    	freeLockers.remove(locker);
    	usedLockers.add(locker);
        return locker;
    }

    /**
     * Initializes the array of lockers and adds each locker to the list of free lockers
     */
    private void createLockers(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Locker locker = new Locker(j,i);
                this.lockers[i][j]= locker;
                this.freeLockers.add(locker);
            }
        }
    }
    
    /**
     * Removes the locker with the given coordinates from the list of used lockers and adds it to the list of free lockers
     @param locker Which Locker we want to put back into the free list
     */
    public void freeLocker(Locker locker){
    	freeLockers.add(locker);
    	usedLockers.remove(locker);
    	locker.setCurrentlyInUse(false);
    	locker.setOccupied(false);
    }

    /**
     * takes all used lockers, resets them and adds them to the list of free lockers
     */
    public void clearGym(){
        freeLockers.addAll(usedLockers);
        for (Locker l: freeLockers) {
            l.setCurrentlyInUse(false);
        }
        usedLockers.clear();
    }

    /**
     * Sets the neighbours for each Locker TODO only for two rows yet
     */
    private void setNeighbours(){
        for(int i = 0; i<rows; i++){
            for(int j = 0; j<columns; j++) {
                //Die beiden ecken oben wie unten
                if(j==0||j==columns-1){
                    if(i==0){
                        if(j==0){
                            //Obere linke ecke
                            lockers[i][j].addNeighbour(lockers[i+1][j]);
                            lockers[i][j].addNeighbour(lockers[i][j+1]);
                            lockers[i][j].addNeighbour(lockers[i+1][j+1]);
                        }else{
                            //untere linke ecke
                            lockers[i][j].addNeighbour(lockers[i+1][j-1]);
                            lockers[i][j].addNeighbour(lockers[i][j-1]);
                            lockers[i][j].addNeighbour(lockers[i+1][j]);
                        }
                    }else{
                        if(j==0){
                            //Obere rechte ecke
                            lockers[i][j].addNeighbour(lockers[i-1][j]);
                            lockers[i][j].addNeighbour(lockers[i][j+1]);
                            lockers[i][j].addNeighbour(lockers[i-1][j+1]);
                        }else{
                            //untere rechte ecke
                            lockers[i][j].addNeighbour(lockers[i-1][j]);
                            lockers[i][j].addNeighbour(lockers[i][j+-1]);
                            lockers[i][j].addNeighbour(lockers[i-1][j-11]);
                        }
                    }
                }else if(i==1){
                    lockers[i][j].addNeighbour(lockers[i][j-1]);
                    lockers[i][j].addNeighbour(lockers[i-1][j-1]);
                    lockers[i][j].addNeighbour(lockers[i-1][j]);
                    lockers[i][j].addNeighbour(lockers[i-1][j+1]);
                    lockers[i][j].addNeighbour(lockers[i][j+1]);
                }else{
                    lockers[i][j].addNeighbour(lockers[i][j-1]);
                    lockers[i][j].addNeighbour(lockers[i+1][j-1]);
                    lockers[i][j].addNeighbour(lockers[i+1][j]);
                    lockers[i][j].addNeighbour(lockers[i+1][j+1]);
                    lockers[i][j].addNeighbour(lockers[i][j+1]);
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
     * Adds the given locker to the list of used Lockers
     * @param locker	the locker to be added to the list of used lockers
     */
    public void addUsedLocker(Locker locker){
        this.usedLockers.add(locker);
    }
    
    /**
     * Returns a list of all used lockers
     */
    public LinkedList<Locker> getUsedLockers(){
    	return this.usedLockers;
    }
    
    
    

}
