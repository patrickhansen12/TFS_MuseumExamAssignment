package museumvolunteer.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.Admin;
import museumvolunteer.BE.Manager;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdminDAO {
    private final ConnectionManager cm;

    public AdminDAO() throws IOException {
        cm = new ConnectionManager();
    }
    
    /**
     * Method for selecting all managers in database table Managers.
     *
     * @return
     * @throws SQLException
     */
    public List<Manager> getAllManagers() throws SQLException {
        List<Manager> allManagers = new ArrayList<>();

        String sql = "SELECT * FROM Managers";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allManagers.add(getOneManager(rs));
            }
            return allManagers;
        }
    }

    /**
     * Reflects attributes for a manager in database table Managers.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private Manager getOneManager(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phoneNumber");
        String username = rs.getString("username");
        String password = rs.getString("password");
        return new Manager(id, name, email, phoneNumber, username, password);
    }
    
    /**
     * Method for adding a new manager to database table Managers.
     *
     * @param m
     * @return
     * @throws SQLException
     */
    public Manager addManager(Manager m) throws SQLException {
        String sql = "INSERT INTO Managers(name, email, phoneNumber, username, password) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNameAsString());
            ps.setString(2, m.getEmailAsString());
            ps.setString(3, m.getPhoneNumberAsString());
            ps.setString(4, m.getUsername());
            ps.setString(5, m.getPassword());
            
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new Manager(id, m.getNameAsString(), m.getEmailAsString(), m.getPhoneNumberAsString(), m.getUsername(), m.getPassword());    
        }
    }
    
    /**
     * Deletes a selected manager from database table Managers.
     *
     * @param m
     * @throws SQLException
     */
    public void deleteManager(Manager m) throws SQLException {
        String sql = "DELETE FROM Managers where id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, m.getIdValue());

            ps.executeUpdate();
        }
    }
    
    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> allAdmins = new ArrayList<>();

        String sql = "SELECT * FROM Admin";
        try (Connection con = cm.getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allAdmins.add(getOneAdmin(rs));
            }
            return allAdmins;
        }
    }
        
    private Admin getOneAdmin(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phoneNumber");
        String username = rs.getString("username");
        String password = rs.getString("password");

        return new Admin(id, name, email, phoneNumber, username, password);
    }
    
    /**
     * Updates database table Managers.
     *
     * @param m
     * @throws SQLException
     */
    public void updateManager(Manager m) throws SQLException {
        try (Connection con = cm.getConnection()) {
            String sql = "UPDATE Managers SET name = ?, email = ?, phoneNumber = ?, username = ?, password = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, m.getNameAsString());
            ps.setString(2, m.getEmailAsString());
            ps.setString(3, m.getPhoneNumberAsString());
            ps.setString(4, m.getUsername());
            ps.setString(5, m.getPassword());
            ps.setInt(6, m.getIdValue());
            
            ps.executeUpdate();
        }
    }  
}
