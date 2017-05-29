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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Manager;
import museumvolunteer.GUI.Model.AdminModel;

/**
 * FXML Controller class
 *
 * @author Casper
 */
public class ManagerInfoViewController implements Initializable {

    @FXML
    private AnchorPane managerInfoScreen;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField emailBox;
    @FXML
    private TextField phoneNumberBox;
    @FXML
    private TextField usernameBox;
    @FXML
    private TextField passwordBox;
    
    private Manager thisManager;
    private AdminModel adminModel;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public ManagerInfoViewController() throws IOException, SQLException {
        adminModel = new AdminModel();
    }

    @FXML
    private void handleUpdate(ActionEvent event) throws SQLException, IOException {
        String name = nameBox.getText().trim();
        String email = emailBox.getText().trim();
        String phoneNumber = phoneNumberBox.getText().trim();
        String username = usernameBox.getText().trim();
        String password = passwordBox.getText().trim();
        adminModel.updateManager(new Manager(thisManager.getIdValue(), name, email, phoneNumber, username, password));
        returnButton(event);
    }

    @FXML
    public void returnButton(ActionEvent event) throws IOException, SQLException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AdministratorView.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Stage stage2 = (Stage) managerInfoScreen.getScene().getWindow();
        stage2.close();
    }
    
    /**
     * gets a clicked manager from ManagerViewController and sets name, email,
     * phoneNumber, username and password.
     *
     * @param m
     * @throws java.sql.SQLException
     */
    public void getManagerData(Manager m) throws SQLException {
        thisManager = m;
        nameBox.setText(m.getNameAsString());
        emailBox.setText(m.getEmailAsString());
        phoneNumberBox.setText(m.getPhoneNumberAsString());
        usernameBox.setText(m.getUsername());
        passwordBox.setText(m.getPassword());
    }   
}
