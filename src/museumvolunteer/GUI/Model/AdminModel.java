package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Admin;
import museumvolunteer.BE.Manager;
import museumvolunteer.BE.ManagerMock;
import museumvolunteer.BLL.BLLFacade;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdminModel {

    //instance variables.
    private BLLFacade bllFacade;
    private ObservableList<Manager> allManagers;
    private ObservableList<Admin> allAdmins;
    private ObservableList<ManagerMock> allManagersMock = FXCollections.observableArrayList();

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
    
    public ObservableList<ManagerMock> getAllManagersMock()
    {
        new ManagerMock("Allan", "1");
        new ManagerMock("Hans", "2");
        new ManagerMock("Ulla", "3");
        new ManagerMock("Hermann", "4");
        new ManagerMock("Benny", "5");
        new ManagerMock("Bertha", "6");
        new ManagerMock("Ulrik", "7");
        new ManagerMock("Matilde", "8");
        new ManagerMock("Henry", "9");
        new ManagerMock("Susanne", "10");
        new ManagerMock("Gurli", "11");
        new ManagerMock("Magnus", "12");
        new ManagerMock("Mikkel", "13");
        new ManagerMock("Arne", "14");
        new ManagerMock("Martin", "15");
    }
}
