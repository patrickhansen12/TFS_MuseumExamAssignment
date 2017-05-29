package museumvolunteer.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class ConnectionManager {

    //private variables.
    private static final String CONFIG_FILE_NAME = "VikingeMuseum.cfg";
    private final SQLServerDataSource ds;

    /**
     * Establishes a connection to the database.
     *
     * @throws IOException
     */
    public ConnectionManager() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader(CONFIG_FILE_NAME));

        ds = new SQLServerDataSource();
        ds.setServerName(props.getProperty("SERVER"));
        ds.setDatabaseName(props.getProperty("DATABASE"));
        ds.setPortNumber(Integer.parseInt(props.getProperty("PORT")));
        ds.setUser(props.getProperty("USER"));
        ds.setPassword(props.getProperty("PASSWORD"));
    }

    /**
     * Method to get connection to the database.
     *
     * @return
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
