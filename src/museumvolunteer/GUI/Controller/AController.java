package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public abstract class AController {

    /**
     * Method for selecting all volunteers from a clicked guild.
     * @param event
     * @throws SQLException
     * @throws java.io.IOException
     */
    @FXML
    public abstract void handleGuildsVolunteers(MouseEvent event) throws SQLException, IOException;
    
    /**
     * Method for returning the user to the previous view.
     * @param event
     * @throws IOException
     * @throws java.sql.SQLException
     */
    @FXML
    public abstract void returnButton(ActionEvent event) throws IOException, SQLException;
    
    /**
     * Method for searching through the list of volunteers to find a specific volunteer.
     * @param event
     * @throws SQLException
     */
    @FXML
    public abstract void searchNameList(KeyEvent event) throws SQLException;
}
