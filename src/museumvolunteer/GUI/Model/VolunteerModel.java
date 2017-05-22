package museumvolunteer.GUI.Model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.BLLFacade;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class VolunteerModel {

    //local variables.
    private static VolunteerModel INSTANCE;
    private BLLFacade bllFacade;

    /**
     * Lists of all volunteers and checkIns currently in view.
     */
    private final ObservableList<String> items;
    private ObservableList<Volunteer> allVolunteers;
    private ObservableList<Volunteer> sortedVolunteers;
    private ObservableList<CheckIn> allCheckIns;

    /**
     * The method to get a reference to this Singleton:
     *
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    public static synchronized VolunteerModel getInstance() throws IOException, SQLException {
        if (INSTANCE == null) {
            INSTANCE = new VolunteerModel();
        }
        return INSTANCE;
    }

    /**
     * Constructs new name- and checkInManagers, creates observable arraylists
     * out of the observable lists Volunteer and CheckIn.
     */
    private VolunteerModel() throws IOException, SQLException {
        bllFacade = new BLLFacade();
        allVolunteers = FXCollections.observableArrayList();
        sortedVolunteers = FXCollections.observableArrayList();
        items = FXCollections.observableArrayList();

        allVolunteers.addAll(bllFacade.getAllVolunteers());
        allCheckIns = FXCollections.observableArrayList();
        allCheckIns.addAll(bllFacade.getAllCheckIns());
    }

    /**
     * Gets the list of all volunteers added to the system.
     * @return
     */
    public ObservableList<Volunteer> getAllVolunteers() {
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
     * Deletes a selected volunteer from observable list Volunteer and the database.
     * @param v
     * @throws SQLException
     */
    public void deleteVolunteer(Volunteer v) throws SQLException {
        bllFacade.deleteVolunteer(v);
        allVolunteers.remove(v);
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
    public void setNamesByGuildId(int guildsId) throws SQLException {
        allVolunteers = FXCollections.observableArrayList();
        allVolunteers.addAll(bllFacade.getAllVolunteersByGuildId(guildsId));
    }

    /**
     * Observable list with the purpose of being able to search through volunteers, when a guild is clicked.
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
        allCheckIns = FXCollections.observableArrayList();
        allCheckIns.addAll(bllFacade.getAllCheckInsByNameIdGuildsId(guildsId, nameId));
    }
    
    public void setCheckInsByNameIdGuildsIdToExcel(int guildsId, int nameId) throws SQLException, IOException {
        allCheckIns = FXCollections.observableArrayList();
        allCheckIns.addAll(bllFacade.exportCheckInsByNameIdGuildsIdToExcel(guildsId, nameId));
    }

    public void addToNewGuild(int nameId, int guildsId) throws SQLException {
        bllFacade.addToNewGuild(nameId, guildsId);
    }
}
