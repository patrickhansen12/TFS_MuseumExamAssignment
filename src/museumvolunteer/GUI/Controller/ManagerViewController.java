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

    @FXML
    private TableView<Guild> LaugManagerTable;
    @FXML
    private TableColumn<Guild, String> LaugManagerColumn;
    @FXML
    private TableView<Volunteer> NavneManagerTable;
    @FXML
    private TableColumn<Volunteer, String> NavneManagerColumn;
    @FXML
    private TableView<?> TimerManagerTable;
    @FXML
    private TableColumn<?, ?> TimerManagerColumn;
    @FXML
    private AnchorPane ManagerScreen;
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
    public ManagerViewController() throws IOException, SQLException 
    {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
    }
    
    @FXML
    private void TilføjFrivilligeButton(ActionEvent event) throws IOException 
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
    private void SletFrivilligeButton(ActionEvent event) throws SQLException 
    {
       Volunteer selectedItem = NavneManagerTable.getSelectionModel().getSelectedItem();
       volunteer = selectedItem;
       volunteerModel.deleteVolunteer(volunteer);
       NavneManagerTable.getItems().remove(selectedItem);
       NavneManagerTable.getSelectionModel().clearSelection();
       
    }

    @FXML
    private void TilføjTimerButton(ActionEvent event) {
    }

    @FXML
    private void SletTimerButton(ActionEvent event) {
    }

    @FXML
    private void TilbageManager(ActionEvent event) throws IOException 
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
     
       LaugManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
       LaugManagerTable.setItems(guildsModel.getGuilds());
    }
  public void setModel(VolunteerModel volunteerModel) 
    {
        this.volunteerModel = volunteerModel;
    }

    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException {
        if (event.isPrimaryButtonDown() == false) {
            int guildId = LaugManagerTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setNamesByGuildId(guildId);
            NavneManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
            NavneManagerTable.setItems(volunteerModel.getAllVolunteers());
        }
    }
}

