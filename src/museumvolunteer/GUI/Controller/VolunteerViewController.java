/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    @FXML
    private AnchorPane VolunteerScreen;

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

    @FXML
    private void TilbageVolunteer(ActionEvent event) throws IOException 
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Frivillig dokumentation");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
        
        stage = (Stage) VolunteerScreen.getScene().getWindow();
        stage.close();
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
