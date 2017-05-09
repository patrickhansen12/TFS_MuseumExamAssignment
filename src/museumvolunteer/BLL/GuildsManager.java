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

//    /**
//     * ArrayList of BE class Guild.
//     * @param id
//     * @return
//     * @throws SQLException 
//     */
//    public List<Guild> getAllGuildsById(int id) throws SQLException {
//        return guildsDAO.getByNameId(id);
//    }
//    /**
//     * Method for calling add() in guildsDAO.
//     * @param g
//     * @return
//     * @throws SQLException 
//     */
//    public Guild add(Guild g) throws SQLException {
//        return guildsDAO.add(g);
//    }
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
     * Method for calling delete() in guildsDAO.
     *
     * @param g
     * @throws SQLException
     */
    public void delete(Guild g) throws SQLException {
        guildsDAO.delete(g);
    }

//    /**
//     * Method for calling deleteByNameId() in guildsDAO.
//     * @param id
//     * @throws SQLException 
//     */
//    public void deleteByNameId(int id) throws SQLException
//    {
//        guildsDAO.deleteByNameId(id);
//    }
    /**
     * Updates the name of the guild clicked.
     * @param g
     * @throws SQLException
     */
    public void update(Guild g) throws SQLException {
        guildsDAO.update(g);
    }
}
