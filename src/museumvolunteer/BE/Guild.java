package museumvolunteer.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Guild{

    //instance variables
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty namesId;
    /**
     * Default constructor.
     *
     * @param id
     * @param name
     */
    
    /**
     * Default constructor.
     * @param id
     * @param name
     * @param namesId
     */
    public Guild(int id, String name, int namesId) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.namesId = new SimpleIntegerProperty(namesId);
    }
    
    public Guild(int id, String name) {
        this(id, name, -1);
    }
    
    public Guild(String name)
    {
       this(-1, name, -1);
    }

    public int getIdValue() {
        return id.get();
    }

    public StringProperty getName() {
        return name;
    }
    
    public String getNameAsString() {
        return name.get();
    }
    
    @Override
    public String toString()
    {
    return getNameAsString();
    }
    
    public int getNamesIdValue()
    {
        return namesId.get();
    }

    public IntegerProperty getId() {
        return id;
    }

    public IntegerProperty getNamesId() {
        return namesId;
    }
}
