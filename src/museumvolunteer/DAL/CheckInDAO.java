package museumvolunteer.DAL;

import java.io.FileOutputStream;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class CheckInDAO {

    //private variable for connectionManager.
    private final ConnectionManager cm;

    /**
     * Creates a new connectionManager.
     *
     * @throws IOException
     */
    public CheckInDAO() throws IOException {
        cm = new ConnectionManager();
    }

    /**
     * Adds hours to a specific volunteer in database table Hours.
     *
     * @param ci
     * @return
     * @throws SQLException
     */
    public CheckIn addCheckIn(CheckIn ci) throws SQLException {
        try (Connection con = cm.getConnection()) {
            String sql = "INSERT INTO Hours(timeStamp, guildsId, nameId, hours) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setTimestamp(1, ci.getDateTime());
            ps.setInt(2, ci.getGuildsIdValue());
            ps.setInt(3, ci.getNameIdValue());
            ps.setInt(4, ci.getHoursValue());
            ps.executeUpdate();
        } catch (SQLException sqlEx) {
            System.out.println("Du har ikke valgt et laug, før du valgte en frivillig " + sqlEx);
        }
        return new CheckIn(ci);
    }

    /**
     * Deletes hours from database table Hours according to specified id.
     *
     * @param ts
     * @throws SQLException
     */
    public void delete(CheckIn ts) throws SQLException {
        try (Connection con = cm.getConnection()) {
            String sql = "DELETE FROM Hours where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ts.getIdValue());
            ps.executeUpdate();
        } catch (SQLException sqlEx) {
            System.out.println("Du har ikke valgt et tidsstempel. " + sqlEx);
        }
    }

    /**
     * Gets a full list of hours from all volunteers.
     *
     * @return @throws SQLException
     */
    public List<CheckIn> getAll() throws SQLException {
        try (Connection con = cm.getConnection()) {
            List<CheckIn> allTimeStamps = new ArrayList<>();
            String sql = "SELECT * FROM Hours";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allTimeStamps.add(getOneCheckIn(rs));
            }
            return allTimeStamps;
        }
    }

    /**
     * Gets checkIns according to specified id.
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public CheckIn getById(int id) throws SQLException {
        try (Connection con = cm.getConnection()) {
            String sql = "SELECT * FROM CheckIn WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return getOneCheckIn(rs);
            } else {
                return null;
            }
        }
    }

    /**
     * Gets hours matching nameId on the volunteer selected.
     *
     * @param guildsId
     * @param nameId
     * @return
     * @throws SQLException
     */
    public List<CheckIn> getByNameIdGuildsId(int guildsId, int nameId) throws SQLException {
        try (Connection con = cm.getConnection()) {
            List<CheckIn> allTimeStamps = new ArrayList<>();
            String sql = "SELECT * FROM Hours WHERE guildsId = ? AND nameId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ps.setInt(2, nameId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                allTimeStamps.add(getOneCheckIn(rs));
            }
            return allTimeStamps;
        }
    }

    /**
     * Reflection of the database table Hours.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public CheckIn getOneCheckIn(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        Timestamp dateTime = rs.getTimestamp("timeStamp");
        int guildsId = rs.getInt("guildsId");
        int nameId = rs.getInt("nameId");
        int hours = rs.getInt("hours");
        
        return new CheckIn(id, dateTime, guildsId, nameId, hours);
    }

    /**
     * Gets all available hours from database table Hours.
     *
     * @return @throws SQLException
     */
    public List<CheckIn> getAllCheckIns() throws SQLException {
        try (Connection con = cm.getConnection()) {
            List<CheckIn> allCheckIns = new ArrayList<>();
            String sql = "SELECT timeStamp FROM Hours";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                allCheckIns.add(getOneCheckIn(rs));
            }
            return allCheckIns;
        }
    }

    /**
     * Deletes hours according to id specified.
     *
     * @param guildsId
     * @param nameId
     * @throws SQLException
     */
    public void deleteByGuildsIdNameId(int guildsId, int nameId) throws SQLException {
        try (Connection con = cm.getConnection()) {
            String sql = "DELETE FROM Hours WHERE guildsId = ? AND nameId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ps.setInt(2, nameId);
            ps.executeUpdate();
        } catch (SQLException sqlEx) {
            System.out.println("Du har ikke valgt et laug og en frivillig. " + sqlEx);
        }
    }

    public List<CheckIn> getByNameIdGuildsIdToExcel(int guildsId, int nameId) throws SQLException, IOException {
        List<CheckIn> allTimeStamps = new ArrayList<>();
        String sql = "SELECT * FROM Hours WHERE guildsId = ? AND nameId = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ps.setInt(2, nameId);
            ResultSet rs = ps.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Timer for frivillig");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Tidsstempel");
            header.createCell(1).setCellValue("Laug id");
            header.createCell(2).setCellValue("Id på frivillig");
            header.createCell(3).setCellValue("Antal timer");

            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("timeStamp"));
                row.createCell(1).setCellValue(rs.getString("guildsId"));
                row.createCell(2).setCellValue(rs.getString("nameId"));
                row.createCell(3).setCellValue(rs.getString("hours"));
                index++;
                allTimeStamps.add(getOneCheckIn(rs));
            }
            FileOutputStream fileOut = new FileOutputStream("DataOmFrivillig.xlsx");
            wb.write(fileOut);
            ps.close();
            rs.close();

            return allTimeStamps;
        }
    }
    
    public int getByGuildsIdSumOfHours(int guildsId) throws SQLException, IOException {
        String sql = "SELECT * FROM Hours WHERE guildsId = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ResultSet rs = ps.executeQuery();
            int sum = 0;
            while (rs.next()) 
            {
                 CheckIn ci = getOneCheckIn(rs);
                  sum += ci.getHoursValue();
            }
            return sum;
        }
    }
    
    public List<CheckIn> getByGuildsIdToExcel(int guildsId) throws SQLException, IOException {
        List<CheckIn> allTimeStamps = new ArrayList<>();
        String sql = "SELECT * FROM Hours WHERE guildsId = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, guildsId);
            ResultSet rs = ps.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Timer for laug");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Tidsstempel");
            header.createCell(1).setCellValue("Laug id");
            header.createCell(2).setCellValue("Antal timer");

            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("timeStamp"));
                row.createCell(1).setCellValue(rs.getString("guildsId"));
                row.createCell(2).setCellValue(rs.getString("hours"));
                index++;
                allTimeStamps.add(getOneCheckIn(rs));
            }
            FileOutputStream fileOut = new FileOutputStream("DataOmLaug.xlsx");
            wb.write(fileOut);
            ps.close();
            rs.close();

            return allTimeStamps;
        }
    }
}
