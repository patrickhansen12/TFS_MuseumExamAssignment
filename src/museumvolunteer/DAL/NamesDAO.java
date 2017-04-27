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
import museumvolunteer.BE.Volunteer;

/**
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class NamesDAO
{
    
    private final ConnectionManager cm;

    public NamesDAO() throws IOException {
        cm = new ConnectionManager();
    }

    /**
     * Method for selecting all volunteers in database table Names.
     * @return
     * @throws SQLException 
     */
    public List<Volunteer> getAllVolunteers() throws SQLException {
        List<Volunteer> allVolunteers = new ArrayList<>();

        String sql = "SELECT * FROM Names";
        try (Connection con = cm.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                allVolunteers.add(getOneVolunteer(rs));
              
            }
            return allVolunteers;
        }
    }
    
    /**
     * Method for adding a new volunteer to database table Volunteer.
     * @param v
     * @return
     * @throws SQLException 
     */
    public Volunteer add(Volunteer v) throws SQLException
    {
        String sql = "INSERT INTO Names(name) VALUES(?, ?, ?, ?)";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, v.getName());
            ps.setString(2, v.getEmail());
            ps.setString(3, v.getPhoneNumber());
            ps.setInt(4, v.getGuildsId());

            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new Volunteer(id, v.getName(), v.getEmail(), v.getPhoneNumber(), v.getGuildsId());
        }
    }

    /**
     * Deletes a selected volunteer from database table Volunteer.
     * @param v
     * @throws SQLException 
     */
    public void delete(Volunteer v) throws SQLException
    {
        String sql = "DELETE FROM Names where id = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, v.getId());

            ps.executeUpdate();
        }
    }

    /**
     * Reflects attributes for a volunteer in database table Volunteer.
     * @param rs
     * @return
     * @throws SQLException 
     */
    private Volunteer getOneVolunteer(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phoneNumber");
        int guildsId = rs.getInt("guildsId");
        
        return new Volunteer(id, name, email, phoneNumber, guildsId);
    }
    
    /**
     * Updates database table Volunteer.
     * @param v
     * @throws SQLException 
     */
    public void update(Volunteer v) throws SQLException
    {
        String sql = "UPDATE Names"
                + "SET name = ?, "
                + "WHERE id = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, v.getName());
            ps.setInt(3, v.getId());

            ps.executeUpdate();
        }
    }
    
    /**
     * Populates a new ArrayList of Volunteers with volunteers for a specific guild gathered from database table Guilds.
     * @param id
     * @return
     * @throws SQLException 
     */
    public List<Volunteer> getByGuildId(int guildsId) throws SQLException{
        
      List<Volunteer> allVolunteers = new ArrayList<>();
      String sql = "SELECT * FROM Names WHERE guildsId = ?";
      try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                allVolunteers.add(getOneVolunteer(rs));
            }
            return allVolunteers;
        }
    }
}
