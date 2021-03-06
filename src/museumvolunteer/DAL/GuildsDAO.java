package museumvolunteer.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.Guild;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class GuildsDAO {

    //instance variable for connectionManager.
    private final ConnectionManager cm;

    /**
     * Creates a new connectionManager.
     *
     * @throws IOException
     */
    public GuildsDAO() throws IOException {
        cm = new ConnectionManager();
    }

    /**
     * Updates name of the specified guild in database table Guilds.
     *
     * @param g
     * @throws SQLException
     */
    public void updateGuild(Guild g) throws SQLException {
        String sql = "UPDATE Guilds SET name = ? WHERE id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, g.getNameAsString());
            ps.setInt(2, g.getIdValue());

            ps.executeUpdate();
        }
    }

    /**
     * Inserts the chosen name of a guild into database table Guilds.
     * @param g
     * @return
     * @throws SQLException 
     */
    public Guild addGuild(Guild g) throws SQLException {
        String sql = "INSERT INTO Guilds(name) VALUES(?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, g.getNameAsString());

            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new Guild(id, g.getNameAsString());
        }
    }

    /**
     * Deletes a guild from database table Guilds.
     *
     * @param g
     * @throws SQLException
     */
    public void deleteGuild(Guild g) throws SQLException {
        String sql = "DELETE FROM Guilds where id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, g.getIdValue());

            ps.executeUpdate();
        }
    }

    /**
     * Populates an ArrayList of BE class Guild with guilds.
     *
     * @return
     * @throws SQLException
     */
    public List<Guild> getAll() throws SQLException {
        List<Guild> allGuilds = new ArrayList<>();

        String sql = "SELECT * FROM Guilds";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allGuilds.add(getOneGuild(rs));
            }
            return allGuilds;
        }
    }

    /**
     * Method for selecting a specific guild by id. The data is gathered from
     * database table Guilds.
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Guild getById(int id) throws SQLException {
        String sql = "SELECT * FROM Guilds WHERE id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getOneGuild(rs);
            } else {
                return null;
            }
        }
    }

    /**
     * Reflects the attributes for 1 guild in the database.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Guild getOneGuild(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Guild(id, name);
    }

    /**
     * Method for returning all names from a chosen guild.
     *
     * @return
     * @throws SQLException
     */
    public List<Guild> getAllGuilds() throws SQLException {
        List<Guild> allGuilds = new ArrayList<>();

        String sql = "SELECT name FROM Guilds ORDER BY name ASC";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allGuilds.add(getOneGuild(rs));
            }
            return allGuilds;
        }
    }

    /**
     * Populates a new ArrayList of Volunteers with volunteers for a specific
     * guild gathered from database table Guilds.
     *
     * @param nameId
     * @return
     * @throws SQLException
     */
    public List<Guild> getGuildsByNameId(int nameId) throws SQLException {
        try (Connection con = cm.getConnection()) {
            List<Guild> allGuildsByNameId = new ArrayList<>();
            String sql = "SELECT * FROM Works_For INNER JOIN Guilds ON Works_For.guildsId = Guilds.id WHERE Works_For.nameId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allGuildsByNameId.add(getOneGuild(rs));
            }
            return allGuildsByNameId;
        }
    }
}
