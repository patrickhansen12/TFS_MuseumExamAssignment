package museumvolunteer.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public abstract class Person {
    private int id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty phoneNumber;

    public Person(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public int getId() {
        return id;
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
    
}
