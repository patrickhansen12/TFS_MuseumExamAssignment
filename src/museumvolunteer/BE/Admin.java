package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Admin extends Person{

    //instance variables
    private String username;
    private String password;

    /**
     * Default constructor.
     *
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     * @param username
     * @param password
     */
    public Admin(int id, String name, String email, String phoneNumber, String username, String password) {
        super(id, name, email, phoneNumber);
        this.username = username;
        this.password = password;
    }
    
    public Admin(int id, String name) {
        super(id, name, null, null);
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
}