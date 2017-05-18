/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Manager;
import museumvolunteer.BE.Volunteer;

/**
 *
 * @author Casper
 */
public interface IBLLFacade {
    public List<Volunteer> getAllVolunteers() throws SQLException;
    public Volunteer addVolunteer(Volunteer v) throws SQLException;
    public void deleteVolunteer(Volunteer v) throws SQLException;
    public void updateVolunteer(Volunteer v) throws SQLException;
    public List<Volunteer> getAllVolunteersByGuildId(int guildsId) throws SQLException;
    public List<String> getAllVolunteerNames(int guildsId) throws SQLException;
    public List<String> search(SearchPattern comparer, int guildsId) throws SQLException;
    public void addToNewGuild(int nameId, int guildsId) throws SQLException;
    
    public List<Guild> getAllGuilds() throws SQLException;
    public void deleteGuild(Guild g) throws SQLException;
    public void updateGuild(Guild g) throws SQLException;
    public Guild addGuild(Guild g) throws SQLException;
    
    public List<CheckIn> getAllCheckInsByNameId(int nameId) throws SQLException, IOException;
    public List<CheckIn> exportCheckInsByIdToExcel(int nameId) throws SQLException, IOException;
    public CheckIn addCheckIn(CheckIn ci) throws SQLException;
    public List<CheckIn> getAllCheckIns() throws SQLException;
    public void deleteCheckIn(CheckIn ci) throws SQLException;
    public void deleteCheckInById(int id) throws SQLException;
    
    public Manager addManager(Manager m) throws SQLException;
    public List<Manager> getAllManagers() throws SQLException;
    public void deleteManager(Manager m) throws SQLException;
}
