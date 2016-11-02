package intelligenteSysteme.Spindbelegung;

/**
 * Created by eiamnacken on 02.11.16.
 */
public class Config {

    private   int LOCKER_ROWS;
    private  int LOCKER_COLUMNS;
    private   int TOTAL_LOCKERS;
    private  int GYM_OPEN_TIME;
    private   int GYM_CLOSE_TIME;
    private  int SIMLUATION_REPEATS;
    private  String PATH_TO_VISIORLIST;

    public Config(int LOCKER_ROWS,int LOCKER_COLUMNS,int
                  TOTAL_LOCKERS,int GYM_CLOSE_TIME,int GYM_OPEN_TIME,
                  int SIMLUATION_REPEATS,String PATH_TO_VISIORLIST) {
        this.LOCKER_COLUMNS=LOCKER_COLUMNS;
        this.LOCKER_ROWS=LOCKER_ROWS;
        this.TOTAL_LOCKERS=TOTAL_LOCKERS;
        this.GYM_OPEN_TIME=GYM_OPEN_TIME;
        this.GYM_CLOSE_TIME=GYM_CLOSE_TIME;
        this.SIMLUATION_REPEATS=SIMLUATION_REPEATS;
        this.PATH_TO_VISIORLIST=PATH_TO_VISIORLIST;

    }

    public int getLOCKER_ROWS() {
        return LOCKER_ROWS;
    }

    public int getLOCKER_COLUMNS() {
        return LOCKER_COLUMNS;
    }

    public int getTOTAL_LOCKERS() {
        return TOTAL_LOCKERS;
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

    public String getPATH_TO_VISIORLIST() {
        return PATH_TO_VISIORLIST;
    }
}
