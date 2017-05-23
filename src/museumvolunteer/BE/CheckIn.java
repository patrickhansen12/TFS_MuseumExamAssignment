package museumvolunteer.BE;

import java.sql.Timestamp;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class CheckIn {

    //Instance variables.
    private IntegerProperty id;
    private Timestamp dateTime;
    private IntegerProperty guildsId;
    private IntegerProperty nameId;
    private IntegerProperty hours;

    /**
     * Default constructor.
     *
     * @param id
     * @param dateTime
     * @param guildsId
     * @param nameId
     * @param hours
     */
    public CheckIn(int id, Timestamp dateTime, int guildsId, int nameId, int hours) {
        this.id = new SimpleIntegerProperty(id);
        this.dateTime = dateTime;
        this.guildsId = new SimpleIntegerProperty(guildsId);
        this.nameId = new SimpleIntegerProperty(nameId);
        this.hours = new SimpleIntegerProperty(hours);
    }

    /**
     * Everyting except id.
     *
     * @param dateTime
     * @param guildsId
     * @param nameId
     * @param hours
     */
    public CheckIn(Timestamp dateTime, int guildsId, int nameId, int hours) {
        this(-1, dateTime, guildsId, nameId, hours);
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
        this(id, ci.getDateTime(), ci.getGuildsIdValue(), ci.getNameIdValue(), ci.getHoursValue());
    }

    public CheckIn(CheckIn ci) {
        this(-1, ci.getDateTime(), ci.getGuildsIdValue(), ci.getNameIdValue(), ci.getHoursValue());
    }

//    public CheckIn(Timestamp dateTime, int hours)
//    {
//        this(-1, dateTime, -1, hours);
//    }
    /**
     *
     * @return
     */
    public int getIdValue() {
        return id.get();
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
     * @return
     */
    public int getNameIdValue() {
        return nameId.get();
    }

    /**
     *
     * @return
     */
    public int getHoursValue() {
        return hours.get();
    }

    public int getGuildsIdValue() {
        return guildsId.get();
    }

    public IntegerProperty getId() {
        return id;
    }

    public IntegerProperty getGuildsId() {
        return guildsId;
    }

    public IntegerProperty getNameId() {
        return nameId;
    }

    public IntegerProperty getHours() {
        return hours;
    }
}
