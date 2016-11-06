package intelligenteSysteme.Spindbelegung;

/**
 * Created by eiamnacken on 02.11.16.
 */
public class Config {

    private final int LOCKER_ROWS;
    private final int LOCKER_COLUMNS;
    private final int TOTAL_LOCKERS;
    private final int GYM_OPEN_TIME;
    private final int GYM_CLOSE_TIME;
    private final int SIMLUATION_REPEATS;
    private final int TIME_SKIP_VALUE;
    private final String PATH_TO_VISIORLIST;

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

    public int getLOCKER_ROWS() {
        return LOCKER_ROWS;
    }

    public int getLOCKER_COLUMNS() {
        return LOCKER_COLUMNS;
    }

    public int getGYM_OPEN_TIME() {
        return GYM_OPEN_TIME;
    }

    public int getGYM_CLOSE_TIME() {
        return GYM_CLOSE_TIME;
    }

    public int getSIMLUATION_REPEATS() {
        return SIMLUATION_REPEATS;
    }
    
    public int getTIME_SKIP_VALUE(){
    	return this.TIME_SKIP_VALUE;
    }

    public String getPATH_TO_VISIORLIST() {
        return PATH_TO_VISIORLIST;
    }
}
