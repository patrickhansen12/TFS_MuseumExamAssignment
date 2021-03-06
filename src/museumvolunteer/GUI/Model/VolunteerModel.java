package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.BLLFacade;
import museumvolunteer.BLL.SearchPattern;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class VolunteerModel {

    //instance variables.
    private BLLFacade bllFacade;
    private final ObservableList<String> items;
    private ObservableList<Volunteer> allVolunteers;
    private ObservableList<Volunteer> sortedVolunteers;
    private ObservableList<CheckIn> allCheckIns;

    /**
     * Instantiates bllFacade, populates allVolunteers and allCheckIns with data from the database.
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public VolunteerModel() throws IOException, SQLException {
        bllFacade = new BLLFacade();
        List<Volunteer> allVolunteers2 = new ArrayList<>(bllFacade.getAllVolunteers());
        allVolunteers = FXCollections.observableList(allVolunteers2);
        List<CheckIn> allCheckIns2 = new ArrayList<>(bllFacade.getAllCheckIns());
        allCheckIns = FXCollections.observableList(allCheckIns2);
        sortedVolunteers = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();
    }

    /**
     * Gets the list of all volunteers added to the system.
     * @return
     * @throws java.sql.SQLException
     */
    public ObservableList<Volunteer> getAllVolunteers() throws SQLException { 
        return allVolunteers;
    }

    /**
     * Gets all checkIns for a specific volunteer.
     * @return
     */
    public ObservableList<CheckIn> getAllCheckIns() {
        return allCheckIns;
    }

    /**
     * Adds a volunteer to observable list Volunteer and the database.
     * @param v
     * @throws SQLException
     */
    public void addVolunteer(Volunteer v) throws SQLException {
        bllFacade.addVolunteer(v);
        allVolunteers.add(v);
    }

    /**
     * Adds hours to observable list CheckIn and into the database.
     * @param ci
     * @throws SQLException
     */
    public void addHours(CheckIn ci) throws SQLException {
        bllFacade.addCheckIn(ci);
        allCheckIns.add(ci);
    }

    /**
     * Deletes the hours clicked in tableview HoursManagerTable, in observable list CheckIn and in the database.
     * @param ci
     * @throws SQLException
     */
    public void deleteHours(CheckIn ci) throws SQLException {
        bllFacade.deleteCheckIn(ci);
        allCheckIns.remove(ci);
    }

    /**
     * Deletes a selected volunteer from observable list Volunteer.
     * @param v
     * @throws SQLException
     */
    public void deleteVolunteer(Volunteer v) throws SQLException {
        allVolunteers.remove(v);
    }
    
    /**
     * Deletes the chosen volunteer from the database.
     * @param nameId
     * @param guildsId
     * @throws SQLException 
     */
    public void deleteVolunteerByNameIdGuildsId(int nameId, int guildsId) throws SQLException {
        bllFacade.deleteVolunteerByNameIdGuildsId(nameId, guildsId);
    }

    /**
     * Updates a selected volunteer in observable list Volunteer and in the database.
     * @param v
     * @throws SQLException
     */
    public void updateVolunteer(Volunteer v) throws SQLException {
        bllFacade.updateVolunteer(v);
        allVolunteers.remove(v);
        allVolunteers.add(v);
    }

    /**
     * Populates nameManagerTable and nameTable with volunteers matching the clicked guild.
     * @param guildsId
     * @throws SQLException
     */
    public void getNamesByGuildId(int guildsId) throws SQLException {
        allVolunteers = FXCollections.observableList(bllFacade.getAllVolunteersByGuildId(guildsId));
    }

    /**
     * Observable list with the purpose of being able to searchVolunteer through volunteers, when a guild is clicked.
     * @return
     */
    public ObservableList<Volunteer> getNames() {
        return sortedVolunteers;
    }

    /**
     * Method for "live" searching after a volunteer in the nameManagerTable.
     * @param name
     */
    public void setFilteredNames(List<String> name) {
        sortedVolunteers.clear();
        items.clear();
        items.addAll(name);
        for (Volunteer volunteer : allVolunteers) {
            for (String volunteerName : items) {
                if (volunteer.getNameAsString().equals(volunteerName)) {
                    sortedVolunteers.add(volunteer);
                }
            }
        }
    }

    /**
     * Method for selecting a specific volunteer, so you can add hours to him/her.
     * @param guildsId
     * @param nameId
     * @throws SQLException
     * @throws java.io.IOException
     */
    public void setCheckInsByNameIdGuildsId(int guildsId, int nameId) throws SQLException, IOException {
        allCheckIns = FXCollections.observableList(bllFacade.getAllCheckInsByNameIdGuildsId(guildsId, nameId));
    }
    
    /**
     * Populates allCheckIns with checkIns according to nameId and guildsId of the chosen volunteer, and exports to excel.
     * @param guildsId
     * @param nameId
     * @param volunteerName
     * @param guildName
     * @throws SQLException
     * @throws IOException 
     */
    public void setCheckInsByNameIdGuildsIdToExcel(int guildsId, int nameId, String volunteerName, String guildName) throws SQLException, IOException {
        bllFacade.exportCheckInsByNameIdGuildsIdToExcel(guildsId, nameId, volunteerName, guildName);
    }
    
    /**
     * Populates allCheckIns with checkIns according to guildsId of the chosen guild, and exports to excel.
     * @param guildsId
     * @param guildName
     * @throws SQLException
     * @throws IOException 
     */
    public void setCheckInsByGuildsIdToExcel(int guildsId, String guildName) throws SQLException, IOException {
        bllFacade.exportCheckInsByGuildsIdToExcel(guildsId, guildName);
    }

    /**
     * Parses nameId and guildsId into addToNewGuild() in bllFacade.
     * @param nameId
     * @param guildsId
     * @throws SQLException 
     */
    public void addToNewGuild(int nameId, int guildsId) throws SQLException {
        bllFacade.addToNewGuild(nameId, guildsId);
    }
    
    /**
     * Method for parsing guildsId of the chosen guild into getByGuildsIdSumOfHours() in bllFacade. Returns the summed up hours for the guild.
     * @param guildsId
     * @return
     * @throws SQLException
     * @throws IOException 
     */
    public List<Integer> getByGuildsIdSumOfHoursList(int guildsId) throws SQLException, IOException
    {
        List<Integer> getHours = new ArrayList<>();
        getHours.add(bllFacade.getByGuildsIdSumOfHours(guildsId));
        return getHours;
    }
    
    /**
     * Parses comparer and guildsId into searchVolunteer in bllFacade.
     * @param comparer
     * @param guildsId
     * @return
     * @throws SQLException 
     */
    public List<String> search(SearchPattern comparer, int guildsId) throws SQLException
    {
        return bllFacade.searchVolunteer(comparer, guildsId);
    }
}
