/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Guild;
import museumvolunteer.GUI.Model.GuildsModel;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class AddGuildController implements Initializable {

    @FXML
    private TextField guildNameBox;
    @FXML
    private AnchorPane addGuildScreen;
    
    private GuildsModel guildModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public AddGuildController() throws IOException, SQLException {
        guildModel = GuildsModel.getInstance();
    }

    @FXML
    private void returnButton(ActionEvent event) {
        Stage stage = (Stage) addGuildScreen.getScene().getWindow();
        stage.close();
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

            Stage stage = (Stage) addGuildScreen.getScene().getWindow();
            stage.close();
        }
    }
    
}
