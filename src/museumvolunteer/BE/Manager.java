package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class Manager extends Person{

    /**
     * Default constructor.
     *
     * @param id
     * @param name
     * @param email
     * @param phoneNumber
     */
    public Manager(int id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
    }

    public Manager(String name, String email, String phoneNumber) {
        super(0, name, email, phoneNumber);
    }
}
