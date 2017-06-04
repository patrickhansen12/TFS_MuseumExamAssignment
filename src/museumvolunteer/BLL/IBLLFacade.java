package museumvolunteer.BLL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import museumvolunteer.BE.Admin;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Manager;
import museumvolunteer.BE.Volunteer;

/**
 * Interface containing all methods from NamaesManager, GuildsManager, CheckInManager and AdminManager.
 * @author Nicolai, Patrick, Kasper, Casper
 */
public interface IBLLFacade {
    public List<Volunteer> getAllVolunteers() throws SQLException;
    public Volunteer addVolunteer(Volunteer v) throws SQLException;
    public void updateVolunteer(Volunteer v) throws SQLException;
    public List<Volunteer> getAllVolunteersByGuildId(int guildsId) throws SQLException;
    public List<String> getAllVolunteerNames(int guildsId) throws SQLException;
    public void deleteVolunteerByNameIdGuildsId(int nameId, int guildsId) throws SQLException;
    public List<String> searchVolunteer(SearchPattern comparer, int guildsId) throws SQLException;
    public void addToNewGuild(int nameId, int guildsId) throws SQLException;
    
    public List<Guild> getAllGuilds() throws SQLException;
    public void deleteGuild(Guild g) throws SQLException;
    public void updateGuild(Guild g) throws SQLException;
    public Guild addGuild(Guild g) throws SQLException;
    public List<Guild> getGuildsByNameId(int nameId) throws SQLException;
    public int getByGuildsIdSumOfHours(int guildsId) throws SQLException, IOException;
    
    public List<CheckIn> getAllCheckInsByNameIdGuildsId(int guildsId, int nameId) throws SQLException, IOException;
    public List<CheckIn> exportCheckInsByNameIdGuildsIdToExcel(int guildsId, int nameId, String volunteerName, String guildName) throws SQLException, IOException;
    public List<CheckIn> exportCheckInsByGuildsIdToExcel(int guildsId, String guildName) throws SQLException, IOException;
    public CheckIn addCheckIn(CheckIn ci) throws SQLException;
    public List<CheckIn> getAllCheckIns() throws SQLException;
    public void deleteCheckIn(CheckIn ci) throws SQLException;
    public void deleteCheckInByGuildsIdNameId(int guildsId, int nameId) throws SQLException;
    
    public Manager addManager(Manager m) throws SQLException;
    public List<Manager> getAllManagers() throws SQLException;
    public void deleteManager(Manager m) throws SQLException;
    public void updateManager(Manager m) throws SQLException;

    public List<Admin> getAllAdmins() throws SQLException;

}
