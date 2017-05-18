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
     * @param ts
     * @return
     * @throws SQLException
     */
    public CheckIn add(CheckIn ts) throws SQLException {
        String sql = "INSERT INTO Hours(timeStamp, nameId, hours) VALUES(?, ?, ?)";
        try (Connection con = cm.getConnection()) {
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

//    /**
//     * Updates 
//     * @param ts
//     * @throws SQLException
//     */
//    public void update(CheckIn ts) throws SQLException {
//        String sql = "UPDATE Hours"
//                + "SET timeStamp = ?, "
//                + "    nameId = ?, "
//                + "WHERE id = ?";
//        try (Connection con = cm.getConnection()) {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setTimestamp(1, ts.getDateTime());
//            ps.setInt(2, ts.getHours());
//            ps.setInt(3, ts.getId());
//
//            ps.executeUpdate();
//        }
//    }
    /**
     * Deletes hours from database table Hours according to specified id.
     *
     * @param ts
     * @throws SQLException
     */
    public void delete(CheckIn ts) throws SQLException {
        String sql = "DELETE FROM Hours where id = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ts.getId());

            ps.executeUpdate();
        }
    }

    /**
     * Gets a full list of hours from all volunteers.
     *
     * @return @throws SQLException
     */
    public List<CheckIn> getAll() throws SQLException {
        List<CheckIn> allTimeStamps = new ArrayList<>();

        String sql = "SELECT * FROM Hours";
        try (Connection con = cm.getConnection()) {
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
        String sql = "SELECT * FROM CheckIn WHERE id = ?";
        try (Connection con = cm.getConnection()) {
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
     * @param nameId
     * @return
     * @throws SQLException
     */
    public List<CheckIn> getByNameId(int nameId) throws SQLException {

        List<CheckIn> allTimeStamps = new ArrayList<>();
        String sql = "SELECT * FROM Hours WHERE nameId = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
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
        int nameId = rs.getInt("nameId");
        int hours = rs.getInt("hours");

        return new CheckIn(id, dateTime, nameId, hours);
    }

    /**
     * Gets all available hours from database table Hours.
     *
     * @return @throws SQLException
     */
    public List<CheckIn> getAllCheckIns() throws SQLException {
        List<CheckIn> allCheckIns = new ArrayList<>();

        String sql = "SELECT timeStamp FROM Hours";
        try (Connection con = cm.getConnection()) {
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
     * @param id
     * @throws SQLException
     */
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Hours WHERE nameId = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    public List<CheckIn> getByNameIdToExcel(int nameId) throws SQLException, IOException {
        List<CheckIn> allTimeStamps = new ArrayList<>();
        String sql = "SELECT * FROM Hours WHERE nameId = ?";
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, nameId);
            ResultSet rs = ps.executeQuery();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Timer for frivillig");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Tidsstempel");
            header.createCell(1).setCellValue("Id på frivillig");
            header.createCell(2).setCellValue("Antal timer");

            int index = 1;
            while (rs.next()) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("timeStamp"));
                row.createCell(1).setCellValue(rs.getString("nameId"));
                row.createCell(2).setCellValue(rs.getString("hours"));
                index++;
                allTimeStamps.add(getOneCheckIn(rs));
            }
            FileOutputStream fileOut = new FileOutputStream("UserDetails.xlsx");
            wb.write(fileOut);
            ps.close();
            rs.close();

            return allTimeStamps;
        }
    }       
}
        

