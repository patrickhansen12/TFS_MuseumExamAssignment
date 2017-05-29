package museumvolunteer.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.ContainsSearch;
import museumvolunteer.BLL.SearchPattern;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class ManagerViewController extends AController implements Initializable {

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
    @FXML
    private TextField searchNameField;

    //private variables.
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;
    private CheckIn checkIn;
    int currentUserAddVolunteer = 0;
    int currentUserVolunteerInfo = 0;

    /**
     * Initializes the ManagerViewController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            datePicker.setValue(LocalDate.now());
            dataBind();
        } catch (SQLException ex) {
            Logger.getLogger(VolunteerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ManagerViewController() throws IOException, SQLException {
        volunteerModel = new VolunteerModel();
        guildsModel = new GuildsModel();
    }

    @FXML
    private void addVolunteersButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/AddVolunteer.fxml"));
        root = loader.load();
        AddVolunteerController controller = loader.getController();
        controller.pullCurrentUserAddVolunteer(currentUserAddVolunteer);
        Scene scene = new Scene(root);
        stage.setTitle("Tilføj frivillig");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        Stage stage2 = (Stage) ManagerScreen.getScene().getWindow();
        stage2.close();
    }

    /**
     * Method for deleting a selected volunteer both in the observable list
     * Volunteer, but also in database table Names.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void deleteVolunteersButton(ActionEvent event) throws SQLException {
        if (nameManagerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig, før du kan slette dem.");
            alert.showAndWait();
        }

        if (nameManagerTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog with Custom Actions");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på du vil slette " + nameManagerTable.getSelectionModel().getSelectedItem().getNameAsString() + "?");
            ButtonType buttonTypeThis = new ButtonType("Godkend");
            ButtonType buttonTypeCancel = new ButtonType("Anuller", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeThis, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeThis) {
                Volunteer selectedItem = nameManagerTable.getSelectionModel().getSelectedItem();
                volunteer = selectedItem;

                int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
                int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getIdValue();
                volunteerModel.deleteVolunteerByNameIdGuildsId(nameId, guildsId);
                nameManagerTable.getItems().remove(selectedItem);
                nameManagerTable.getSelectionModel().clearSelection();
                hoursManagerTable.getItems().clear();
            }
        }
    }

    /**
     * Returns the manager to MainView.
     *
     * @param event
     * @throws IOException
     * @throws java.sql.SQLException
     */
    @FXML
    @Override
    public void returnButton(ActionEvent event) throws IOException, SQLException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/MainView.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Indgang");
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

        stage = (Stage) ManagerScreen.getScene().getWindow();
        stage.close();
    }

    private void dataBind() throws SQLException {

        guildManagerColumn.setCellValueFactory(guildAdminCol -> guildAdminCol.getValue().getName());
        guildManagerTable.getItems().setAll(guildsModel.getGuilds());
        guildManagerTable.setPlaceholder(new Label("Der er ikke nogen \nlaug at vise"));
        nameManagerTable.setPlaceholder(new Label("Der er ikke nogen \ntimer at vise"));
        hoursManagerTable.setPlaceholder(new Label("Der er ikke nogen \nnavne at vise"));
    }

    /**
     * Method for selecting all volunteers matching the guild that was clicked.
     *
     * @param event
     * @throws SQLException
     * @throws java.io.IOException
     */
    @FXML
    @Override
    public void handleGuildsVolunteers(MouseEvent event) throws SQLException, IOException {
        if (guildManagerTable.getSelectionModel().getSelectedItem().getIdValue() != -1) {

            int guildId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.getNamesByGuildId(guildId);

            nameManagerColumn.setCellValueFactory(managerManagerCol -> managerManagerCol.getValue().getName());
            nameManagerTable.setItems(volunteerModel.getAllVolunteers());

            hoursManagerTable.getItems().clear();
            searchNameField.clear();

        }
    }

    /**
     * Adds hours to the selected volunteer, both in the observable list
     * CheckIn, but also in the database table Hours.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void addHoursButton(ActionEvent event) throws SQLException, IOException {
        if (datePicker.getValue() != null && guildManagerTable.getSelectionModel().getSelectedItem().getIdValue() != -1 && nameManagerTable.getSelectionModel().getSelectedItem().getIdValue() != -1 && !txtFieldHours.getText().isEmpty()) {
            LocalDateTime timeStamp = datePicker.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);
            int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
            int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getIdValue();
            int hours = Integer.parseInt(txtFieldHours.getText().trim());
            volunteerModel.addHours(new CheckIn(dateTime, guildsId, nameId, hours));
            volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);
            hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHoursValue()));
            dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursManagerTable.setItems(volunteerModel.getAllCheckIns());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText(nameManagerTable.getSelectionModel().getSelectedItem().getNameAsString() + " har bidraget med " + txtFieldHours.getText() + " time(r)" + " til " + guildManagerTable.getSelectionModel().getSelectedItem().getNameAsString());
            txtFieldHours.clear();
            alert.showAndWait();

        } else if (datePicker.getValue() == null || guildManagerTable.getSelectionModel().getSelectedItem().getIdValue() == -1 || nameManagerTable.getSelectionModel().getSelectedItem().getIdValue() == -1 || txtFieldHours.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbejde, før du kan indtaste timer.");
            alert.showAndWait();
        }
    }

    /**
     * Method for deleting the selected hours for a volunteer.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void deleteHoursButton(ActionEvent event) throws SQLException {
        if (hoursManagerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig, før du kan slette deres timer .");
            alert.showAndWait();
        }
        if (hoursManagerTable.getSelectionModel().getSelectedItem() != null) {
            CheckIn selectedItem = hoursManagerTable.getSelectionModel().getSelectedItem();
            checkIn = selectedItem;
            volunteerModel.deleteHours(checkIn);
            hoursManagerTable.getItems().remove(selectedItem);
            hoursManagerTable.getSelectionModel().clearSelection();
        }
    }

    /**
     * Populates a list of hours/timeStamps for a clicked volunteer.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void handleVolunteersHours(MouseEvent event) throws SQLException, IOException {
        if (nameManagerTable.getSelectionModel().getSelectedItem().getIdValue() != -1) {
            int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
            int nameId = nameManagerTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);
            hoursManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHoursValue()));
            dateManagerColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursManagerTable.setItems(volunteerModel.getAllCheckIns());
        }
    }

    /**
     * Opens the VolunteerInfo view and passes id, name, email, phoneNumber and
     * guildsId on the clicked volunteer into VolunteerInfoController.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void handleInfo(ActionEvent event) throws SQLException, IOException {
        if (nameManagerTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig før du kan se informationer om dem.");
            alert.showAndWait();
        }

        if (nameManagerTable.getSelectionModel().getSelectedItem() != null) {
            try {
                Stage stage = new Stage();
                Parent root;
                int id = nameManagerTable.getSelectionModel().getSelectedItem().getIdValue();
                String name = nameManagerTable.getSelectionModel().getSelectedItem().getNameAsString();
                String email = nameManagerTable.getSelectionModel().getSelectedItem().getEmailAsString();
                String phoneNumber = nameManagerTable.getSelectionModel().getSelectedItem().getPhoneNumberAsString();
                int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/VolunteerInfoView.fxml"));
                root = loader.load();
                VolunteerInfoViewController controller = loader.getController();
                controller.transferVolunteerInfo(new Volunteer(id, name, email, phoneNumber, guildsId));
                controller.pullCurrentUserVolunteerInfo(currentUserVolunteerInfo);
                Scene scene = new Scene(root);
                stage.setTitle("Rediger frivillig");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

                Stage stage2 = (Stage) ManagerScreen.getScene().getWindow();
                stage2.close();

            } catch (IOException ex) {
                System.out.println("HandleInfo " + ex);
            }
        }
    }

    @FXML
    @Override
    public void searchNameList(KeyEvent event) throws SQLException {

        String query = searchNameField.getText().trim();
        List<String> searchResult = null;
        SearchPattern searchStrategy;
        searchStrategy = new ContainsSearch(query);
        int guildId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
        searchResult = volunteerModel.search(searchStrategy, guildId);
        volunteerModel.setFilteredNames(searchResult);
        nameManagerTable.setItems(volunteerModel.getNames().sorted());
        if (query.isEmpty()) {
            int guildsId = guildManagerTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.getNamesByGuildId(guildsId);
            nameManagerColumn.setCellValueFactory(managerAdminCol -> managerAdminCol.getValue().getName());
            nameManagerTable.setItems(volunteerModel.getAllVolunteers());
        }
    }
}
