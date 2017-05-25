package museumvolunteer.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Volunteer extends Person{
    
    //Instance variables.
//    private IntegerProperty id;
//    private StringProperty name;
//    private StringProperty email;
//    private StringProperty phoneNumber;
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
//        this.id = new SimpleIntegerProperty(id);
//        this.name = new SimpleStringProperty(name);
//        this.email = new SimpleStringProperty(email);
//        this.phoneNumber = new SimpleStringProperty(phoneNumber);
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

    /**
     *
     * @return
     */
    public int getGuildsIdValue() {
        return guildsId.get();
    }

    public IntegerProperty getGuildsId() {
        return guildsId;
    }
}
