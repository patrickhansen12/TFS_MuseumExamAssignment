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

    //instance variables.
    private BLLFacade bllFacade;

    //Observable lists of BE Guild.
    private ObservableList<Guild> guilds;
    private ObservableList<Guild> guildsByNameId;

    /**
     * Constructs a new GuildsManager and creates an observable arraylist out of
     * the observable list Guild.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public GuildsModel() throws IOException, SQLException {
        bllFacade = new BLLFacade();
        List<Guild> guilds2 = new ArrayList<>(bllFacade.getAllGuilds());
        guilds = FXCollections.observableList(guilds2);
    }

    /**
     * This method returns an observable list of BE class Guild.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ObservableList<Guild> getGuilds() throws SQLException {
        return guilds;
    }
    
    /**
     * Clears the observable list guilds, adds all data from the database and returns list guilds.
     * @return
     * @throws SQLException 
     */
    public List<Guild> getGuildsFromFacade() throws SQLException
    {
        guilds.clear();
        guilds.addAll(bllFacade.getAllGuilds());
        return guilds;
    }
    
    /**
     * Populates observable list guildsByNameId with data from the database, and then returns the list guildsByNameId.
     * @param nameId
     * @return
     * @throws SQLException 
     */
    public ObservableList<Guild> getGuildsByNameId(int nameId) throws SQLException 
    {
        guildsByNameId = FXCollections.observableList(bllFacade.getGuildsByNameId(nameId));
        return guildsByNameId;
    }

    /**
     * update guild name.
     * @param g
     * @throws SQLException 
     */
    public void updateGuild(Guild g) throws SQLException {
        bllFacade.updateGuild(g);
    }
    
    /**
     * Deletes the chosen guild from the database and observable list guilds.
     * @param g
     * @throws SQLException 
     */
    public void deleteGuild(Guild g) throws SQLException
    {
        bllFacade.deleteGuild(g);
        guilds.remove(g);
    }
    
    /**
     * Adds a guild to the database and the observable list guilds.
     * @param g
     * @throws SQLException 
     */
    public void addGuild(Guild g) throws SQLException {
        bllFacade.addGuild(g);
        guilds.add(g);
    }
}
