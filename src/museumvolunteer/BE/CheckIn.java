package museumvolunteer.BE;

import java.sql.Timestamp;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class CheckIn {

    //Instance variables.
    private int id;
    private Timestamp dateTime;
    private int nameId;
    private int hours;

    /**
     * Default constructor.
     *
     * @param id
     * @param dateTime
     * @param nameId
     * @param hours
     */
    public CheckIn(int id, Timestamp dateTime, int nameId, int hours) {
        this.id = id;
        this.dateTime = dateTime;
        this.nameId = nameId;
        this.hours = hours;
    }

    /**
     * Everyting except id.
     *
     * @param dateTime
     * @param nameId
     * @param hours
     */
    public CheckIn(Timestamp dateTime, int nameId, int hours) {
        this(-1, dateTime, nameId, hours);
    }

//    public void setHours(int hours) {
//        this.hours = hours;
//    }
    /**
     * Special constructor.
     * @param id
     * @param ci
     */
    public CheckIn(int id, CheckIn ci) {
        this(id, ci.getDateTime(), ci.getNameId(), ci.getHours());
    }

//    public CheckIn(Timestamp dateTime, int hours)
//    {
//        this(-1, dateTime, -1, hours);
//    }
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
    public Timestamp getDateTime() {
        return dateTime;
    }

    /**
     *
     * @param dateTime
     */
    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    /**
     *
     * @return
     */
    public int getNameId() {
        return nameId;
    }

    /**
     *
     * @param nameId
     */
    public void setNameId(int nameId) {
        this.id = nameId;
    }

    /**
     *
     * @return
     */
    public int getHours() {
        return hours;
    }

}
