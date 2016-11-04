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
    	freeLockers.remove(locker);
    	usedLockers.add(locker);
        return locker;
    }

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
    }

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
    public void setNeighbours(){
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
