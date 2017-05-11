package museumvolunteer.BE;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public abstract class Person {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;

    public Person(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
