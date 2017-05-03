/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.DAL.NamesDAO;

/**
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class NamesManager {

    private NamesDAO namesDAO;

    public NamesManager() throws IOException {
        namesDAO = new NamesDAO();
    }
    
    /**
     * Creates annd returns a new Volunteer object.
     * @param name
     * @return The new Volunteer object.
     */
    public Volunteer createNewVolunteer(String name)
    {
        return new Volunteer(name);
    }
    
    /**
     * Method for calling getAllVolunteers() in namesDAO.
     * @return
     * @throws SQLException 
     */
    public List<Volunteer> getAllVolunteers() throws SQLException {
        return namesDAO.getAllVolunteers();
    }
    
    /**
     * Method for calling add() in namesDAO.
     * @param v
     * @return
     * @throws SQLException 
     */
    public Volunteer add(Volunteer v) throws SQLException
    {
        return namesDAO.add(v);
    }
    
    /**
     * Method for calling delete() in namesDAO.
     * @param v
     * @throws SQLException 
     */
    public void delete(Volunteer v) throws SQLException
    {
        namesDAO.delete(v);
    }
    
    /**
     * ArrayList of BE class Volunteer.
     * @param guildsId
     * @return
     * @throws SQLException 
     */
    public List<Volunteer> getAllVolunteersByGuildId(int guildsId) throws SQLException {
        return namesDAO.getByGuildId(guildsId);
    }
    
    public List<String> getAllVolunteerNames() throws SQLException {
        List<Volunteer> allVolunteers = getAllVolunteersByGuildId(1);
        List<String> allVolunteerNames = new ArrayList<>();
        for (Volunteer v : allVolunteers) {
            String nameString = v.getName();
            allVolunteerNames.add(nameString);
        }
        return allVolunteerNames;
    }
    
    public List<String> search(SearchPattern comparer) throws SQLException 
    {
        List<String> allVolunteers = getAllVolunteerNames();
        List<String> result = new ArrayList<>();
        for(String name : allVolunteers)
        {
            if(comparer.compare(name))
            {
                result.add(name);
            }
        }
        return result;
    }
}
