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
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Nicolai
 */
public class ManagerViewController implements Initializable {

    private TableView<Guild> guildManagerTable;
    private TableColumn<Guild, String> guildManagerColumn;
    private TableView<Volunteer> nameManagerTable;
    private TableColumn<Volunteer, String> nameManagerColumn;
    @FXML
    private AnchorPane ManagerScreen;
       private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;

    @FXML
    private TableView<?> hoursManagerTable;
    @FXML
    private TableColumn<?, ?> hoursManagerColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dataBind();
    }    
    public ManagerViewController() throws IOException, SQLException 
    {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
    }
    
    private void addVolunteersButton (ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddVolunteer.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Tilføj frivillig");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    private void deleteVolunteersButton(ActionEvent event) throws SQLException 
    {
       Volunteer selectedItem = nameManagerTable.getSelectionModel().getSelectedItem();
       volunteer = selectedItem;
       volunteerModel.deleteVolunteer(volunteer);
       nameManagerTable.getItems().remove(selectedItem);
       nameManagerTable.getSelectionModel().clearSelection();
       
    }


    private void returnManager(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        
        stage = (Stage) ManagerScreen.getScene().getWindow();
        stage.close();
    }
        private void dataBind()
    {
     
       guildManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
       guildManagerTable.setItems(guildsModel.getGuilds());
    }
  public void setModel(VolunteerModel volunteerModel) 
    {
        this.volunteerModel = volunteerModel;
    }

    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException {
        if (event.isPrimaryButtonDown() == false) {
            int guildId = guildManagerTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setNamesByGuildId(guildId);
            nameManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
            nameManagerTable.setItems(volunteerModel.getAllVolunteers());
        }
    }

 

    @FXML
    private void addHoursButton(ActionEvent event) {
    }

    @FXML
    private void deleteHoursButton(ActionEvent event) {
    }

  
}

