package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.DAL.NamesDAO;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class NamesManager {

    //private variable for namesDAO.
    private NamesDAO namesDAO;

    /**
     * Creates a new NamesDAO.
     *
     * @throws IOException
     */
    public NamesManager() throws IOException {
        namesDAO = new NamesDAO();
    }

    /**
     * Method for calling getAllVolunteers() in namesDAO.
     *
     * @return
     * @throws SQLException
     */
    public List<Volunteer> getAllVolunteers() throws SQLException {
        return namesDAO.getAllVolunteers();
    }

    /**
     * Method for calling add() in namesDAO.
     *
     * @param v
     * @return
     * @throws SQLException
     */
    public Volunteer addVolunteer(Volunteer v) throws SQLException {
        return namesDAO.add(v);
    }
    
    public void deleteVolunteerByNameIdGuildsId(int nameId, int guildsId) throws SQLException
    {
        namesDAO.deleteVolunteerByNameIdGuildsId(nameId, guildsId);
    }

    /**
     * Updates values for the specified volunteer.
     *
     * @param v
     * @throws SQLException
     */
    public void updateVolunteer(Volunteer v) throws SQLException {
        namesDAO.update(v);
    }

    /**
     * ArrayList of BE class Volunteer.
     *
     * @param guildsId
     * @return
     * @throws SQLException
     */
    public List<Volunteer> getAllVolunteersByGuildId(int guildsId) throws SQLException {
        return namesDAO.getByGuildId(guildsId);
    }

    /**
     * Gets all volunteer names according to guildsId.
     *
     * @param guildsId
     * @return
     * @throws SQLException
     */
    public List<String> getAllVolunteerNames(int guildsId) throws SQLException {
        List<Volunteer> allVolunteers = getAllVolunteersByGuildId(guildsId);
        List<String> allVolunteerNames = new ArrayList<>();
        for (Volunteer v : allVolunteers) {
            String nameString = v.getNameAsString();
            allVolunteerNames.add(nameString);
        }
        return allVolunteerNames;
    }

    /**
     * Method for searching through the selected list of volunteers.
     *
     * @param comparer
     * @param guildsId
     * @return
     * @throws SQLException
     */
    public List<String> search(SearchPattern comparer, int guildsId) throws SQLException {
        List<String> allVolunteers = getAllVolunteerNames(guildsId);
        List<String> result = new ArrayList<>();
        for (String name : allVolunteers) {
            if (comparer.compare(name)) {
                result.add(name);
            }
        }
        return result;
    }

    public void addToNewGuild(int nameId, int guildsId) throws SQLException {
        namesDAO.addToNewGuild(nameId, guildsId);
    }
}
