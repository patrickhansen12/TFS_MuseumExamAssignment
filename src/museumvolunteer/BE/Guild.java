package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Guild{

    //instance variables
    private int id;
    private String name;
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
        this.name = name;
        this.namesId = namesId;
    }
    
    public Guild(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Guild(String name)
    {
       this(-1, name); 
    }

//    public Guild(int id, Guild g) {
//        this(id, g.getName(), g.getNameId());
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public int getNamesId()
    {
        return namesId;
    }
}
