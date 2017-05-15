package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Manager;
import museumvolunteer.BLL.AdminManager;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdminModel {
    
    private static AdminModel INSTANCE;
    private final AdminManager adminManager;
    
    private ObservableList<Manager> allManagers;
    
    /**
     * The method to get a reference to this Singleton:
     *
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static synchronized AdminModel getInstance() throws IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new AdminModel();
        }
        return INSTANCE;
    }
    
    private AdminModel() throws SQLException, IOException
    {
        adminManager = new AdminManager();
        allManagers = FXCollections.observableArrayList();
        allManagers.addAll(adminManager.getAllManagers());
        
    }
    
    /**
     * Adds a manager to observable list Managers and the database.
     * @param m
     * @throws SQLException
     */
    public void addManager(Manager m) throws SQLException {
        adminManager.addManager(m);
        allManagers.add(m);
    }
    
    /**
     * This method returns an observable list of BE class Guild.
     *
     * @return
     */
    public ObservableList<Manager> getAllManagers() {
        return allManagers;
    }
    
    /**
     * Deletes a selected manager from observable list allManagers and the database.
     * @param m
     * @throws SQLException
     */
    public void deleteManager(Manager m) throws SQLException {
        adminManager.deleteManager(m);
        allManagers.remove(m);
    }
}
