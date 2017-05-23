package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Admin;
import museumvolunteer.BE.Manager;
import museumvolunteer.BLL.BLLFacade;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdminModel {

    private static AdminModel INSTANCE;
    private BLLFacade bllFacade;

    private ObservableList<Manager> allManagers;
    private ObservableList<Admin> allAdmins;

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

    private AdminModel() throws SQLException, IOException {
        bllFacade = new BLLFacade();
        allManagers = FXCollections.observableArrayList();
        allAdmins = FXCollections.observableArrayList();

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
     */
    public ObservableList<Manager> getAllManagers() throws SQLException {
        allManagers.addAll(bllFacade.getAllManagers());
        return allManagers;
    }

    public ObservableList<Admin> getAllAdmins() throws SQLException {
        allAdmins.addAll(bllFacade.getAllAdmins());
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
}
