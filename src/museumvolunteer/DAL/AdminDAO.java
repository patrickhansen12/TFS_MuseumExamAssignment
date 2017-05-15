/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.DAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.Manager;

/**
 *
 * @author Casper
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

        return new Manager(id, name, email, phoneNumber);
    }
    
    /**
     * Method for adding a new manager to database table Managers.
     *
     * @param m
     * @return
     * @throws SQLException
     */
    public Manager addManager(Manager m) throws SQLException {
        String sql = "INSERT INTO Managers(name, email, phoneNumber) VALUES(?, ?, ?)";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getPhoneNumber());
            
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new Manager(id, m.getName(), m.getEmail(), m.getPhoneNumber());    
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
            ps.setInt(1, m.getId());

            ps.executeUpdate();
        }
    }
    
    
    
}
