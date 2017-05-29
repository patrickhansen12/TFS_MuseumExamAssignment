package museumvolunteer.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Volunteer extends Person{
    
    //Instance variables.
    private IntegerProperty guildsId;

    /**
     * Default constructor.
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     * @param guildsId
     */
    public Volunteer(int id, String name, String email, String phoneNumber, int guildsId) {
        super(id, name, email, phoneNumber);
        this.guildsId = new SimpleIntegerProperty(guildsId);
    }

    /**
     * Contructor for only returning name.
     * @param name
     */
    public Volunteer(String name) {
        super(-1, name, null, null);
    }

    /**
     * All variables except id.
     * @param name
     * @param email
     * @param phoneNumber
     * @param guildsId
     */
    public Volunteer(String name, String email, String phoneNumber, int guildsId)
    {
       super(-1, name, email, phoneNumber);
       this.guildsId = new SimpleIntegerProperty(guildsId);
    }

    /**
     * Everything except guildsId.
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     */
    public Volunteer(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    public int getGuildsIdValue() {
        return guildsId.get();
    }

    public IntegerProperty getGuildsId() {
        return guildsId;
    }
}
