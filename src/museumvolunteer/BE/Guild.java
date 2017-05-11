package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Guild{

    //instance variables
    private int id;
    private String name;
    /**
     * Default constructor.
     *
     * @param id
     * @param name
     */
    public Guild(int id, String name) {
        this.id = id;
        this.name = name;
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
}
