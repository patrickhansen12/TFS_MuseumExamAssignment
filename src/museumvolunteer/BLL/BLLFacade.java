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
 * Facade for decoupling bll from gui. Contains references to all methods in NamesManager, GuildsManager, CheckInManager and AdminManager.
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class BLLFacade implements IBLLFacade{

    private AdminManager adminManager;
    private CheckInManager checkInManager;
    private GuildsManager guildsManager;
    private NamesManager namesManager;

    public BLLFacade() throws IOException {
        adminManager = new AdminManager();
        checkInManager = new CheckInManager();
        guildsManager = new GuildsManager();
        namesManager = new NamesManager();
    }

    @Override
    public List<Volunteer> getAllVolunteers() throws SQLException {
        return namesManager.getAllVolunteers();
    }

    @Override
    public Volunteer addVolunteer(Volunteer v) throws SQLException {
        return namesManager.addVolunteer(v);
    }
    
    @Override
    public void deleteVolunteerByNameIdGuildsId(int nameId, int guildsId) throws SQLException {
        namesManager.deleteVolunteerByNameIdGuildsId(nameId, guildsId);
    }

    @Override
    public void updateVolunteer(Volunteer v) throws SQLException {
        namesManager.updateVolunteer(v);
    }

    @Override
    public List<Volunteer> getAllVolunteersByGuildId(int guildsId) throws SQLException {
        return namesManager.getAllVolunteersByGuildId(guildsId);
    }

    @Override
    public List<String> getAllVolunteerNames(int guildsId) throws SQLException {
        return namesManager.getAllVolunteerNames(guildsId);
    }

    @Override
    public List<String> searchVolunteer(SearchPattern comparer, int guildsId) throws SQLException {
        return namesManager.searchVolunteers(comparer, guildsId);
    }

    @Override
    public void addToNewGuild(int nameId, int guildsId) throws SQLException {
        namesManager.addToNewGuild(nameId, guildsId);
    }

    @Override
    public List<Guild> getAllGuilds() throws SQLException {
        return guildsManager.getAllGuilds();
    }

    @Override
    public void deleteGuild(Guild g) throws SQLException {
        guildsManager.deleteGuild(g);
    }

    @Override
    public void updateGuild(Guild g) throws SQLException {
        guildsManager.updateGuild(g);
    }

    @Override
    public Guild addGuild(Guild g) throws SQLException {
        return guildsManager.addGuild(g);
    }

    @Override
    public List<CheckIn> getAllCheckInsByNameIdGuildsId(int guildsId, int nameId) throws SQLException, IOException {
        return checkInManager.getAllCheckInsByNameIdGuildsId(guildsId, nameId);
    }

    @Override
    public List<CheckIn> exportCheckInsByNameIdGuildsIdToExcel(int guildsId, int nameId, String volunteerName, String guildName) throws SQLException, IOException {
        return checkInManager.exportCheckInsByNameIdGuildsIdToExcel(guildsId, nameId, volunteerName, guildName);
    }
    
    @Override
    public List<CheckIn> exportCheckInsByGuildsIdToExcel(int guildsId, String guildName) throws SQLException, IOException {
        return checkInManager.exportCheckInsByGuildsIdToExcel(guildsId, guildName);
    }
    
    @Override
    public int getByGuildsIdSumOfHours(int guildsId) throws SQLException, IOException {
        return checkInManager.getByGuildsIdSumOfHours(guildsId);
    }

    @Override
    public CheckIn addCheckIn(CheckIn ci) throws SQLException {
        return checkInManager.addCheckIn(ci);
    }

    @Override
    public List<CheckIn> getAllCheckIns() throws SQLException {
        return checkInManager.getAllCheckIns();
    }

    @Override
    public void deleteCheckIn(CheckIn ci) throws SQLException {
        checkInManager.deleteCheckIn(ci);
    }

    @Override
    public void deleteCheckInByGuildsIdNameId(int guildsId, int nameId) throws SQLException {
        checkInManager.deleteCheckInByGuildsIdNameId(guildsId, nameId);
    }

    @Override
    public Manager addManager(Manager m) throws SQLException {
        return adminManager.addManager(m);
    }

    @Override
    public List<Manager> getAllManagers() throws SQLException {
        return adminManager.getAllManagers();
    }

    @Override
    public void deleteManager(Manager m) throws SQLException {
        adminManager.deleteManager(m);
    }
    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        return adminManager.getAllAdmins();
    }

    @Override
    public List<Guild> getGuildsByNameId(int nameId) throws SQLException {
        return guildsManager.getGuildsByNameId(nameId);
    }
    
    @Override
    public void updateManager(Manager m) throws SQLException {
        adminManager.updateManager(m);
    }
}
