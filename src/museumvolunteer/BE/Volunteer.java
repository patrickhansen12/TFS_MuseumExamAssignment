package museumvolunteer.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Volunteer {
    
    //Instance variables.
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty phoneNumber;
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
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.guildsId = new SimpleIntegerProperty(guildsId);
    }

    /**
     * Contructor for only returning name.
     * @param name
     */
    public Volunteer(String name) {
        this(-1, name, null, null, -1);
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
       this(-1, name, email, phoneNumber, guildsId); 
    }

    /**
     * Everything except guildsId.
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     */
    public Volunteer(int id, String name, String email, String phoneNumber) {
        this(id, name, email, phoneNumber, -1);
    }

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
    public String getNameAsString() {
        return name.get();
    }

    /**
     *
     * @return
     */
    public String getEmailAsString() {
        return email.get();
    }

    /**
     *
     * @return
     */
    public String getPhoneNumberAsString() {
        return phoneNumber.get();
    }

    /**
     *
     * @return
     */
    public int getGuildsIdValue() {
        return guildsId.get();
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getEmail() {
        return email;
    }

    public StringProperty getPhoneNumber() {
        return phoneNumber;
    }

    public IntegerProperty getId() {
        return id;
    }

    public IntegerProperty getGuildsId() {
        return guildsId;
    }
}
