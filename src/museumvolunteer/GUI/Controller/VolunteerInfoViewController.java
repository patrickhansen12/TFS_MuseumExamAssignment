/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import museumvolunteer.BE.Volunteer;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class VolunteerInfoViewController implements Initializable {

    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneNumberBox;
    @FXML
    private Label nameLbl;
    @FXML
    private Label emailLbl;
    @FXML
    private Label phoneNumberLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void doMagicStuff(Volunteer v) {
        nameBox.setText(v.getName());
        emailBox.setText(v.getEmail());
        phoneNumberBox.setText(v.getPhoneNumber());
    }
    
}
