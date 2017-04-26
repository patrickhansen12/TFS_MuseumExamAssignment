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

import museumvolunteer.BE.Calendar;
import museumvolunteer.BE.CheckIn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO StudentCheckIn(studentCheckIn, studentId, isAttendance) VALUES(?, ?, ?)";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, ts.getDateTime());
            ps.setInt(2, ts.getStudentId());
            ps.setDouble(3, ts.getHours());

            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            generatedKey.next();
            int id = generatedKey.getInt(1);
            return new CheckIn(id, ts);
            
        }
    }

    public void update(CheckIn ts) throws SQLException
    {
        String sql = "UPDATE CheckIn "
                + "SET dateTime = ?, "
                + "    volunteerId = ?, "
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
        String sql = "DELETE FROM CheckIn where id = ?";
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

        String sql = "SELECT * FROM CheckIn";
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
    
    public List<CheckIn> getByVoulunteerId(int id) throws SQLException{
        
      List<CheckIn> allTimeStamps = new ArrayList<>();
      String sql = "SELECT * FROM CheckIn WHERE Id = ?";
      try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
        Timestamp dateTime = rs.getTimestamp("studentCheckIn");
        int volunteerId = rs.getInt("volunteerId");
        int hours = rs.getInt("Hours");
        
        return new CheckIn(id, dateTime, volunteerId, hours );
    }

    public List<CheckIn> getAllCheckIns() throws SQLException {
        List<CheckIn> allCheckIns = new ArrayList<>();

        String sql = "SELECT studentCheckIn FROM Hours";
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
        String sql = "DELETE FROM Hours where volunteerId = ?";
        try (Connection con = cm.getConnection())
        {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }
}
