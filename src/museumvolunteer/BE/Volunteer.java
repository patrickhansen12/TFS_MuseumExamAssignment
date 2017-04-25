/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BE;

/**
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class Volunteer {
    
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    public Volunteer(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Volunteer(String name) {
        this(-1, name, null, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
  
        /**
     * returns name, currentClass and attendance as strings
     * @return 
     */
    @Override
    public String toString() {
        return name;
    }

    
}
