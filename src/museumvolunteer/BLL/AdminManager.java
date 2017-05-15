/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import museumvolunteer.BE.Manager;
import museumvolunteer.DAL.AdminDAO;

/**
 *
 * @author Casper
 */
public class AdminManager {

    private Manager manager;
    private AdminDAO managerDAO;
    
    public AdminManager() throws IOException {
        managerDAO = new AdminDAO();
    }
    
    /**
     * Method for calling addManager() in managerDAO.
     *
     * @param m
     * @return
     * @throws SQLException
     */
    public Manager addManager(Manager m) throws SQLException {
        return managerDAO.addManager(m);
    }
    
    /**
     * Method for calling getAllManagers() in managerDAO.
     *
     * @return
     * @throws SQLException
     */
    public List<Manager> getAllManagers() throws SQLException {
        return managerDAO.getAllManagers();
    }
    
    /**
     * Method for calling deleteManager() in managerDAO.
     *
     * @param m
     * @throws SQLException
     */
    public void deleteManager(Manager m) throws SQLException {
        managerDAO.deleteManager(m);
    }
}
