/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class AdministatorViewController implements Initializable {


    @FXML
    private AnchorPane adminScreen;
    @FXML
    private TableView<?> guildAdminTable;
    @FXML
    private TableColumn<?, ?> guildAdminColumn;
    @FXML
    private TableView<?> nameAdminTable;
    @FXML
    private TableColumn<?, ?> nameAdminColumn;
    @FXML
    private TableView<?> hoursAdminTable;
    @FXML
    private TableColumn<?, ?> hoursAdminColumn;
    @FXML
    private TableView<?> managerAdminTable;
    @FXML
    private TableColumn<?, ?> managerAdminColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void returnAdmin(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        
        stage = (Stage) adminScreen.getScene().getWindow();
        stage.close();
    } 

    @FXML
    private void addGuildButton(ActionEvent event) {
    }

    @FXML
    private void addVolunteerButton(ActionEvent event) {
    }

    @FXML
    private void removeGuildButton(ActionEvent event) {
    }

    @FXML
    private void removeVolunteersButton(ActionEvent event) {
    }

    @FXML
    private void addManagerButton(ActionEvent event) {
    }

    @FXML
    private void removeManagerButton(ActionEvent event) {
    }

    @FXML
    private void removeHoursButton(ActionEvent event) {
    }

    @FXML
    private void addHoursButton(ActionEvent event) {
    }
}
