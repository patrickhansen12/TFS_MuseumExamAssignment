/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
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
    
    /**
     * The list of all volunteers currently in view
     */
    private final ObservableList<Volunteer> allVolunteers;
    
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
     * Constructs a new NamesManager and creates an observable arraylist out  of the observable list Student.
     */
    private VolunteerModel() throws IOException, SQLException
    {
        namesManager = new NamesManager();
        allVolunteers = FXCollections.observableArrayList();
        
        allVolunteers.addAll(namesManager.getAllVolunteers());
    }
}
