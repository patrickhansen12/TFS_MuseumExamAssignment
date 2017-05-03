/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.NamesManager;

/**
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class VolunteerModel {
    //local variables for NamesManager and VolunteerModel.
    private static VolunteerModel INSTANCE;

    private final NamesManager namesManager;
    private final ObservableList<String> items;
    
    /**
     * The list of all volunteers currently in view
     */
    private ObservableList<Volunteer> allVolunteers;
    private ObservableList<Volunteer> sortedVolunteers;
    
    
    /**
     * The method to get a reference to this Singleton:
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static synchronized VolunteerModel getInstance() throws IOException, SQLException
    {
        if (INSTANCE == null)
        {
            INSTANCE = new VolunteerModel();
        }
        return INSTANCE;
    }
    
    /**
     * Constructs a new NamesManager and creates an observable arraylist out  of the observable list Volunteer.
     */
    private VolunteerModel() throws IOException, SQLException
    {
        namesManager = new NamesManager();
        allVolunteers = FXCollections.observableArrayList();
        sortedVolunteers = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
        
        allVolunteers.addAll(namesManager.getAllVolunteers());
    }
    
    /**
     * Gets the list of all volunteers added to the system.
     *
     * @return
     */
    public ObservableList<Volunteer> getAllVolunteers()
    {
        return allVolunteers;
    }
    
    public void addVolunteer(Volunteer v) throws SQLException
    {
        namesManager.add(v);
        allVolunteers.add(v);
    }
    
    public void deleteVolunteer(Volunteer v) throws SQLException
    {
        namesManager.delete(v);
        allVolunteers.remove(v);
    }
    
    public void setNamesByGuildId(int guildsId) throws SQLException {

        allVolunteers = FXCollections.observableArrayList();
        allVolunteers.addAll(namesManager.getAllVolunteersByGuildId(guildsId));
    }
    
    public ObservableList<Volunteer> getNames() {
        return sortedVolunteers;
    }
    
    public void setFilteredNames(List<String> name){
        sortedVolunteers.clear();
        items.clear();
        items.addAll(name);
        for(Volunteer volunteer : allVolunteers)
        {
            for (String volunteerName : items)
            {
                if (volunteer.getName().equals(volunteerName))
                {
                    sortedVolunteers.add(volunteer);
                }
            }
        }
    }
}
