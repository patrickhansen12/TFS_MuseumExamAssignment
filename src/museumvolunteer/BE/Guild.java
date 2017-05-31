package museumvolunteer.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Guild extends Person{

    //instance variables
    private IntegerProperty namesId;
    
    /**
     * Default constructor.
     * @param id
     * @param name
     * @param namesId
     */
    public Guild(int id, String name, int namesId) {
        super(id, name, null, null);
        this.namesId = new SimpleIntegerProperty(namesId);
    }
    
    public Guild(int id, String name) {
        super(id, name, null, null);
    }
    
    public Guild(String name)
    {
       super(-1, name, null, null);
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

    public IntegerProperty getNamesId() {
        return namesId;
    }
}
