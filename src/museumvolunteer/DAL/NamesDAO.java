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
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT * FROM Names";
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
        
        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Names(name, email, phoneNumber) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, v.getNameAsString());
            ps.setString(2, v.getEmailAsString());
            ps.setString(3, v.getPhoneNumberAsString());

            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);

            String sql2 = "INSERT INTO Works_For(nameId, guildsId) VALUES(?, ?)";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, id);
            ps2.setInt(2, v.getGuildsIdValue());

            ps2.executeUpdate();
            return new Volunteer(id, v.getNameAsString(), v.getEmailAsString(), v.getPhoneNumberAsString(), v.getGuildsIdValue());
        }
    }

//    /**
//     * Deletes a selected volunteer from database table Volunteer.
//     *
//     * @param v
//     * @throws SQLException
//     */
//    public void deleteVolunteer(Volunteer v) throws SQLException {       
//        try (Connection con = cm.getConnection()) {
//            String sql = "DELETE FROM Names WHERE id = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, v.getIdValue());
//            ps.executeUpdate();
//        } catch (SQLException sqlEx) {
//            System.out.println("Du har ikke valgt en frivillig at slette. " + sqlEx);
//        }
//    }

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
        
        try (Connection con = cm.getConnection()) {
            String sql = "UPDATE Names SET name = ?, email = ?, phoneNumber = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, v.getNameAsString());
            ps.setString(2, v.getEmailAsString());
            ps.setString(3, v.getPhoneNumberAsString());
            ps.setInt(4, v.getIdValue());
            ps.executeUpdate();

//        String sql2 = "UPDATE Works_For SET guildsId = ? FROM Works_For INNER JOIN Names on Works_For.nameId = Names.id WHERE id = ?";
//            PreparedStatement ps2 = con.prepareStatement(sql2);
//            ps2.setInt(1, v.getGuildsId());
//            ps2.setInt(2, v.getId());
//            ps2.executeUpdate();
        }
    }

    /**
     * Updates guildsId on a specific volunteer in the database.
     *
     * @param v
     * @throws SQLException
     */
    public void updateGuildByGuildsId(Volunteer v) throws SQLException {
        
        try (Connection con = cm.getConnection()) {
            String sql = "UPDATE Works_For SET guildsId = ? FROM Works_For INNER JOIN Names on Works_For.nameId = Names.id WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getGuildsIdValue());
            ps.setInt(2, v.getIdValue());
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
        String sql = "SELECT DISTINCT * FROM Works_For INNER JOIN Names ON Works_For.nameId = Names.id WHERE Works_For.guildsId =  ? ORDER BY name ASC";
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

    public void addToNewGuild(int nameId, int guildsId) throws SQLException {

        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Works_For (nameId, guildsId) VALUES(?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
            ps.setInt(2, guildsId);

            ps.executeUpdate();
        }
    }
    
    public void deleteVolunteerByNameIdGuildsId(int nameId, int guildsId) throws SQLException {

        try (Connection con = cm.getConnection()) {
            String sql = "DELETE FROM Works_For WHERE nameId = ? AND guildsId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
            ps.setInt(2, guildsId);

            ps.executeUpdate();
        }
    }
}
