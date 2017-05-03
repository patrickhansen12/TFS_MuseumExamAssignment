/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.DAL;

/**
 *
 * @author patrick
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import museumvolunteer.BE.CheckIn;

/**
 * @author Jens, Patrick, Casper, Kasper
 */
public class CheckInDAO
{

    private final ConnectionManager cm;
//    private int studentId;

    public CheckInDAO() throws IOException
    {
        cm = new ConnectionManager();
    }

    public CheckIn add(CheckIn ts) throws SQLException
    {
        String sql = "INSERT INTO Hours(timeStamp, nameId, hours) VALUES(?, ?, ?)";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, ts.getDateTime());
            ps.setInt(2, ts.getNameId());
            ps.setInt(3, ts.getHours());

            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new CheckIn(id, ts);
            
        }
    }

    public void update(CheckIn ts) throws SQLException
    {
        String sql = "UPDATE Hours"
                + "SET timeStamp = ?, "
                + "    nameId = ?, "
                + "WHERE id = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, ts.getDateTime());
            ps.setInt(2, ts.getHours());
            ps.setInt(3, ts.getId());

            ps.executeUpdate();
        }
    }

    public void delete(CheckIn ts) throws SQLException
    {
        String sql = "DELETE FROM Hours where id = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ts.getId());

            ps.executeUpdate();
        }
    }

    public List<CheckIn> getAll() throws SQLException
    {
        List<CheckIn> allTimeStamps = new ArrayList<>();

        String sql = "SELECT * FROM Hours";
        try (Connection con = cm.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                allTimeStamps.add(getOneCheckIn(rs));
            }
            return allTimeStamps;
        }
    }

    public CheckIn getById(int id) throws SQLException
    {
        String sql = "SELECT * FROM CheckIn WHERE id = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return getOneCheckIn(rs);
            }
            else
            {
                return null;
            }
        }
    }
    
    public List<CheckIn> getByNameId(int nameId) throws SQLException{
        
      List<CheckIn> allTimeStamps = new ArrayList<>();
      String sql = "SELECT * FROM Hours WHERE nameId = ?";
      try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                allTimeStamps.add(getOneCheckIn(rs));
            }
            return allTimeStamps;
        }
      
    }
    
    public CheckIn getOneCheckIn(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("id");
        Timestamp dateTime = rs.getTimestamp("timeStamp");
        int nameId = rs.getInt("nameId");
        int hours = rs.getInt("hours");
        
        return new CheckIn(id, dateTime, nameId, hours);
    }

    public List<CheckIn> getAllCheckIns() throws SQLException {
        List<CheckIn> allCheckIns = new ArrayList<>();

        String sql = "SELECT timeStamp FROM Hours";
        try (Connection con = cm.getConnection())
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                allCheckIns.add(getOneCheckIn(rs));
            }
            return allCheckIns;
        }
    }

//    public int getStudentId()
//    {
//        return studentId;
//    }

    public void deleteById(int id) throws SQLException
    {
        String sql = "DELETE FROM Hours WHERE nameId = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
