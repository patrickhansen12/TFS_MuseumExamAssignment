package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Guild;
import museumvolunteer.GUI.Model.GuildsModel;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AddGuildController implements Initializable {

    @FXML
    private TextField guildNameBox;
    @FXML
    private AnchorPane addGuildScreen;

    private GuildsModel guildModel;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public AddGuildController() throws IOException, SQLException {
        guildModel = new GuildsModel();
    }

    /**
     * returns the user to the administratorView.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void returnButton(ActionEvent event) throws IOException, SQLException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) addGuildScreen.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void performButton(ActionEvent event) throws SQLException, IOException {
        if (guildNameBox.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal skrive laugets navn før du kan oprette det.");
            alert.showAndWait();
        } else {
            String name = guildNameBox.getText().trim();
            guildModel.addGuild(new Guild(name));
            guildNameBox.clear();
            guildNameBox.requestFocus();

            returnButton(event);
        }
    }
}
