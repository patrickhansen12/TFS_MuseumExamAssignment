package museumvolunteer.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Guild{

    //instance variables
    private int id;
    private StringProperty name;
    private int namesId;
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
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.namesId = namesId;
    }
    
    public Guild(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }
    
    public Guild(String name)
    {
       this.id = -1;
       this.name = new SimpleStringProperty(name);
    }

//    public Guild(int id, Guild g) {
//        this(id, g.getName(), g.getNameId());
//    }

    public int getId() {
        return id;
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
    
    public int getNamesId()
    {
        return namesId;
    }
}
