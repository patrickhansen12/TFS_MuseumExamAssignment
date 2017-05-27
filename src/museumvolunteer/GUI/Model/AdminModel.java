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

    //private static AdminModel INSTANCE;
    private BLLFacade bllFacade;

    private ObservableList<Manager> allManagers;
    private ObservableList<Admin> allAdmins;

//    /**
//     * The method to get a reference to this Singleton:
//     *
//     * @return
//     * @throws java.io.IOException
//     * @throws java.sql.SQLException
//     */
//    public static synchronized AdminModel getInstance() throws IOException, SQLException {
//        if (INSTANCE == null) {
//            INSTANCE = new AdminModel();
//        }
//        return INSTANCE;
//    }

    public AdminModel() throws SQLException, IOException {
        this.allManagers = allManagers;
        bllFacade = new BLLFacade();
        List<Manager> allManagers2 = new ArrayList<>(bllFacade.getAllManagers());
        allManagers = FXCollections.observableList(allManagers2);
        List<Admin> allAdmins2 = new ArrayList<>(bllFacade.getAllAdmins());
        allAdmins = FXCollections.observableList(allAdmins2);
        //allAdmins.addAll();
//        allManagers.addListener(new ListChangeListener() 
//        {
//            @Override
//            public void onChanged(ListChangeListener.Change c) 
//            {
//                try {
//                    if(c.next())
//                        System.out.println(c.wasRemoved());
//                    getAllManagers();
//                    if(c.wasRemoved()) 
//                    {
//                        System.out.println(c.wasUpdated());
//                    }
//                } catch (SQLException ex) {
//                    Logger.getLogger(AdminModel.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            
//        });

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
    
//    public ObservableList<Manager> getAllManagersFromFacade() throws SQLException 
//    {       
//        allManagers.clear();
//        allManagers.addAll(bllFacade.getAllManagers());
//        return allManagers;
//    }

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
    
    public void updateManager(Manager m) throws SQLException {
        bllFacade.updateManager(m);
        allManagers.remove(m);
        allManagers.add(m);

    }
}
