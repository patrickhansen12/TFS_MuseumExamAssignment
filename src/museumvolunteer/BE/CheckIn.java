/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BE;

import java.sql.Timestamp;
import static javafx.util.Duration.hours;

/**
 * @author Jens, Patrick, Casper, Kasper
 */
public class CheckIn{
    private int id;
    private Timestamp dateTime;
    private int volunteerId;
    private int Hours;

    public int getHours() {
        return Hours;
    }

    public void setHours(int getHours) {
        this.Hours = Hours;
    }

    public CheckIn(int id, CheckIn ts) {
        this(id, ts.getDateTime(), ts.getStudentId(), ts.getHours());
    }
    
    public CheckIn(Timestamp dateTime, int volunteerId, int hours){
        this(-1, dateTime, volunteerId, hours);
    }

    public CheckIn(int id, Timestamp dateTime, int studentId, int hours) {
        this.id = id;
        this.dateTime = dateTime;
        this.volunteerId = studentId;
        this.Hours = hours;
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

    public int getStudentId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.id = volunteerId;
    }
    
}
