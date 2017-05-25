package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */

public class Manager extends Person{

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
    public Manager(int id, String name, String email, String phoneNumber, String username, String password) {
        super(id, name, email, phoneNumber);
        this.username = username;
        this.password = password;
    }

    public Manager(String name, String email, String phoneNumber) {
        super(-1, name, email, phoneNumber);
    }
    
    public Manager(String name, String email, String phoneNumber, String username, String password) {
        super(-1, name, email, phoneNumber);
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

}
