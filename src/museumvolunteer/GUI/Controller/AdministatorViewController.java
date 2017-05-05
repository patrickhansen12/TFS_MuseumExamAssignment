/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.CheckIn;
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
    private TableView<CheckIn> hoursAdminTable;
    @FXML
    private TableColumn<CheckIn, Integer> hoursAdminColumn;
    @FXML
    private TableView<Manager> managerAdminTable;
    @FXML
    private TableColumn<Manager, String> managerAdminColumn;
    
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;
    private CheckIn checkIn;
    @FXML
    private TableColumn<CheckIn, Timestamp> dateAdminColumn;
    @FXML
    private TextField txtFieldHours;
    @FXML
    private DatePicker datePicker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dataBind();
        datePicker.setValue(LocalDate.now());
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
       Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Er du sikker på du vil slette den frivillige");
        alert.setHeaderText(null);
        alert.setContentText("Vil du slette den frivillige fra dette laug eller alle laug?");

        ButtonType buttonTypeThis = new ButtonType("Dette laug");
        ButtonType buttonTypeAll = new ButtonType("Alle laug");
        ButtonType buttonTypeCancel = new ButtonType("Fortryd", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeThis, buttonTypeAll, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeThis)
        {
            Volunteer selectedItem = nameAdminTable.getSelectionModel().getSelectedItem();
            volunteer = selectedItem;
            volunteerModel.deleteVolunteer(volunteer);
            nameAdminTable.getItems().remove(selectedItem);
            nameAdminTable.getSelectionModel().clearSelection();  
        } 
        else if (result.get() == buttonTypeAll) 
        {
            Volunteer selectedItem = nameAdminTable.getSelectionModel().getSelectedItem();
            volunteer = selectedItem;
            volunteerModel.deleteVolunteer(volunteer);
            nameAdminTable.getItems().remove(selectedItem);
            nameAdminTable.getSelectionModel().clearSelection();  
        } 
        else 
        {
            // ... user chose CANCEL or closed the dialog
        } 
    }

    @FXML
    private void addManagerButton(ActionEvent event) {
    }

    @FXML
    private void removeManagerButton(ActionEvent event)
    {
        
    }

    @FXML
    private void removeHoursButton(ActionEvent event) throws SQLException 
    {
        CheckIn selectedItem = hoursAdminTable.getSelectionModel().getSelectedItem();
        checkIn = selectedItem;
        volunteerModel.deleteHours(checkIn);
        hoursAdminTable.getItems().remove(selectedItem);
        hoursAdminTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void addHoursButton(ActionEvent event) throws SQLException, IOException 
    {
        if (datePicker.getValue() != null && guildAdminTable.getSelectionModel().getSelectedItem() != null && nameAdminTable.getSelectionModel().getSelectedItem() != null && !txtFieldHours.getText().isEmpty()) {

            LocalDateTime timeStamp = datePicker.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);
            //datePicker timeStamp = datePick.getDayCellFactory().trim();
            int nameId = nameAdminTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setCheckInsByNameId(nameId);
            //int nameId = Integer.parseInt(nameColumn.getText().trim());
            int hours = Integer.parseInt(txtFieldHours.getText().trim());
            VolunteerModel.getInstance().addHours(new CheckIn(dateTime, nameId, hours));
            hoursAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
            dateAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursAdminTable.setItems(volunteerModel.getAllCheckIns());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText("Du har bidraget med " + txtFieldHours.getText() + " timer");
            alert.showAndWait();

        } else if (datePicker.getValue() == null || guildAdminTable.getSelectionModel().getSelectedItem() == null || nameAdminTable.getSelectionModel().getSelectedItem() == null || txtFieldHours.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbejde, før du kan indtaste timer.");
            alert.showAndWait();
        }
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

    @FXML
    private void handleVolunteersHours(MouseEvent event) throws SQLException 
    {
        if (event.isPrimaryButtonDown() == false) {
            int nameId = nameAdminTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setCheckInsByNameId(nameId);
            hoursAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
            dateAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursAdminTable.setItems(volunteerModel.getAllCheckIns());
        }
    }
}
