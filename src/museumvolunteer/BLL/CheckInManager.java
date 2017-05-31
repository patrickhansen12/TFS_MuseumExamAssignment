package museumvolunteer.BLL;

import museumvolunteer.BE.CheckIn;
import museumvolunteer.DAL.CheckInDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class CheckInManager {
    
    //private variable for checkInDAO.
    private CheckInDAO checkInDAO;

    /**
     * Creates a new CheckInDAO object.
     * @throws IOException
     */
    public CheckInManager() throws IOException {
        checkInDAO = new CheckInDAO();
    }
    
    /**
     * Gets all checkIns according to nameId.
     * @param guildsId
     * @param nameId
     * @return
     * @throws SQLException
     * @throws java.io.IOException
     */
    public List<CheckIn> getAllCheckInsByNameIdGuildsId(int guildsId, int nameId) throws SQLException, IOException {
        return checkInDAO.getByNameIdGuildsId(guildsId, nameId);
    }
    
    /**
     * Selects all checkIns for the chosen volunteer and prints to an excel file stored in the application folder.
     * @param guildsId
     * @param nameId
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public List<CheckIn> exportCheckInsByNameIdGuildsIdToExcel(int guildsId, int nameId) throws SQLException, IOException {
        return checkInDAO.getByNameIdGuildsIdToExcel(guildsId, nameId);
    }
    
    /**
     * Selects all checkIns for the chosen guild and prints to an excel file stored in the application folder.
     * @param guildsId
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public List<CheckIn> exportCheckInsByGuildsIdToExcel(int guildsId) throws SQLException, IOException {
        return checkInDAO.getByGuildsIdToExcel(guildsId);
    }
    
    /**
     * Returns getByGuildsIdSumOfHours() from checkInDAO. Parses guildsId from the GUI layer.
     * @param guildsId
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public int getByGuildsIdSumOfHours(int guildsId) throws SQLException, IOException {
        return checkInDAO.getByGuildsIdSumOfHours(guildsId);
    }
    
    /**
     * Adds a checkIn to database table Hours.
     * @param ci
     * @return
     * @throws SQLException
     */
    public CheckIn addCheckIn(CheckIn ci) throws SQLException {
        return checkInDAO.addCheckIn(ci);
    }
    
    /**
     * Gets all checkIns.
     * @return
     * @throws SQLException
     */
    public List<CheckIn> getAllCheckIns() throws SQLException
    {
        return checkInDAO.getAll();
    }

    /**
     * Deletes a selected checkIn from database table Hours.
     * @param ci
     * @throws SQLException
     */
    public void deleteCheckIn(CheckIn ci) throws SQLException {
        checkInDAO.deleteCheckIn(ci);
    }
    
    /**
     * Deletes a selected checkIn from database table Hours according to guildsId and nameId.
     * @param guildsId
     * @param nameId
     * @throws SQLException
     */
    public void deleteCheckInByGuildsIdNameId(int guildsId, int nameId) throws SQLException
    {
        checkInDAO.deleteByGuildsIdNameId(guildsId, nameId);
    }
}

