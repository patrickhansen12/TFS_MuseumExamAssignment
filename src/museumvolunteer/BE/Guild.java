package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Guild {

    //Instance variables.
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
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
