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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import museumvolunteer.BE.CheckIn;
import museumvolunteer.BE.Guild;
import museumvolunteer.BE.Manager;
import museumvolunteer.BE.Volunteer;
import museumvolunteer.BLL.BLLFacade;
import museumvolunteer.BLL.ContainsSearch;
import museumvolunteer.BLL.SearchPattern;
import museumvolunteer.GUI.Model.AdminModel;
import museumvolunteer.GUI.Model.GuildsModel;
import museumvolunteer.GUI.Model.VolunteerModel;

/**
 * @author Nicolai, Patrick, Kasper, Casper
 */
public class AdministratorViewController implements Initializable {

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
    @FXML
    private TableColumn<CheckIn, Timestamp> dateAdminColumn;
    @FXML
    private TextField txtFieldHours;
    @FXML
    private DatePicker datePicker;

    //private variables.
    private VolunteerModel volunteerModel;
    private GuildsModel guildsModel;
    private Volunteer volunteer;
    private Guild guild;
    private CheckIn checkIn;
    private AdminModel adminModel;
    @FXML
    private Button exportToExcel;
    @FXML
    private TextField searchNameField;
    private BLLFacade bllFacade;

    /**
     * Initializes the AdministratorViewController class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dataBind();
            datePicker.setValue(LocalDate.now());
        } catch (SQLException ex) {
            Logger.getLogger(AdministratorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * gets instances of volunteerModel and guildsModel.
     *
     * @throws IOException
     * @throws SQLException
     */
    public AdministratorViewController() throws IOException, SQLException {
        volunteerModel = VolunteerModel.getInstance();
        guildsModel = GuildsModel.getInstance();
        adminModel = AdminModel.getInstance();
        bllFacade = new BLLFacade();
    }

    /**
     * Returns the admin to MainView.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void returnAdmin(ActionEvent event) throws IOException {
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

    /**
     * Method for adding a new guild.
     *
     * @param event
     */
    @FXML
    private void addGuildButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddGuild.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Tilføj laug");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the view AddVolunteer
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void addVolunteerButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddVolunteer.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Tilføj frivillig");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Removes the guild selected.
     *
     * @param event
     */
    @FXML
    private void removeGuildButton(ActionEvent event) throws SQLException {
        if (guildAdminTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge et laug, før du kan slette det.");
            alert.showAndWait();
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Er du sikker?");
        alert.setHeaderText(null);
        alert.setContentText("Er du sikker på du vil slette dette laug?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Guild selectedItem = guildAdminTable.getSelectionModel().getSelectedItem();
            guild = selectedItem;
            guildsModel.deleteGuild(guild);
            guildAdminTable.getItems().remove(selectedItem);
            guildAdminTable.getSelectionModel().clearSelection();
        }
    }

    /**
     * Removes the selected volunteer from the observable list Volunteer and the
     * database table Names.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void removeVolunteersButton(ActionEvent event) throws SQLException, IOException {
        if (nameAdminTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig, før du kan slette dem.");
            alert.showAndWait();
        }

        if (nameAdminTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog with Custom Actions");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på du vil slette " + nameAdminTable.getSelectionModel().getSelectedItem().getNameAsString() + "?");
            ButtonType buttonTypeThis = new ButtonType("Godkend");
            ButtonType buttonTypeCancel = new ButtonType("Anuller", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeThis, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeThis) {
                Volunteer selectedItem = nameAdminTable.getSelectionModel().getSelectedItem();
                volunteer = selectedItem;
                int guildsId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
                int nameId = nameAdminTable.getSelectionModel().getSelectedItem().getIdValue();
                volunteerModel.deleteVolunteerByNameIdGuildsId(nameId, guildsId);
                volunteerModel.deleteVolunteer(volunteer);
                nameAdminTable.getItems().remove(selectedItem);
                nameAdminTable.getSelectionModel().clearSelection();
            }
        }
    }

    /**
     * Adds a new manager to observable list Manager and to database table
     * Managers.
     *
     * @param event
     */
    @FXML
    private void addManagerButton(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/museumvolunteer/GUI/View/AddManager.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Tilføj manager");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Removes a selected manager from observable list Manager and database
     * table Managers.
     *
     * @param event
     */
    @FXML
    private void removeManagerButton(ActionEvent event) throws SQLException {
        if (managerAdminTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en manager, før du kan slette vedkommende.");
            alert.showAndWait();
        }

        if (managerAdminTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog with Custom Actions");
            alert.setHeaderText(null);
            alert.setContentText("Er du sikker på du vil slette " + managerAdminTable.getSelectionModel().getSelectedItem().getNameAsString() + "?");
            ButtonType buttonTypeThis = new ButtonType("Godkend");
            ButtonType buttonTypeCancel = new ButtonType("Anuller", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeThis, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeThis) {
                Manager selectedItem = managerAdminTable.getSelectionModel().getSelectedItem();
                adminModel.deleteManager(selectedItem);
                managerAdminTable.getItems().remove(selectedItem);
                managerAdminTable.getSelectionModel().clearSelection();
            }
        }
    }

    /**
     * Removes selected hours for a specific volunteer.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void removeHoursButton(ActionEvent event) throws SQLException {

        if (hoursAdminTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig, før du kan slette deres timer .");
            alert.showAndWait();
        }
        if (hoursAdminTable.getSelectionModel().getSelectedItem() != null) {
            CheckIn selectedItem = hoursAdminTable.getSelectionModel().getSelectedItem();
            checkIn = selectedItem;
            volunteerModel.deleteHours(checkIn);
            hoursAdminTable.getItems().remove(selectedItem);
            hoursAdminTable.getSelectionModel().clearSelection();
        }
    }

    /**
     * Adds hours to the selected volunteer, into observable list CheckIn and
     * database table Hours.
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void addHoursButton(ActionEvent event) throws SQLException, IOException {
        if (datePicker.getValue() != null && guildAdminTable.getSelectionModel().getSelectedItem().getIdValue() != -1 && nameAdminTable.getSelectionModel().getSelectedItem().getIdValue() != -1 && !txtFieldHours.getText().isEmpty()) {

            LocalDateTime timeStamp = datePicker.getValue().atTime(LocalTime.now());
            java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(timeStamp);

            int guildsId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
            int nameId = nameAdminTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);

            int hours = Integer.parseInt(txtFieldHours.getText().trim());
            volunteerModel.addHours(new CheckIn(dateTime, guildsId, nameId, hours));
            dateAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getHoursValue()));
            hoursAdminTable.setItems(volunteerModel.getAllCheckIns());
//hoursAdminTable.getColumns().get(0).setVisible(false);
//        hoursAdminTable.getColumns().get(1).setVisible(false);
//      hoursAdminTable.getSelectionModel().clearSelection();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bidragede timer");
            alert.setHeaderText(null);
            alert.setContentText(nameAdminTable.getSelectionModel().getSelectedItem().getNameAsString() + " har bidraget med " + txtFieldHours.getText() + " timer");
            txtFieldHours.setText("");
            hoursAdminTable.refresh();

            alert.showAndWait();

        } else if (datePicker.getValue() == null || guildAdminTable.getSelectionModel().getSelectedItem().getIdValue() == -1 || nameAdminTable.getSelectionModel().getSelectedItem().getIdValue() == -1 || txtFieldHours.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge både laug, navn og antal timers frivilligt arbejde, før du kan indtaste timer.");
            alert.showAndWait();
        }
    }

    private void dataBind() throws SQLException {
        guildAdminColumn.setCellValueFactory(guildAdminCol -> guildAdminCol.getValue().getName());
        guildAdminTable.setItems(guildsModel.getGuilds());

        nameAdminColumn.setCellValueFactory(managerAdminCol -> managerAdminCol.getValue().getName());
        nameAdminTable.setItems(volunteerModel.getAllVolunteers());

        managerAdminColumn.setCellValueFactory(managerAdminCol -> managerAdminCol.getValue().getName());
        managerAdminTable.setItems(adminModel.getAllManagers());
    }

    /**
     * Populates ameAdminTable with volunteers matching the clicked guild.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    public void handleGuildsVolunteers(MouseEvent event) throws SQLException {
        if (guildAdminTable.getSelectionModel().getSelectedItem() != null) {
            int guildsId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.setNamesByGuildId(guildsId);

            nameAdminColumn.setCellValueFactory(managerAdminCol -> managerAdminCol.getValue().getName());
            nameAdminTable.setItems(volunteerModel.getAllVolunteers());
            hoursAdminTable.getColumns().clear();
            hoursAdminColumn.setCellValueFactory(hoursAdminCol -> hoursAdminCol.getValue().getHours().asObject());
            dateAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursAdminTable.setItems(volunteerModel.getAllCheckIns());
        }
        searchNameField.clear();
    }

    /**
     * Populates hoursAdminTable with hours for the clicked volunteer.
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void handleVolunteersHours(MouseEvent event) throws SQLException, IOException {
        if (nameAdminTable.getSelectionModel().getSelectedItem() != null) {
            int guildsId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
            int nameId = nameAdminTable.getSelectionModel().getSelectedItem().getIdValue();
            volunteerModel.setCheckInsByNameIdGuildsId(guildsId, nameId);
            hoursAdminColumn.setCellValueFactory(hoursAdminCol -> hoursAdminCol.getValue().getHours().asObject());
            dateAdminColumn.setCellValueFactory(value -> new SimpleObjectProperty<>(value.getValue().getDateTime()));
            hoursAdminTable.setItems(volunteerModel.getAllCheckIns());
        }
    }

    @FXML
    public void handleExportToExcel(ActionEvent event) throws SQLException, IOException {
        int guildsId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
        String volunteerName = nameAdminTable.getSelectionModel().getSelectedItem().getNameAsString();
        int nameId = nameAdminTable.getSelectionModel().getSelectedItem().getIdValue();
        volunteerModel.setCheckInsByNameIdGuildsIdToExcel(guildsId, nameId);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export til Excel");
        alert.setHeaderText(null);
        alert.setContentText("Du har eksporteret data om " + volunteerName + " til Excel");
        alert.showAndWait();
    }

    @FXML
    private void searchNameList(KeyEvent event) throws SQLException {
        String query = searchNameField.getText().trim();
        List<String> searchResult = null;
        SearchPattern searchStrategy;
        searchStrategy = new ContainsSearch(query);
        int guildId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
        searchResult = bllFacade.search(searchStrategy, guildId);
        volunteerModel.setFilteredNames(searchResult);
        nameAdminTable.setItems(volunteerModel.getNames().sorted());
    }

//    public void cleanTableViews() {
//        nameAdminTable.getColumns().get(0).setVisible(false);
//        hoursAdminTable.getColumns().get(0).setVisible(false);
//        hoursAdminTable.getColumns().get(1).setVisible(false);
//        hoursAdminTable.getSelectionModel().clearSelection();
//        nameAdminTable.getSelectionModel().clearSelection();
//    }
    @FXML
    private void handleInfo(ActionEvent event) throws SQLException {
        if (nameAdminTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fejl");
            alert.setHeaderText(null);
            alert.setContentText("Du skal vælge en frivillig før du kan se informationer om dem.");
            alert.showAndWait();
        }

        if (nameAdminTable.getSelectionModel().getSelectedItem() != null) {
            try {
                int id = nameAdminTable.getSelectionModel().getSelectedItem().getIdValue();
                String name = nameAdminTable.getSelectionModel().getSelectedItem().getNameAsString();
                String email = nameAdminTable.getSelectionModel().getSelectedItem().getEmailAsString();
                String phoneNumber = nameAdminTable.getSelectionModel().getSelectedItem().getPhoneNumberAsString();
                int guildsId = guildAdminTable.getSelectionModel().getSelectedItem().getIdValue();
                Stage stage = new Stage();
                Parent root;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/museumvolunteer/GUI/View/VolunteerInfoView.fxml"));
                root = loader.load();
                VolunteerInfoViewController controller = loader.getController();
                controller.doMagicStuff(new Volunteer(id, name, email, phoneNumber, guildsId));
                Scene scene = new Scene(root);
                stage.setTitle("Rediger frivillig");
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);

                stage.setScene(scene);
                stage.show();

//                stage = (Stage) adminScreen.getScene().getWindow();
//                stage.close();
            } catch (IOException ex) {
                System.out.println("HandleInfo " + ex);
            }
        }
    }
}
