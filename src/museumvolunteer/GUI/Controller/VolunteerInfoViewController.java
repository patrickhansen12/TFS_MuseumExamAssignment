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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

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
    @FXML
    private Button confirmButton;
    @FXML
    private Label guildLbl;
    @FXML
    private TextField guildBox;
    
    private VolunteerModel volunteerModel;
    private Volunteer thisVolunteer;
    private Guild thisGuild;
    private GuildsModel guildsModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public VolunteerInfoViewController() throws IOException, SQLException 
    {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
    }

    public void doMagicStuff(Volunteer v) {
        thisVolunteer = v;
        nameBox.setText(v.getName());
        emailBox.setText(v.getEmail());
        phoneNumberBox.setText(v.getPhoneNumber());
    }

    @FXML
    private void handleUpdate(ActionEvent event) throws SQLException {
        String name = nameBox.getText().trim();
        String email = emailBox.getText().trim();
        String phoneNumber = phoneNumberBox.getText().trim();
        String guild = guildBox.getText().trim();
        volunteerModel.updateVolunteer(new Volunteer(thisVolunteer.getId(), name, email, phoneNumber));
        guildsModel.updateGuild(new Guild(thisGuild.getId(), guild));
    }

    public void getGuild(Guild g) {
        thisGuild = g;
        guildBox.setText(g.getName());
    }
    
}
