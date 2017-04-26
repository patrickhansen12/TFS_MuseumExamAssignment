/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BE;

/**
 *
 * @author Casper
 */
public class Guild {
    private int id;
    private String name;
    private int nameId;

    public Guild(int id, String name, int nameId) {
        this.id = id;
        this.name = name;
        this.nameId = nameId;
    }

//    public Guild(int id, Guild g) {
//        this(id, g.getName(), g.getNameId());
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }
}
