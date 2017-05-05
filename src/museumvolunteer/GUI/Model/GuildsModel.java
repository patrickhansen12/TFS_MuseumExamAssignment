/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Guild;
import museumvolunteer.BLL.GuildsManager;

/**
 *
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class GuildsModel {
    private static GuildsModel INSTANCE;
    private final ObservableList<Guild> guilds;
    private final GuildsManager guildsMgr;

    /**
     * Constructs a new StudentManager and creates an observable arraylist out
     * of the observable list Student.
     */
    private GuildsModel() throws IOException, SQLException {
        guildsMgr = new GuildsManager();
        guilds = FXCollections.observableArrayList();
        guilds.addAll(guildsMgr.getAllGuilds());
    }

    /**
     * The method to get a reference to this Singleton:
     *
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static synchronized GuildsModel getInstance() throws IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new GuildsModel();
        }
        return INSTANCE;
    }

    /**
     * This method returns an observable list of BE class StudentCheckIn.
     * @return 
     */
    public ObservableList<Guild> getGuilds() {
        return guilds;
    }

//    /**
//     * Creates an observable list of guilds and adds all guilds on the specific student by id into CheckInManager.
//     * @param id
//     * @throws SQLException 
//     */
//    public void setCheckInListById(int id) throws SQLException {
//
//        studentCheckIn = FXCollections.observableArrayList();
//        studentCheckIn.addAll(checkInMgr.getAllCheckInsById(id));
//    }

    /**

    /*
    * adds the check ins for students
    */

    public void deleteCheckIn(Guild g) throws SQLException {
        guildsMgr.delete(g);
        guilds.remove(g);
    }
}
