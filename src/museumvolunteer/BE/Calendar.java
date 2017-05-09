package museumvolunteer.BE;

import java.sql.Timestamp;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Calendar {

    //Instance variables.
    private int id;
    private Timestamp day;

    /**
     * Default constructor.
     *
     * @param id
     * @param day
     */
    public Calendar(int id, Timestamp day) {
        this.id = id;
        this.day = day;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Timestamp getDay() {
        return day;
    }

    /**
     *
     * @param day
     */
    public void setSchoolDate(Timestamp day) {
        this.day = day;
    }
}
