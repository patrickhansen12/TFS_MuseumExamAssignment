package museumvolunteer.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.Volunteer;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class NamesDAO {

    //private variable for the connectionManager.
    private final ConnectionManager cm;

    /**
     * Creates a new connectionManager.
     *
     * @throws IOException
     */
    public NamesDAO() throws IOException {
        cm = new ConnectionManager();
    }

    /**
     * Method for selecting all volunteers in database table Names.
     *
     * @return
     * @throws SQLException
     */
    public List<Volunteer> getAllVolunteers() throws SQLException {
        List<Volunteer> allVolunteers = new ArrayList<>();

        String sql = "SELECT * FROM Names";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allVolunteers.add(getOneVolunteer(rs));
            }
            return allVolunteers;
        }
    }

    /**
     * Method for adding a new volunteer to database table Names.
     *
     * @param v
     * @return
     * @throws SQLException
     */
    public Volunteer add(Volunteer v) throws SQLException {
        String sql = "INSERT INTO Names(name, email, phoneNumber) VALUES(?, ?, ?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, v.getName());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPhoneNumber());

            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new Volunteer(id, v.getName(), v.getEmail(), v.getPhoneNumber());
        }
    }

    /**
     * Deletes a selected volunteer from database table Volunteer.
     *
     * @param v
     * @throws SQLException
     */
    public void delete(Volunteer v) throws SQLException {
        String sql = "DELETE FROM Names where id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getId());

            ps.executeUpdate();
        }
    }

    /**
     * Reflects attributes for a volunteer in database table Volunteer.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Volunteer getOneVolunteer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phoneNumber");

        return new Volunteer(id, name, email, phoneNumber);
    }

    /**
     * Updates database table Volunteer.
     *
     * @param v
     * @throws SQLException
     */
    public void update(Volunteer v) throws SQLException {
        String sql = "UPDATE Names SET name = ?, email = ?, phoneNumber = ? WHERE id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, v.getName());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPhoneNumber());
            ps.setInt(4, v.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Updates guildsId on a specific volunteer in the database.
     *
     * @param v
     * @throws SQLException
     */
    public void updateGuildByGuildsId(Volunteer v) throws SQLException {
        String sql = "UPDATE Works_For SET guildsId = ? FROM Works_For INNER JOIN Names on Works_For.nameId = Names.id WHERE id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getGuildsId());
            ps.setInt(2, v.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Populates a new ArrayList of Volunteers with volunteers for a specific
     * guild gathered from database table Guilds.
     *
     * @param guildsId
     * @return
     * @throws SQLException
     */
    public List<Volunteer> getByGuildId(int guildsId) throws SQLException {

        List<Volunteer> allVolunteers = new ArrayList<>();
        String sql = "SELECT * FROM Works_For INNER JOIN Names ON Works_For.nameId = Names.id WHERE Works_For.guildsId =  ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allVolunteers.add(getOneVolunteer(rs));
            }
            return allVolunteers;
        }
    }
}
