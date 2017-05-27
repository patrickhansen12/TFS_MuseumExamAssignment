package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Guild;
import museumvolunteer.BLL.BLLFacade;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class GuildsModel {

    //private variables.
    //private static GuildsModel INSTANCE;
    private BLLFacade bllFacade;

    //Observable list of BE Guild.
    private ObservableList<Guild> guilds;
    private ObservableList<Guild> guildsByNameId;

    /**
     * Constructs a new GuildsManager and creates an observable arraylist out of
     * the observable list Guild.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public GuildsModel() throws IOException, SQLException {
        this.guilds = guilds;
        bllFacade = new BLLFacade();
        List<Guild> guilds2 = new ArrayList<>(bllFacade.getAllGuilds());
        guilds = FXCollections.observableList(guilds2);
    }

//    /**
//     * The method to get a reference to this Singleton:
//     *
//     * @return
//     * @throws java.io.IOException
//     * @throws java.sql.SQLException
//     */
//    public static synchronized GuildsModel getInstance() throws IOException, SQLException {
//        if (INSTANCE == null) {
//            INSTANCE = new GuildsModel();
//        }
//        return INSTANCE;
//    }

    /**
     * This method returns an observable list of BE class Guild.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ObservableList<Guild> getGuilds() throws SQLException {
        return guilds;
    }
    
    public List<Guild> getGuildsFromFacade() throws SQLException
    {
        guilds.clear();
        guilds.addAll(bllFacade.getAllGuilds());
        return guilds;
    }
    
    public ObservableList<Guild> getGuildsByNameId(int nameId) throws SQLException 
    {
        guildsByNameId = FXCollections.observableList(bllFacade.getGuildsByNameId(nameId));
        return guildsByNameId;
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
        bllFacade.updateGuild(g);
    }
    
    public void deleteGuild(Guild g) throws SQLException
    {
        bllFacade.deleteGuild(g);
        guilds.remove(g);
    }
    
    public void addGuild(Guild g) throws SQLException {
        bllFacade.addGuild(g);
        guilds.add(g);
    }
}
