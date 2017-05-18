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
     * @param nameId
     * @return
     * @throws SQLException
     * @throws java.io.IOException
     */
    public List<CheckIn> getAllCheckInsByNameId(int nameId) throws SQLException, IOException {
        return checkInDAO.getByNameId(nameId);
    }
    
    /**
     * Selects all checkIns for the chosen volunteer and prints to an excel file stored in the application folder.
     * @param nameId
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public List<CheckIn> exportCheckInsByIdToExcel(int nameId) throws SQLException, IOException {
        return checkInDAO.getByNameIdToExcel(nameId);
    }
    
    /**
     * Adds a checkIn to database table Hours.
     * @param ci
     * @return
     * @throws SQLException
     */
    public CheckIn addCheckIn(CheckIn ci) throws SQLException {
        return checkInDAO.add(ci);
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
        checkInDAO.delete(ci);
    }
    
    /**
     * Deletes a selected checkIn from database table Hours according to id.
     * @param id
     * @throws SQLException
     */
    public void deleteCheckInById(int id) throws SQLException
    {
        checkInDAO.deleteById(id);
    }
}

