/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BE;

import java.sql.Timestamp;

/**
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class CheckIn{
    private int id;
    private Timestamp dateTime;
    private int nameId;
    private int hours;

    public CheckIn(Timestamp dateTime, int nameId, int hours) {
        this(-1, dateTime, nameId, hours);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public CheckIn(int id, CheckIn ts) {
        this(id, ts.getDateTime(), ts.getNameId(), ts.getHours());
    }

    public CheckIn(int id, Timestamp dateTime, int nameId, int hours) {
        this.id = id;
        this.dateTime = dateTime;
        this.nameId = nameId;
        this.hours = hours;
    }
    
    public CheckIn(Timestamp dateTime, int hours)
    {
        this(-1, dateTime, -1, hours);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.id = nameId;
    }
    
}
