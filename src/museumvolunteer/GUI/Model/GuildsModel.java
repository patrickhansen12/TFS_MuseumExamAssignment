package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Guild;
import museumvolunteer.BLL.GuildsManager;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class GuildsModel {

    //private variables.
    private static GuildsModel INSTANCE;
    private final GuildsManager guildsMgr;

    //Observable list of BE Guild.
    private final ObservableList<Guild> guilds;

    /**
     * Constructs a new GuildsManager and creates an observable arraylist out of
     * the observable list Guild.
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
     * This method returns an observable list of BE class Guild.
     *
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
//    /**
//     * delete checkIn.
//     */
//    public void deleteCheckIn(Guild g) throws SQLException {
//        guildsMgr.delete(g);
//        guilds.remove(g);
//    }

    /**
     * update guild name.
     * @param g
     * @throws SQLException 
     */
    public void updateGuild(Guild g) throws SQLException {
        guildsMgr.update(g);
    }
    
    public void deleteGuild(Guild g) throws SQLException
    {
        guildsMgr.delete(g);
        guilds.remove(g);
    }
    
    public void addGuild(Guild g) throws SQLException {
        guildsMgr.add(g);
        guilds.add(g);
    }
}
