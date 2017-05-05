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
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Manager;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class AdministatorViewController implements Initializable {


    @FXML
    private AnchorPane adminScreen;
    @FXML
    private TableView<Guild> guildAdminTable;
    @FXML
    private TableColumn<Guild, String> guildAdminColumn;
    @FXML
    private TableView<Volunteer> nameAdminTable;
    @FXML
    private TableColumn<Volunteer, String> nameAdminColumn;
    @FXML
    private TableView<?> hoursAdminTable;
    @FXML
    private TableColumn<?, ?> hoursAdminColumn;
    @FXML
    private TableView<Manager> managerAdminTable;
    @FXML
    private TableColumn<Manager, String> managerAdminColumn;
    
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dataBind();
    }    
    
    public AdministatorViewController() throws IOException, SQLException 
    {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
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
    private void addVolunteerButton(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddVolunteer.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Tilføj frivillig");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void removeGuildButton(ActionEvent event) {
    }

    @FXML
    private void removeVolunteersButton(ActionEvent event) throws SQLException 
    {
       Volunteer selectedItem = nameAdminTable.getSelectionModel().getSelectedItem();
       volunteer = selectedItem;
       volunteerModel.deleteVolunteer(volunteer);
       nameAdminTable.getItems().remove(selectedItem);
       nameAdminTable.getSelectionModel().clearSelection();   
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

    private void dataBind()
    {  
       guildAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
       guildAdminTable.setItems(guildsModel.getGuilds());
    }
    
    public void setModel(VolunteerModel volunteerModel) 
    {
        this.volunteerModel = volunteerModel;
    }

    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException 
    {
        if (event.isPrimaryButtonDown() == false) {
            int guildId = guildAdminTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setNamesByGuildId(guildId);
            nameAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
            nameAdminTable.setItems(volunteerModel.getAllVolunteers());
        }
    }
}
