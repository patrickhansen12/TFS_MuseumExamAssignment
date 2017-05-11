package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Volunteer extends Person{
    
    //Instance variables.
    private int guildsId;
    /**
     * Default constructor.
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     * @param guildsId
     */
    public Volunteer(int id, String name, String email, String phoneNumber, int guildsId) {
        super(id, name, email, phoneNumber);
        this.guildsId = guildsId;
    }

    /**
     * Contructor for only returning name.
     * @param name
     */
    public Volunteer(String name) {
        super(0, name, null, null);
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
       super(0, name, email, phoneNumber); 
       this.guildsId = guildsId;
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
    public int getGuildsId() {
        return guildsId;
    }

    /**
     *
     * @param guildsId
     */
    public void setGuildsId(int guildsId) {
        this.guildsId = guildsId;
    }
}
