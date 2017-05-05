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
public class Calendar {
    private int id;
    private Timestamp day;
    
    public Calendar(int id, Timestamp day) {
        this.id = id;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDay() {
        return day;
    }

    public void setSchoolDate(Timestamp day) {
        this.day = day;
    }
}