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

    //private variable for connectionManager.
    private final ConnectionManager cm;

    /**
     * Creates a new connectionManager.
     *
     * @throws IOException
     */
    public GuildsDAO() throws IOException {
        cm = new ConnectionManager();
    }

//    /**
//     * Adds a guild object to database table Guilds containing name and nameId.
//     * @param g
//     * @return
//     * @throws SQLException 
//     */
//    public Guild add(Guild g) throws SQLException
//    {
//        String sql = "INSERT INTO Guilds(name, nameId) VALUES(?, ?)";
//        try (Connection con = cm.getConnection())
//        {
//            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setString(1, g.getName());
//            ps.setInt(2, g.getNameId());
//
//            ps.executeUpdate();
//            ResultSet generatedKey = ps.getGeneratedKeys();
//            generatedKey.next();
//            int id = generatedKey.getInt(1);
//            return new Guild(id, g);   
//        }
//    }
    /**
     * Updates name of the specified guild in database table Guilds.
     *
     * @param g
     * @throws SQLException
     */
    public void update(Guild g) throws SQLException {
        String sql = "UPDATE Guilds SET name = ? WHERE id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, g.getName());
            ps.setInt(2, g.getId());

            ps.executeUpdate();
        }
    }
    
     public Guild add(Guild g) throws SQLException {
        String sql = "INSERT INTO Guilds(name) VALUES(?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, g.getName());
            
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new Guild(id, g.getName());
            
//        String sql2 = "INSERT INTO Works_For(nameId, guildsId) VALUES(?, ?)";
//            PreparedStatement ps2 = con.prepareStatement(sql2);
//            ps2.setInt(1, g.getNamesId());
//            ps2.setInt(2, id);
//
//            ps2.executeUpdate();
//            return new Guild(id, g.getName(), g.getNamesId());
        }
     }

    /**
     * Deletes a guild from database table Guilds.
     *
     * @param g
     * @throws SQLException
     */
    public void delete(Guild g) throws SQLException {
        String sql = "DELETE FROM Guilds where id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, g.getId());

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
    public Guild getOneGuild(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");

        return new Guild(id, name);
    }

    /**
     * Method for returning all guilds on a guild.
     *
     * @return
     * @throws SQLException
     */
    public List<Guild> getAllGuilds() throws SQLException {
        List<Guild> allGuilds = new ArrayList<>();

        String sql = "SELECT name FROM Guilds";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allGuilds.add(getOneGuild(rs));
            }
            return allGuilds;
        }
    }

//    /**
//     * Method for deleting a selected guild for a specific volunteer.
//     * @param id
//     * @throws SQLException 
//     */
//    public void deleteByNameId(int id) throws SQLException
//    {
//        String sql = "DELETE FROM Guilds where nameId = ?";
//        try (Connection con = cm.getConnection())
//        {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, id);
//
//            ps.executeUpdate();
//        }
//    }
    /**
     * Populates a new ArrayList of Volunteers with volunteers for a specific
     * guild gathered from database table Guilds.
     *
     * @param nameId
     * @return
     * @throws SQLException
     */
    public List<Guild> getByGuildId(int nameId) throws SQLException {

        List<Guild> allGuilds = new ArrayList<>();
        String sql = "SELECT * FROM Works_For INNER JOIN Guilds ON Works_For.guildsId = Guilds.id WHERE Works_For.nameId =  ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allGuilds.add(getOneGuild(rs));
            }
            return allGuilds;
        }
    }
}
