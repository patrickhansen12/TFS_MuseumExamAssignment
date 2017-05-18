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
    public void deleteVolunteer(Volunteer v) throws SQLException {
        namesManager.deleteVolunteer(v);
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
    public List<String> search(SearchPattern comparer, int guildsId) throws SQLException {
        return namesManager.search(comparer, guildsId);
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
    public List<CheckIn> getAllCheckInsByNameId(int nameId) throws SQLException, IOException {
        return checkInManager.getAllCheckInsByNameId(nameId);
    }

    @Override
    public List<CheckIn> exportCheckInsByIdToExcel(int nameId) throws SQLException, IOException {
        return checkInManager.exportCheckInsByIdToExcel(nameId);
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
    public void deleteCheckInById(int id) throws SQLException {
        checkInManager.deleteCheckInById(id);
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
}
