/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class VolunteerViewController implements Initializable {

    @FXML
    private TableView<Guild> guildTable;
    @FXML
    private TableColumn<Guild, String> guildColumn;
    @FXML
    private TableView<Volunteer> nameTable;
    @FXML
    private TableColumn<Volunteer, String> nameColumn;
    @FXML
    private AnchorPane VolunteerScreen;

    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    @FXML
    private TextField searchnameField;
    @FXML
    private TextField noteHoursField;
    @FXML
    private DatePicker datePick;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dataBind();
        datePick.setValue(LocalDate.now());
        datePick.setVisible(false);
    }

    public VolunteerViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
    }

    @FXML
    private void backVolunteer(ActionEvent event) throws IOException {
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

    private void dataBind() {
        guildColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
        guildTable.setItems(guildsModel.getGuilds());
        nameTable.setPlaceholder(new Label("Der er ikke \nnogen navne \nat vise"));
        guildTable.setPlaceholder(new Label("Der er ikke \nnogen laug \nat vise"));
    }

    public void setModel(VolunteerModel volunteerModel) {
        this.volunteerModel = volunteerModel;
    }

    @FXML
    private void handleGuildsVolunteers(MouseEvent event) throws SQLException {
        if (event.isPrimaryButtonDown() == false) {
            int guildId = guildTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setNamesByGuildId(guildId);
            nameColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getName()));
            nameTable.setItems(volunteerModel.getAllVolunteers());
        }
    }

    @FXML
    private void insertHours(ActionEvent event) throws SQLException, IOException {

        if (datePick.getValue() != null && guildTable.getSelectionModel().getSelectedItem() != null && nameTable.getSelectionModel().getSelectedItem() != null && !noteHoursField.getText().isEmpty()) {

            LocalDateTime timeStamp = datePick.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);
            //datePicker timeStamp = datePick.getDayCellFactory().trim();
            int nameId = nameTable.getSelectionModel().getSelectedItem().getId();
            volunteerModel.setCheckInsByNameId(nameId);
            //int nameId = Integer.parseInt(nameColumn.getText().trim());
            int hours = Integer.parseInt(noteHoursField.getText().trim());
            VolunteerModel.getInstance().addHours(new CheckIn(dateTime, nameId, hours));

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText("Du har bidraget med " + noteHoursField.getText() + " timer");
            alert.showAndWait();

        } else if (datePick.getValue() == null || guildTable.getSelectionModel().getSelectedItem() == null || nameTable.getSelectionModel().getSelectedItem() == null || noteHoursField.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbjede, før du kan indtaste timer.");
            alert.showAndWait();
        }
    }
}
