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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * FXML Controller class
 *
 * @author Nicolai, Emil, Patrick, Kasper, Casper
 */
public class ManagerViewController implements Initializable {

    @FXML
    private TableView<Guild> guildManagerTable;
    @FXML
    private TableColumn<Guild, String> guildManagerColumn;
    @FXML
    private TableView<Volunteer> nameManagerTable;
    @FXML
    private TableColumn<Volunteer, String> nameManagerColumn;
    @FXML
    private AnchorPane ManagerScreen;
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;
    private CheckIn checkIn;

    @FXML
    private TableView<CheckIn> hoursManagerTable;
    @FXML
    private TableColumn<CheckIn, Integer> hoursManagerColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField txtFieldHours;
    @FXML
    private TableColumn<CheckIn, Timestamp> dateManagerColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        datePicker.setValue(LocalDate.now());
        dataBind();
    }

    public ManagerViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
    }

    @FXML
    private void addVolunteersButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddVolunteer.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Tilføj frivillig");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteVolunteersButton(ActionEvent event) throws SQLException {
        Volunteer selectedItem = nameManagerTable.getSelectionModel().getSelectedItem();
        volunteer = selectedItem;
        volunteerModel.deleteVolunteer(volunteer);
        nameManagerTable.getItems().remove(selectedItem);
        nameManagerTable.getSelectionModel().clearSelection();

    }

    @FXML
    private void returnManager(ActionEvent event) throws IOException {
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

    private void dataBind() {

        guildManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
        guildManagerTable.setItems(guildsModel.getGuilds());
    }

    public void setModel(VolunteerModel volunteerModel) {
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
    private void addHoursButton(ActionEvent event) throws SQLException, IOException {
        if (datePicker.getValue() != null && guildManagerTable.getSelectionModel().getSelectedItem() != null && nameManagerTable.getSelectionModel().getSelectedItem() != null && !txtFieldHours.getText().isEmpty()) {

            LocalDateTime timeStamp = datePicker.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);
            //datePicker timeStamp = datePick.getDayCellFactory().trim();
            int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setCheckInsByNameId(nameId);
            //int nameId = Integer.parseInt(nameColumn.getText().trim());
            int hours = Integer.parseInt(txtFieldHours.getText().trim());
            VolunteerModel.getInstance().addHours(new CheckIn(dateTime, nameId, hours));
            hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
            dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursManagerTable.setItems(volunteerModel.getAllCheckIns());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText(nameManagerTable.getSelectionModel().getSelectedItem().getName() + " har bidraget med " + txtFieldHours.getText() + " timer");
            alert.showAndWait();

        } else if (datePicker.getValue() == null || guildManagerTable.getSelectionModel().getSelectedItem() == null || nameManagerTable.getSelectionModel().getSelectedItem() == null || txtFieldHours.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbejde, før du kan indtaste timer.");
            alert.showAndWait();
        }
    }

    @FXML
    private void deleteHoursButton(ActionEvent event) throws SQLException 
    {
        CheckIn selectedItem = hoursManagerTable.getSelectionModel().getSelectedItem();
        checkIn = selectedItem;
        volunteerModel.deleteHours(checkIn);
        hoursManagerTable.getItems().remove(selectedItem);
        hoursManagerTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleVolunteersHours(MouseEvent event) throws SQLException {
        if (event.isPrimaryButtonDown() == false) {
            int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setCheckInsByNameId(nameId);
            hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHours()));
            dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursManagerTable.setItems(volunteerModel.getAllCheckIns());
        }
    }

}
