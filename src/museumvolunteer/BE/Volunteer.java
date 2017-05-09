package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Volunteer {
    
    //Instance variables.
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
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
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.guildsId = guildsId;
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

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
  
        /**
     * returns name, currentClass and attendance as strings
     * @return 
     */
    @Override
    public String toString() {
        return name;
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
