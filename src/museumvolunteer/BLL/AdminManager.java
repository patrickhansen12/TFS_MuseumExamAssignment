package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import museumvolunteer.BE.Admin;
import museumvolunteer.BE.Manager;
import museumvolunteer.DAL.AdminDAO;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdminManager {

    private AdminDAO managerDAO;
    
    public AdminManager() throws IOException {
        managerDAO = new AdminDAO();
    }
    
    /**
     * Method for returning addManager() in managerDAO.
     * @param m
     * @return
     * @throws SQLException
     */
    public Manager addManager(Manager m) throws SQLException {
        return managerDAO.addManager(m);
    }
    
    /**
     * Method for returning getAllManagers() in managerDAO.
     * @return
     * @throws SQLException
     */
    public List<Manager> getAllManagers() throws SQLException {
        return managerDAO.getAllManagers();
    }
    
    /**
     * Method for calling deleteManager() in managerDAO.
     * @param m
     * @throws SQLException
     */
    public void deleteManager(Manager m) throws SQLException {
        managerDAO.deleteManager(m);
    }
    
    /**
     * Method for calling getAllAdmins() in managerDAO.
     * @return
     * @throws SQLException 
     */
    public List<Admin> getAllAdmins() throws SQLException {
        return managerDAO.getAllAdmins();
    }
    
    /**
     * Method for calling getAllAdmins() in managerDAO.
     * @throws SQLException 
     */
    public void updateManager(Manager m) throws SQLException {
        managerDAO.updateManager(m);
    }
}
