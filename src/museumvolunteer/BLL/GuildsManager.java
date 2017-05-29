package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import museumvolunteer.BE.Guild;
import museumvolunteer.DAL.GuildsDAO;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class GuildsManager {

    //private variable for guildsDAO.
    private GuildsDAO guildsDAO;

    /**
     * Creates a new guildsDAO object.
     *
     * @throws IOException
     */
    public GuildsManager() throws IOException {
        guildsDAO = new GuildsDAO();
    }

    /**
     * Method for calling getAllGuilds() in guildsDAO.
     *
     * @return
     * @throws SQLException
     */
    public List<Guild> getAllGuilds() throws SQLException {
        return guildsDAO.getAll();
    }

    /**
     * Method for calling deleteGuild() in guildsDAO.
     *
     * @param g
     * @throws SQLException
     */
    public void deleteGuild(Guild g) throws SQLException {
        guildsDAO.deleteGuild(g);
    }

    /**
     * Updates the name of the guild clicked.
     * @param g
     * @throws SQLException
     */
    public void updateGuild(Guild g) throws SQLException {
        guildsDAO.updateGuild(g);
    }
    
    public Guild addGuild(Guild g) throws SQLException {
        return guildsDAO.addGuild(g);
    }

    public List<Guild> getGuildsByNameId(int nameId) throws SQLException {
        return guildsDAO.getGuildsByNameId(nameId);
    }
}
