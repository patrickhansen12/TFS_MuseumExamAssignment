/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class VolunteerViewController implements Initializable {

    @FXML
    private TableView<Guild> LaugTable;
    @FXML
    private TableColumn<Guild, String> LaugColumn;
    @FXML
    private TableView<Volunteer> NavneTable;
    @FXML
    private TableColumn<Volunteer, String> NavneColumn;
    @FXML
    private TextField SøgNavnField;
    @FXML
    private TextField NoterTimerField;
    @FXML
    private Label ConfirmationLabel;
    
    private VolunteerModel volunteerModel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataBind();
    }    

    @FXML
    private void IndsætTimer(ActionEvent event) {
    }
    
    private void dataBind()
    {
       NavneColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
       NavneTable.setItems(volunteerModel.getAllVolunteers());
    }
    
    public void setModel(VolunteerModel volunteerModel) 
    {
        this.volunteerModel = volunteerModel;
    }
    
}
