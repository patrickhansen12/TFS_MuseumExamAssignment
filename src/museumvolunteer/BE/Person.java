package museumvolunteer.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public abstract class Person {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty phoneNumber;

    public Person(int id, String name, String email, String phoneNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public int getIdValue() {
        return id.get();
    }

    public StringProperty getName() {
        return name;
    }
    
    public String getNameAsString()
    {
        return name.get();
        
    }
    
    @Override
    public String toString(){
        return getNameAsString();    
    }
 
    public StringProperty getEmail() {
        return email;
    }
    
    public String getEmailAsString()
    {
        return email.get();
    }
    public StringProperty getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getPhoneNumberAsString()
    {
        return phoneNumber.get();
    }

    public IntegerProperty getId() {
        return id;
    }
}
