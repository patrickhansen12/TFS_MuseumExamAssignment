package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Admin;
import museumvolunteer.BE.Manager;

import museumvolunteer.BLL.BLLFacade;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdminModel {

    //instance variables.
    private BLLFacade bllFacade;
    private ObservableList<Manager> allManagers;
    private ObservableList<Admin> allAdmins;
 

    /**
     * Instantiates bllFacade, populates allManagers and allAdmins with data from the database through bllFacade.
     * @throws SQLException
     * @throws IOException 
     */
    public AdminModel() throws SQLException, IOException {
        bllFacade = new BLLFacade();
        List<Manager> allManagers2 = new ArrayList<>(bllFacade.getAllManagers());
        allManagers = FXCollections.observableList(allManagers2);
        List<Admin> allAdmins2 = new ArrayList<>(bllFacade.getAllAdmins());
        allAdmins = FXCollections.observableList(allAdmins2);      
    }

    /**
     * Adds a manager to observable list Managers and the database.
     *
     * @param m
     * @throws SQLException
     */
    public void addManager(Manager m) throws SQLException {
        
        bllFacade.addManager(m);
        allManagers.add(m);
    }

    /**
     * This method returns an observable list of BE class Guild.
     *
     * @return
     * @throws java.sql.SQLException
     */
    public ObservableList<Manager> getAllManagers() throws SQLException {
        return allManagers;
    }

    /**
     * Method for returning all data from an observable list of BE class Admin.
     * @return
     * @throws SQLException 
     */
    public ObservableList<Admin> getAllAdmins() throws SQLException {
        return allAdmins;
    }

    /**
     * Deletes a selected manager from observable list allManagers and the
     * database.
     *
     * @param m
     * @throws SQLException
     */
    public void deleteManager(Manager m) throws SQLException {
        bllFacade.deleteManager(m);
        allManagers.remove(m);
    }
    
    /**
     * Updates the values of a chosen manager.
     * @param m
     * @throws SQLException 
     */
    public void updateManager(Manager m) throws SQLException {
        bllFacade.updateManager(m);
        allManagers.remove(m);
        allManagers.add(m);
    }
    

}
