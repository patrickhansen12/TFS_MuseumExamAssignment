/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BLL;

/**
 *
 * @author patrick
 */
import museumvolunteer.BE.CheckIn;
import museumvolunteer.DAL.CheckInDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Jens, Patrick, Casper, Kasper
 */
public class CheckInManager {
    
    
    private CheckInDAO checkInDAO;

    public CheckInManager() throws IOException {
        checkInDAO = new CheckInDAO();
    }
    
    
    
    public List<CheckIn> getAllCheckInsById(int id) throws SQLException {
        return checkInDAO.getByVoulunteerId(id);
    }
    
    

    public CheckIn add(CheckIn sCheckIn) throws SQLException {
        return checkInDAO.add(sCheckIn);
    }
    
    public List<CheckIn> getCheckIn() throws SQLException
    {
        return checkInDAO.getAllCheckIns();
    }

    public void delete(CheckIn studCheckIn) throws SQLException {
        checkInDAO.delete(studCheckIn);
    }
    
    public void deleteById(int id) throws SQLException
    {
        checkInDAO.deleteById(id);
    }
}

