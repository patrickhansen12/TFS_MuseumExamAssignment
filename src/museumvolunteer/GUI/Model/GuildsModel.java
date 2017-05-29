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
