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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class AddVolunteerController implements Initializable {

    @FXML
    private AnchorPane addScreen;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField guildBox;
    @FXML
    private TextArea noteBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void performButton(ActionEvent event) throws SQLException, IOException
    {
        String name = nameBox.getText().trim();
        String email = emailBox.getText().trim();
        String phoneNumber = phoneBox.getText().trim();
        int guildsId = Integer.parseInt(guildBox.getText().trim());
        VolunteerModel.getInstance().addVolunteer(new Volunteer(name, email, phoneNumber, guildsId));
        nameBox.clear();
        nameBox.requestFocus();
        
        Stage stage = (Stage) addScreen.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void returnButton(ActionEvent event) 
    {
        Stage stage = (Stage) addScreen.getScene().getWindow();
        stage.close();
    }
}
