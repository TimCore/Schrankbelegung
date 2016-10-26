package intelligenteSysteme.Spindbelegung;

import java.util.LinkedList;

/**
 * Created by Tc0r3 on 19.10.2016.
 */
public class Gym {

    private int columns;         //Number of lockers in a row
    private int rows;            //Number of rows with lockers
    private int total;           //Total number of lockers
    private Locker[][] lockers;  //All lockers
    private LinkedList<int[]> lockersInUse; //TODO herausfinden wie am besten anhand id loeschen wenn fertig


    /**
     * Constructor, initialises lockers[rows][columns]
     * @param col   number of columns
     * @param row   number of rows
     */
    public Gym(int row, int col){
        this.columns = col;
        this.rows = row;
        this.total = col*row;
        lockers = new Locker[rows][columns];
    }

    //TODO ueberlegen wie run() am besten einzusetzen

    /**
     * returns a list with visitorID and the number of encounters
     * @return
     */
    /*public LinkedList<int[]> run(){

    }*/

    /**
     * Receives the coordinates of a chosen locker from the class Calculate and
     * starts its use
     * @param row   value of the row
     * @param col   value of the column
     * @param time  the time the user will need
     * @param visID the visitor id
     */
    public void choseLocker(int row, int col, int time, int visID){
        lockers[row][col].changeArrive(time, visID);
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
