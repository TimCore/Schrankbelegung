package intelligenteSysteme.Spindbelegung;

/**
 * Contains all needed data for the simulation
 */
public class Config {

	/**
	 * The number of rows
	 */
    private final int LOCKER_ROWS;
    
    /**
	 * The number of columns
	 */
    private final int LOCKER_COLUMNS;
    
    /**
	 * The number of total lockers
	 */
    private final int TOTAL_LOCKERS;
    
    /**
	 * Opening time of the Gym in hours
	 */
    private final int GYM_OPEN_TIME;
    
    /**
	 * Closing time of the Gym in hours
	 */
    private final int GYM_CLOSE_TIME;
    
    /**
	 * Number of simulations
	 */
    private final int SIMLUATION_REPEATS;
    
    /**
	 * The value for each time skip
	 */
    private final int TIME_SKIP_VALUE;
    
    /**
     * the path to the list of visitors and their times
     */
    private final String PATH_TO_VISIORLIST;

    /**
     * Constructor
     * @param LOCKER_ROWS			The number of rows
     * @param LOCKER_COLUMNS		The number of columns
     * @param TOTAL_LOCKERS			The number of total lockers
     * @param GYM_CLOSE_TIME		Closing time of the Gym in hours
     * @param GYM_OPEN_TIME			Opening time of the Gym in hours
     * @param SIMLUATION_REPEATS	Number of simulations
     * @param TIME_SKIP_VALUE		The value for each time skip
     * @param PATH_TO_VISIORLIST	the path to the list of visitors and their times
     */
    public Config(int LOCKER_ROWS,int LOCKER_COLUMNS,int
                  TOTAL_LOCKERS,int GYM_CLOSE_TIME,int GYM_OPEN_TIME,
                  int SIMLUATION_REPEATS,int TIME_SKIP_VALUE, String PATH_TO_VISIORLIST) {
        this.LOCKER_COLUMNS=LOCKER_COLUMNS;
        this.LOCKER_ROWS=LOCKER_ROWS;
        this.TOTAL_LOCKERS=TOTAL_LOCKERS;
        this.GYM_OPEN_TIME=GYM_OPEN_TIME;
        this.GYM_CLOSE_TIME=GYM_CLOSE_TIME;
        this.SIMLUATION_REPEATS=SIMLUATION_REPEATS;
        this.TIME_SKIP_VALUE = TIME_SKIP_VALUE;
        this.PATH_TO_VISIORLIST=PATH_TO_VISIORLIST;

    }

    /**
     * returns the value of rows
     */
    public int getLOCKER_ROWS() {
        return LOCKER_ROWS;
    }

    /**
     * returns the value of columns
     */
    public int getLOCKER_COLUMNS() {
        return LOCKER_COLUMNS;
    }

    /**
     * returns the value of the opening time
     */
    public int getGYM_OPEN_TIME() {
        return GYM_OPEN_TIME;
    }

    /**
     * returns the value of the closing time
     */
    public int getGYM_CLOSE_TIME() {
        return GYM_CLOSE_TIME;
    }

    /**
     * returns the value of repeats
     */
    public int getSIMLUATION_REPEATS() {
        return SIMLUATION_REPEATS;
    }
    
    /**
     * returns the value of the time skip value
     */
    public int getTIME_SKIP_VALUE(){
    	return this.TIME_SKIP_VALUE;
    }

    /**
     * returns the path to the visitorlist
     */
    public String getPATH_TO_VISIORLIST() {
        return PATH_TO_VISIORLIST;
    }
}
